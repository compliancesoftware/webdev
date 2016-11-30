package br.com.compliancesoftware.control.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.auxModels.RegistroREST;

/**
 * Controlador das páginas de login
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class LoginController 
{
	private String mensagem="";
	
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao dao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	/**
	 * Função de efetuar o login de perfil.
	 * @param perfil
	 * @param session
	 * @return
	 */
	@RequestMapping("entrar")
	public String efetuaLogin(String nome, String senha, HttpSession session)
	{
		try
		{
			RegistroREST reg = RegistroREST.unmarshall();
			if(reg != null && reg.isAtivo())
			{
				Perfil perfil = new Perfil();
				perfil.setNome(nome);
				perfil.setSenha(senha);
				
				mensagem = "<strong>Logout!</strong> Sessão expirada!";
				HashMap<String,Object> result = dao.login(perfil);
				Perfil logado = (Perfil)result.get("Perfil");
				String msg = (String)result.get("Mensagem");
				
				if(logado == null)
				{
					mensagem = msg;
					return "redirect:login";
				}
				
				if(msg.contains(">OK"))
				{
					Log log = new Log();
					log.setAcao(msg + " ["+perfil.getNome()+"]");
					log.setAutor(logado);
					log.setData(null);
					logsDao.adiciona(log);
				}
				
				session.setAttribute("logado", logado);
				SystemController.setLogado(logado);
				return "redirect:home";
			}
			else
			{
				if(reg != null)
				{
					mensagem = "<strong>Erro!</strong> Seu serviço está suspenso, contacte o fornecedor do sistema.";
					return "redirect:login";
				}
				else
				{
					mensagem = "<strong>Erro!</strong> Não foi possível consultar suas credenciais. Verifique sua conexão com a internet.";
					return "redirect:login";
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "redirect:login";
		}
	}
	
	/**
	 * Efetua logout
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		mensagem = "<strong>Logout!</strong> Você saiu do sistema.";
		SystemController.setLogado(null);
		return "redirect:login";
	}
	
	/**
	 * Direciona usuário para a tela de login.
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model, HttpSession session)
	{
		RegistroREST reg = RegistroREST.unmarshall();
		if(reg != null && reg.isAtivo())
		{
			alertasDao.primeiroUso();
			dao.primeiroUso();
			
			Perfil logado = (Perfil)session.getAttribute("logado");
			if(logado != null)
			{
				SystemController.setLogado(logado);
				return "redirect:home";
			}
			
		}
		else
		{
			mensagem = "<strong>Erro!</strong> Seu serviço está suspenso, contacte o fornecedor do sistema.";
		}
		
		model.addAttribute("mensagem",mensagem);
		
		mensagem = "";
		return "login";
	}
	
	/**
	 * Página principal do acesso ao aplicativo.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String mainPage(Model model, HttpSession session)
	{
		return login(model, session);
	}
	
}
