package br.com.compliancesoftware.control.Model;

/**
 * Classe usada no envio de email de inscritos.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class Inscrever 
{
	private String subsemail;
	
	public void setSubsemail(String subsemail)
	{
		this.subsemail = subsemail;
	}
	
	public String getSubsemail()
	{
		return this.subsemail;
	}
}
