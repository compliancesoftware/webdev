package br.com.compliancesoftware.control.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.control.dao.ClientesDao;
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.control.dao.RegistrosDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.graficos.GraficoClientes;
import br.com.compliancesoftware.model.graficos.GraficoMes;

/**
 * Controlador de requisições do site(projeto).
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class SystemController
{
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao perfisDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("clientesJPA")
	@Autowired
	private ClientesDao clientesDao;
	
	@Qualifier("registrosJPA")
	@Autowired
	private RegistrosDao registrosDao;
	
	private static String mensagem = null;
	private static Perfil logged = null;
	
	public static void setLogado(Perfil logado)
	{
		logged = logado;
	}
	
	public static void setMsg(String msg)
	{
		mensagem = msg;
	}
	
	/**
	 * Retorna à página principal.
	 * @param model
	 * @return
	 */
	@RequestMapping("home")
	public String home(Model model)
	{
		int alertas = alertasDao.conta();
		
		List<GraficoMes> gCadastradosMes = pegaCadastradosNoMes();
		List<GraficoMes> gRegistradosMes = pegaRegistradosNoMes();
		GraficoClientes clientes = pegaClientesAtrasadosEmDia();
		
		model.addAttribute("gCadastradosMes",gCadastradosMes);
		model.addAttribute("gRegistradosMes",gRegistradosMes);
		model.addAttribute("clientes",clientes);
		
		model.addAttribute("alertas",alertas);
		model.addAttribute("mensagem",mensagem);
		model.addAttribute("logged",logged);
		mensagem = null;
		return "index";
	}
	
	/**
	 * Faz a listagem dinamica de cadastrados no mês.
	 * @return
	 */
	private List<GraficoMes> pegaCadastradosNoMes()
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		int esteMes = hoje.get(Calendar.MONTH) + 1;
		List<GraficoMes> gCadastradosMes = new ArrayList<GraficoMes>();
		GraficoMes ponto = new GraficoMes();
		int i = 0;
		for(i = 0;i < esteMes;i++)
		{
			ponto = new GraficoMes();
			ponto.setMes(i);
			ponto.setValor(""+clientesDao.cadastradosMes(i));
			
			gCadastradosMes.add(ponto);
		}
		
		return gCadastradosMes;
	}
	
	/**
	 * Faz a listagem dinamica de registrados no mês.
	 * @return
	 */
	private List<GraficoMes> pegaRegistradosNoMes()
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		int esteMes = hoje.get(Calendar.MONTH) + 1;
		List<GraficoMes> gRegistradosMes = new ArrayList<GraficoMes>();
		GraficoMes ponto = new GraficoMes();
		int i = 0;
		for(i = 0;i < esteMes;i++)
		{
			ponto = new GraficoMes();
			ponto.setMes(i);
			ponto.setValor(""+registrosDao.listaRegistrados(i));
			
			gRegistradosMes.add(ponto);
		}
		
		return gRegistradosMes;
	}
	
	/**
	 * Auxilia ao gerar o gráfico de atrasados x em dia.
	 * @return
	 */
	private GraficoClientes pegaClientesAtrasadosEmDia()
	{
		GraficoClientes clientes = new GraficoClientes();
		clientes.setAtrasados(registrosDao.listaAtrasados());
		clientes.setEmDia(registrosDao.listaAtrasados());
		clientes.calcula();
		
		return clientes;
	}
	
	/**
	 * Método que retorna a tela de configuração de perfis
	 * @return
	 */
	@RequestMapping("gerenciarPerfis")
	public String cfgPerfis(HttpSession session, Model model) throws Exception
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		if(logado.getPermissao().equals(PerfisDao.ADM))
		{
			List<Perfil> listaPerfis = perfisDao.getLista();
			int alertas = alertasDao.conta();
			
			model.addAttribute("alertas",alertas);
			model.addAttribute("logged",logged);
			if(mensagem != null)
				model.addAttribute("mensagem",mensagem);
			mensagem = null;
			model.addAttribute("listaPerfis",listaPerfis);
			
			return "perfis/perfis";
		}
		else
		{
			return cfgPerfil(logado.getId(),session, model);
		}
	}
	
	/**
	 * Método que retorna a tela de configuração de perfil
	 * @return
	 */
	@RequestMapping("gerenciarPerfil")
	public String cfgPerfil(Long id, HttpSession session, Model model) throws Exception
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		if(logado.getPermissao().equals(PerfisDao.ADM))
		{
			Perfil perfil = perfisDao.getPerfilById(id);
			
			if(mensagem != null)
				model.addAttribute("mensagem",mensagem);
			model.addAttribute("perfilAlterar",perfil);
			model.addAttribute("logged",logged);
			mensagem = null;
			return "perfis/perfil";
		}
		else
		{
			Perfil perfil = perfisDao.getPerfilById(id);
			if(perfil.getId() == logado.getId())
			{
				if(mensagem != null)
					model.addAttribute("mensagem",mensagem);
				model.addAttribute("perfilAlterar",perfil);
				model.addAttribute("logged",logged);
				mensagem = null;
				return "perfis/perfil";
			}
			else
			{
				session.invalidate();
				return "forward:home";
			}
		}
	}
	
	/**
	 * Lista logs do sistema para Administrador
	 * @return
	 */
	@RequestMapping("logs")
	public String verLogs(HttpSession session, Model model) throws Exception
	{
		Perfil logado = (Perfil)session.getAttribute("logado");
		
		int alertas = alertasDao.conta();
		List<Log> lista = logsDao.lista(null, null);
		model.addAttribute("alertas",alertas);
		model.addAttribute("listaLogs",lista);
		model.addAttribute("logged",logado);
		return "logs/logs";
	}
	
}
