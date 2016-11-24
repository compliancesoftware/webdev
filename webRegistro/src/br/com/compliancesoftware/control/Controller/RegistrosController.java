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
import br.com.compliancesoftware.control.dao.RegistrosDao;
import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.Registro;

/**
 * Controller de registros (acess�vel apenas por perfis ADM)
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
@Transactional
@Controller
public class RegistrosController 
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
	
	@Qualifier("registrosJPA")
	@Autowired
	private RegistrosDao registrosDao;
	
	private String mensagem = null;
	
	/**
	 * Acesso � p�gina de gerenciamento de registros.
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("gerenciarRegistros")
	public String gerenciarRegistros(HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> N�o h� clientes cadastrados!");
			return "redirect:home";
		}
		else
		{
			int alertas = alertasDao.conta();
			model.addAttribute("alertas",alertas);
			
			Perfil logado = (Perfil)session.getAttribute("logado");
			model.addAttribute("logged",logado);
			
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
			
			List<Registro> listaRegistros = registrosDao.lista();
			model.addAttribute("listaRegistros",listaRegistros);
			
			return "registros/registros";
		}
	}
	
	/**
	 * M�todo que j� filtra os registros a serem visualizados para um determinado Cliente
	 * @return
	 */
	@RequestMapping("visualizarRegistros")
	public String visualizarRegistros(Long id, HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> N�o h� clientes cadastrados!");
			return "redirect:home";
		}
		else
		{
			List<Registro> registros = registrosDao.pegaRegistrosDeCliente(id);
			if(registros != null)
			{
				int alertas = alertasDao.conta();
				model.addAttribute("alertas",alertas);
				
				Perfil logado = (Perfil)session.getAttribute("logado");
				model.addAttribute("logged",logado);
				
				model.addAttribute("mensagem",mensagem);
				mensagem = null;
				
				model.addAttribute("listaRegistros",registros);
				
				return "registros/registros";
			}
			else
			{
				mensagem = "<strong>Erro!</strong> Este cliente n�o possui registros.";
				return "redirect:gerenciarRegistros";
			}
		}
	}
	
	/**
	 * Aplica filtro na pesquisa e retorna apenas a tabela, o foco � acessar este m�todo via Ajax
	 * @param filtro
	 * @return
	 */
	@RequestMapping("pesquisarRegistros")
	public String pesquisarRegistros(FiltroRegistro filtro, Model model)
	{
		List<Registro> listaRegistros = registrosDao.lista(filtro);
		model.addAttribute("listaRegistros",listaRegistros);
		
		return "registros/tabela";
	}
	
	/**
	 * M�todo que leva � p�gina de realiza��o de novo registro
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("cadastrarRegistro")
	public String paginaDeCadastro(HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> N�o h� clientes cadastrados!");
			return "redirect:home";
		}
		else
		{
			int alertas = alertasDao.conta();
			model.addAttribute("alertas",alertas);
			
			Perfil logado = (Perfil)session.getAttribute("logado");
			model.addAttribute("logged",logado);
			
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
			
			List<Registro> listaRegistros = registrosDao.lista();
			model.addAttribute("listaRegistros",listaRegistros);
			
			return "registros/cadastrar";
		}
	}
	
	/**
	 * Este m�todo vis ser usado numa requisi��o Ajax e retorna um selector de clientes ap�s a busca.
	 * @param pesquisa
	 * @param model
	 * @return
	 */
	@RequestMapping("pesquisarClienteParaRegistro")
	public String pesquisarClienteParaRegistro(String pesquisa, Model model)
	{
		List<Cliente> listaClientes = clientesDao.pesquisaCliente(pesquisa);
		model.addAttribute("listaClientes",listaClientes);
		
		return "regsitros/seletorclientes";
	}
	
	/**
	 * M�todo usado para realizar o novo registro.
	 * @param registro
	 * @param session
	 * @return
	 */
	@RequestMapping("cadastraRegistro")
	public String cadastraRegistro(Registro registro, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		
		registro.setQuemIncluiu(logado);
		registro.setIncluido(agora);
		registro.setAtivo(true);
		
		mensagem = registrosDao.adiciona(registro);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [Cliente: "+registro.getCliente().getNome()+" | Software: "+registro.getSoftware().getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarRegistros";
	}
	
	/**
	 * M�todo que leva � p�gina de atualiza��o de registro.
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("atualizarRegistro")
	public String atualizarRegistro(Long id, HttpSession session, Model model)
	{
		Registro registro = registrosDao.pegaRegistroPorId(id);
		model.addAttribute("registro", registro);
		
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		List<Registro> listaRegistros = registrosDao.lista();
		model.addAttribute("listaRegistros",listaRegistros);
		
		return "registros/atualizar";
	}
	
	/**
	 * M�todo usado para atualizar o registro de um determindo cliente.
	 * @param registro
	 * @param session
	 * @return
	 */
	@RequestMapping("atualizaRegistro")
	public String atualizaRegistro(Registro registro, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		
		registro.setQuemIncluiu(logado);
		registro.setIncluido(agora);
		
		mensagem = registrosDao.altera(registro);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [Cliente: "+registro.getCliente().getNome()+" | Id: "+registro.getId()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarRegistros";
	}
	
	/**
	 * M�todo usado para remover registros.
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("removerRegistro")
	public String removerRegistro(Long id, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		
		Registro registro = registrosDao.pegaRegistroPorId(id);
		
		mensagem = registrosDao.remove(registro);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [Cliente: "+registro.getCliente().getNome()+" | Id: "+registro.getId()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarRegistros";
	}
	
	/**
	 * Consulta o Status de um determinado registro para determinado software.
	 * retorna em forma de XML escrito atrav�s de um PrintWriter usando XStream
	 * @param cliente
	 * @param software
	 */
	@RequestMapping("consultaRegistro")
	public void consultaRegistro(String cliente, String software)
	{
		//TODO
	}
	
}
