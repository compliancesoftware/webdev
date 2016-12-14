package br.com.compliancesoftware.control.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.Model.Email;
import br.com.compliancesoftware.control.Model.Inscrever;

/**
 * Controlador de requisições do site(projeto).
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Controller
public class SystemController 
{
	private String mensagem = null;
	
	@Autowired
    private JavaMailSender mailSender;
	
	/**
	 * Método para enviar email para inscrever-se em nossa página.
	 * @return
	 */
	@RequestMapping("inscrever")
	public String inscrever(Inscrever inscrever)
	{
		String msg = null;
		String message = inscrever.getSubsemail();
		
		if(message != null && !message.equals(""))
		{
			String subject = "Novo inscrito!";
			String recipientAddress = "inscrevase.compliancesoftware@gmail.com";
			
			SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message);
	        
	        // Envia o e-mail
	        mailSender.send(email);
	        msg = "E-mail cadastrado com sucesso!";
		}
		
		mensagem = msg;
		return "redirect:home";
	}
	
	/**
	 * Método para enviar email para de contato.
	 * @return
	 */
	@RequestMapping("enviaEmail")
	public String enviaEmail(Email email)
	{
		String msg = null;
		String mailposter = email.getEmail();
		String message = "E-mail enviado pelo site ("+mailposter+")\n"+email.getComments();
		String nome = email.getName();
		
		if(message != null && !message.equals(""))
		{
			String subject = "Compliance Contato[De: "+nome+"]";
			String recipientAddress = "contato.compliancesoftware@gmail.com";
			
			SimpleMailMessage mailer = new SimpleMailMessage();
	        mailer.setTo(recipientAddress);
	        mailer.setSubject(subject);
	        mailer.setText(message);
	        
	        // Envia o e-mail
	        mailSender.send(mailer);
	        msg = "E-mail enviado com sucesso!";
		}
		
		mensagem = msg;
		return "redirect:home";
	}
	
	/**
	 * Retorna à página principal.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("mensagem",mensagem);
		mensagem = null;
		return "pages/index";
	}
	
	/**
	 * Retorna a home.
	 * @param model
	 * @return
	 */
	@RequestMapping("home")
	public String home2(Model model)
	{
		return home(model);
	}
	
}
