package br.com.compliancesoftware.control.Controller;

import java.util.ArrayList;
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
import br.com.compliancesoftware.control.dao.SoftwaresDao;
import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.Software;
import br.com.compliancesoftware.model.auxModels.Percentual;
import br.com.compliancesoftware.model.auxModels.RegistroAux;

/**
 * Controller de registros (acessível apenas por perfis ADM)
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
	
	@Qualifier("softwaresJPA")
	@Autowired
	private SoftwaresDao softwaresDao;
	
	private String mensagem = null;
	
	/**
	 * Acesso à página de gerenciamento de registros.
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("gerenciarRegistros")
	public String gerenciarRegistros(HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> Não há clientes cadastrados!");
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
			
			ArrayList<Percentual> listaPercentual = Percentual.getListaZeroACem();
			model.addAttribute("listaPercentual",listaPercentual);
			
			return "registros/registros";
		}
	}
	
	/**
	 * Método que já filtra os registros a serem visualizados para um determinado Cliente
	 * @return
	 */
	@RequestMapping("visualizarRegistros")
	public String visualizarRegistros(Long id, HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> Não há clientes cadastrados!");
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
				mensagem = "<strong>Erro!</strong> Este cliente não possui registros.";
				return "redirect:gerenciarRegistros";
			}
		}
	}
	
	/**
	 * Aplica filtro na pesquisa e retorna apenas a tabela, o foco é acessar este método via Ajax
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
	 * Método que leva à página de realização de novo registro
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("cadastrarRegistro")
	public String paginaDeCadastro(HttpSession session, Model model)
	{
		if(!clientesDao.temClienteCadastrado())
		{
			SystemController.setMsg("<strong>Erro!</strong> Não há clientes cadastrados!");
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
			
			List<Cliente> listaClientes = clientesDao.listaClientes();
			model.addAttribute("listaClientes",listaClientes);
			
			List<Software> listaSoftwares = softwaresDao.lista();
			model.addAttribute("listaSoftwares",listaSoftwares);
			
			ArrayList<Percentual> listaPercentual = Percentual.getListaZeroACem();
			model.addAttribute("listaPercentual",listaPercentual);
			
			return "registros/cadastrar";
		}
	}
	
	/**
	 * Este método visa ser usado numa requisição Ajax e retorna um selector de clientes após a busca.
	 * @param pesquisa
	 * @param model
	 * @return
	 */
	@RequestMapping("pesquisarClienteParaRegistro")
	public String pesquisarClienteParaRegistro(String pesquisa, Model model)
	{
		System.out.println(pesquisa);
		List<Cliente> listaClientes = clientesDao.pesquisaCliente(pesquisa);
		model.addAttribute("listaClientes",listaClientes);
		
		return "registros/cliente-selector";
	}
	
	/**
	 * Método usado para realizar o novo registro.
	 * @param registro
	 * @param session
	 * @return
	 */
	@RequestMapping("cadastraRegistro")
	public String cadastraRegistro(RegistroAux registro, HttpSession session)
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		Calendar agora = Calendar.getInstance();
		agora.setTimeInMillis(System.currentTimeMillis());
		
		Registro reg = new Registro();
		reg.setAtivo(true);
		Cliente cliente = clientesDao.pegaClientePorId(registro.getCliente());
		reg.setCliente(cliente);
		reg.setDesconto(registro.getDesconto());
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		reg.setIncluido(hoje);
		reg.setObservacoes(registro.getObservacoes());
		reg.setQuemIncluiu(logado);
		Software software = softwaresDao.getSoftwareByNome(registro.getSoftware());
		reg.setSoftware(software);
		reg.setValidade(registro.getValidade());
		reg.setValor(registro.getValor());
		
		mensagem = registrosDao.adiciona(reg);
		if(mensagem.contains(">OK"))
		{
			Log log = new Log();
			log.setAcao(mensagem + " [Cliente: "+reg.getCliente().getNome()+" | Software: "+reg.getSoftware().getNome()+"]");
			log.setAutor(logado);
			log.setData(null);
			logsDao.adiciona(log);
		}
		
		return "redirect:gerenciarRegistros";
	}
	
	/**
	 * Método que leva à página de atualização de registro.
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("atualizarRegistro")
	public String atualizarRegistro(Long id, HttpSession session, Model model)//TODO fazer view e as views de controle e cadastro de software
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
	 * Método usado para atualizar o registro de um determindo cliente.
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
	 * Método usado para remover registros.
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
	 * retorna em forma de XML escrito através de um PrintWriter usando XStream
	 * @param cliente
	 * @param software
	 */
	@RequestMapping("consultaRegistro")
	public void consultaRegistro(String cliente, String software)
	{
		//TODO
	}
	
}
