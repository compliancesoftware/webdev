package br.com.compliancesoftware.model.auxModels;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utilitário de envio de emails
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class EnviaEmail 
{
	private static final String username = "douglasf.filho@gmail.com";
	private static final String password = "$9916do@d";

	/**
	 * Envia o mesmo e-mail para todos da lista
	 * @param emails
	 */
	public static String envia(String emails, String assunto, String corpo)
	{
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			
			String lista = emails.replace("'", "").replace("[", "").replace("]", "");
			
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(lista));
			message.setSubject(assunto);
			message.setText(corpo);

			Transport.send(message);
			
			return "<strong>OK!</strong> E-mail enviado!";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "<strong>Erro!</strong> Impossível enviar os e-mails!<br>Verifique se o e-mail servidor está devidamente configurado e tente novamente.";
		}
	}
}
