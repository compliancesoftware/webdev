package br.com.compliancesoftware.control.Controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.ClientesDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;

/**
 * Controller para clientes
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class ClientesController 
{
	@Qualifier("clientesJPA")
	@Autowired
	private ClientesDao clientesDao;
	
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao perfisDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	private List<Cliente> listaClientes = null;
	
	private String mensagem = null;
	
	/**
	 * Retorna à página de clientes.
	 * @param model
	 * @return
	 */
	@RequestMapping("gerenciarClientes")
	public String clientes(HttpSession session, Model model)
	{
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		if(listaClientes != null && listaClientes.size()>0)
		{
			model.addAttribute("listaClientes",listaClientes);
			listaClientes = null;
		}
		else
		{
			List<Cliente> lista = clientesDao.listaClientes();
			model.addAttribute("listaClientes",lista);
		}
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		return "clientes/clientes";
	}
	
	/**
	 * Página de cadastro de cliente
	 * @param model
	 * @return
	 */
	@RequestMapping("cadastrarCliente")
	public String cadastrar(HttpSession session, Model model)
	{
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		return "clientes/cadastrar";
	}
	
	/**
	 * Método para cadastrar novo Cliente
	 * @param model
	 * @return
	 */
	@RequestMapping("cadastraCliente")
	public String cadastra(Cliente cliente, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		
		cliente.setQuemIncluiu(logado);
		cliente.setDtInclusao(agora);
		
		mensagem = clientesDao.adiciona(cliente);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " ["+cliente.getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarClientes";
	}
	
	@RequestMapping("pesquisaClientes")
	public String pesquisaClientes(String pesquisa, Model model)
	{
		List<Cliente> lista = clientesDao.pesquisaCliente(pesquisa);
		model.addAttribute("listaClientes",lista);
		
		return "clientes/tabela";
	}
	
	/**
	 * Acesso à página de atualização do cliente de acordo com seu id.
	 * @param id
	 * @param session
	 * @param model
	 */
	@RequestMapping("atualizarCliente")
	public String paginaDeAtualizacao(Long id, HttpSession session, Model model)
	{
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		Cliente cliente = clientesDao.pegaClientePorId(id);
		model.addAttribute("cliente",cliente);

		return "clientes/atualizar";
	}
	
	/**
	 * Método para atualizar um Cliente
	 * @return
	 */
	@RequestMapping("atualizaCliente")
	public String atualiza(Cliente cliente, HttpSession session)
	{
		Cliente onDB = clientesDao.pegaClientePorId(cliente.getId());
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		cliente.setDtInclusao(agora);
		cliente.setQuemIncluiu(onDB.getQuemIncluiu());
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		
		mensagem = clientesDao.altera(cliente);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " ["+cliente.getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarClientes";
	}
	
	/**
	 * Método para remover um Cliente
	 * @return
	 */
	@RequestMapping("removerCliente")
	public String remover(Long id, HttpSession session)
	{
		Cliente cliente = clientesDao.pegaClientePorId(id);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		
		mensagem = clientesDao.remove(cliente);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " ["+cliente.getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarClientes";
	}
	
}
