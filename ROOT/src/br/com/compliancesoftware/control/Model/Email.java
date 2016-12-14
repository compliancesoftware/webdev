package br.com.compliancesoftware.control.Model;

/**
 * Model usado no envio de emails.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class Email 
{
	private String name;
	private String email;
	private String comments;
	/**
	 * @return Retorna name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param Nova configuração para name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Retorna email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param Nova configuração para email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Retorna comments
	 */
	public String getComments() {
		return this.comments;
	}
	/**
	 * @param Nova configuração para comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
