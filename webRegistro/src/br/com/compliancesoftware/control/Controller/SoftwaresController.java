package br.com.compliancesoftware.control.Controller;

import java.util.List;

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
import br.com.compliancesoftware.control.dao.SoftwaresDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.Software;

/**
 * Controller para softwares
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
@Transactional
@Controller
public class SoftwaresController 
{
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao perfisDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("softwaresJPA")
	@Autowired
	private SoftwaresDao softwaresDao;
	
	private String mensagem = null;
	
	/**
	 * Acesso à página de gerenciamento de softwares.
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("gerenciarSoftwares")
	public String gerenciarRegistros(HttpSession session, Model model)
	{
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		List<Software> listaSoftwares = softwaresDao.lista();
		model.addAttribute("listaSoftwares",listaSoftwares);
		
		return "softwares/softwares";
	}
	
	/**
	 * Aplica filtro na pesquisa e retorna apenas a tabela, o foco é acessar este método via Ajax
	 * @param filtro
	 * @return
	 */
	@RequestMapping("pesquisarSoftwares")
	public String pesquisarSoftwares(String pesquisa, Model model)
	{
		List<Software> listaSoftwares = softwaresDao.listaPorNome(pesquisa);
		model.addAttribute("listaSoftwares",listaSoftwares);
		
		return "softwares/tabela";
	}
	
	/**
	 * Método que leva à página de realização de novo cadastro
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("cadastrarSoftware")
	public String paginaDeCadastro(HttpSession session, Model model)
	{
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		return "registros/cadastrar";
	}
	
	/**
	 * Método usado para realizar o novo cadastro.
	 * @param software
	 * @param session
	 * @return
	 */
	@RequestMapping("cadastraRegistro")
	public String cadastraSoftware(Software software, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		
		mensagem = softwaresDao.adiciona(software);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [Software: "+software.getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarSoftwares";
	}
	
	/**
	 * Método usado para remover softwares.
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("removerSoftware")
	public String removerSoftware(Long id, HttpSession session, Model model)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		
		mensagem = softwaresDao.remove(id);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [SoftwareId: "+id+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarSoftwares";
	}
}
