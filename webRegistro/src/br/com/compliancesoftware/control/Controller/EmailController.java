package br.com.compliancesoftware.control.Controller;

import java.util.ArrayList;
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
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.auxModels.EnviaEmail;

/**
 * Implementação do uso da ferramente de notificações de clientes.
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
@Transactional
@Controller
public class EmailController 
{
	@Qualifier("clientesJPA")
	@Autowired
	private ClientesDao clientesDao;
	
	@Qualifier("registrosJPA")
	@Autowired
	private RegistrosDao registrosDao;
	
	@Qualifier("perfisJPA")
	@Autowired
	private PerfisDao perfisDao;
	
	@Qualifier("logsJPA")
	@Autowired
	private LogsDao logsDao;
	
	@Qualifier("alertasJPA")
	@Autowired
	private AlertasDao alertasDao;
	
	private String mensagem = null;
	
	/**
	 * Acesso a tela de envio de e-mail para o cliente escolhido.
	 * @param id
	 * @return
	 */
	@RequestMapping("enviarEmail")
	public String enviarEmail(String email, HttpSession session, Model model)
	{
		ArrayList<String> emails = new ArrayList<String>();
		emails.add(email);
		
		model.addAttribute("emails",emails);
		
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		return "email/enviar";
	}
	
	/**
	 * Acesso a tela de envio de e-mails para os clientes atrasados.
	 * @param id
	 * @return
	 */
	@RequestMapping("notificarAtrasados")
	public String notificarAtrasados(String email, HttpSession session, Model model)
	{
		List<Registro> lista = registrosDao.listaAtrasados();
		
		ArrayList<String> emails = new ArrayList<String>();
		for(Registro reg : lista)
		{
			emails.add(reg.getCliente().getEmail());
		}
		
		model.addAttribute("emails",emails);
		
		int alertas = alertasDao.conta();
		model.addAttribute("alertas",alertas);
		
		Perfil logado = (Perfil)session.getAttribute("logado");
		model.addAttribute("logged",logado);
		
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		
		return "email/enviar";
	}
	
	/**
	 * Método para enviar um e-mail.
	 * @param email
	 * @param assunto
	 * @param corpo
	 * @return
	 */
	@RequestMapping("enviaEmail")
	public String enviaEmail(String emails, String assunto, String corpo)
	{
		mensagem = EnviaEmail.envia(emails, assunto, corpo);
		SystemController.setMsg(mensagem);
		return "redirect:home";
	}
}
