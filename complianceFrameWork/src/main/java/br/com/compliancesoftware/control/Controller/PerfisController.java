package br.com.compliancesoftware.control.Controller;

import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;

/**
 * Controller criado para organizar o método de atualização do perfil.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class PerfisController 
{
	private String mensagem = null;
	
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao dao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	/**
	 * Acessa a tela de cadastro de perfil
	 * @return página de cadastro
	 */
	@RequestMapping("cadastrarNovoPerfil")
	public String cadastrarNovo(HttpSession session, Model model) throws Exception
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		model.addAttribute("logged",logado);
		return "perfis/cadastrar";
	}
	
	/**
	 * Método para cadatrar novo Perfil no banco de dados
	 */
	@RequestMapping("cadastraPerfil")
	public String cadastra(Perfil perfil, HttpSession session, HttpServletRequest request) throws Exception
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		perfil.setAcesso(now());
		perfil.setUlt_acesso(now());
		perfil.setDefaultFoto();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("image");
		
		byte[] conteudo = multipartFile.getBytes();
		
		if(multipartFile != null && conteudo != null && conteudo.length > 1)
		{
			try
			{
				byte[] image = multipartFile.getBytes();
				
				perfil.setFoto(image);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		mensagem = dao.adiciona(perfil);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " ["+perfil.getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		SystemController.setMsg(mensagem);
		mensagem = null;
		return "redirect:gerenciarPerfis";
	}
	
	/**
	 * Atualiza o banco de perfis de usuários.
	 * @return
	 */
	@RequestMapping("atualizaPerfil")
	public String atualiza(Perfil perfil, HttpSession session, HttpServletRequest request) throws Exception
	{
		Perfil logged = (Perfil)session.getAttribute("logado");
		
		Perfil onDB = dao.getPerfilById(perfil.getId());
		perfil.setFoto(onDB.getFoto());
		perfil.setAcesso(onDB.getAcesso());
		perfil.setUlt_acesso(onDB.getUlt_acesso());
		if(!(logged.getPermissao().equals(PerfisDao.ADM)))
			perfil.setPermissao(onDB.getPermissao());
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("image");
		
		byte[] conteudo = multipartFile.getBytes();
		
		if(multipartFile != null && conteudo != null && conteudo.length > 1)
		{
			try
			{
				byte[] image = multipartFile.getBytes();
				
				perfil.setFoto(image);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		String msg = dao.alteraPerfil(logged,perfil);
		if(msg.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(msg + " ["+perfil.getNome()+"]");
			log.setAutor(logged);
			log.setData(null);
			logsDao.adiciona(log);
		}
		session.setAttribute("logado", logged);
		SystemController.setLogado(logged);
		if(msg != null)
			SystemController.setMsg(msg);
		
		return "redirect:gerenciarPerfis";
	}
	
	/**
	 * Auxilia na recuperação de imagens do banco de dados.
	 */
	@RequestMapping("mostraFoto")
	public void mostraFoto(Long id, HttpServletResponse response) throws Exception
	{
		Perfil perfil = dao.getPerfilById(id);
		byte[] foto = perfil.getFoto();
		
		if(foto != null)
		{
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(foto);
			response.getOutputStream().close();
		}
	}
	
	private Calendar now()
	{
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		return agora;
	}
	
	/**
	 * Rest service para perfis
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("perfilREST")
	public void getPerfil(Long id, HttpServletResponse response) throws Exception
	{
		Perfil perfil = dao.getPerfilById(id);
		
		if(perfil == null)
		{
			perfil = new Perfil();
			perfil.setId(0);
			perfil.setNome("Não encontrado");
			perfil.setFoto(null);
			perfil.setContato("error");
			perfil.setEmail("error");
		}
		
		response.setContentType("text/xml");
		PrintWriter writer = response.getWriter();
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		writer.println("<webDeliveryAdm>");
		
		writer.println("<dataBase>");
		writer.println("<perfil>");
		
		writer.println("<id>");
		writer.println(""+perfil.getId());
		writer.println("</id>");
		
		writer.println("<nome>");
		writer.println(""+perfil.getNome());
		writer.println("</nome>");
		
		String foto = "";
		if(perfil.getFoto() != null)
		{
			byte[] imagem = perfil.getFoto();
			for(byte b:imagem)
			{
				foto += (int)b;
			}
		}
		
		writer.println("<foto>");
		writer.println(""+foto);
		writer.println("</foto>");
		
		writer.println("<contato>");
		writer.println(""+perfil.getContato());
		writer.println("</contato>");
		
		writer.println("<email>");
		writer.println(""+perfil.getEmail());
		writer.println("</email>");
		
		writer.println("</perfil>");
		writer.println("</dataBase>");
		
		writer.println("</webDeliveryAdm>");
		
		writer.close();
	}
}
