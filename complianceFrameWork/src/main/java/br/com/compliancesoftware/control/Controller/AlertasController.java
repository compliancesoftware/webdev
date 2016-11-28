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
import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.control.dao.filtros.FiltroAlerta;
import br.com.compliancesoftware.model.Alerta;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;

/**
 * Controller para alertas do sistema.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Transactional
@Controller
public class AlertasController 
{
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	private static String mensagem = null;
	
	@RequestMapping("alertas")
	public String alertas(Model model, HttpSession session)
	{
		Perfil logged = (Perfil)session.getAttribute("logado");
		
		int alertas = alertasDao.conta();
		List<Alerta> listaAlertas = alertasDao.lista(null, null);
		
		model.addAttribute("alertas",alertas);
		model.addAttribute("logged",logged);
		model.addAttribute("listaAlertas", listaAlertas);
		if(mensagem != null)
		{
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
		}
		
		return "alertas/alertas";
	}
	
	@RequestMapping("alertasFiltro")
	public String alertasFiltro(FiltroAlerta filtro, Model model, HttpSession session)
	{
		Perfil logged = (Perfil)session.getAttribute("logado");
		
		int alertas = alertasDao.conta();
		List<Alerta> listaAlertas = alertasDao.listaPorVisibilidade(filtro.getInicioCalendar(), filtro.getFimCalendar(), filtro.getLido());
		
		model.addAttribute("alertas",alertas);
		model.addAttribute("logged",logged);
		model.addAttribute("listaAlertas", listaAlertas);
		if(mensagem != null)
		{
			model.addAttribute("mensagem",mensagem);
			mensagem = null;
		}
		
		return "alertas/alertas";
	}
	
	@RequestMapping("atualizaVisibilidadeAlerta")
	public String atualizaVisibilidadeAlerta(Long id, HttpSession session)
	{
		Alerta alerta = alertasDao.getAlerta(id);
		alerta.setVisto(!alerta.getVisto());
		mensagem = alertasDao.altera(alerta);
		
		if(mensagem.contains(">OK"))
		{
			Perfil logado = (Perfil)session.getAttribute("logado");
			Log log = new Log();
			log.setAcao(mensagem + " [Id: "+id+"]");
			log.setAutor(logado);
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			log.setData(hoje);
			
			logsDao.adiciona(log);
		}
		
		return "redirect:alertas";
	}
}
