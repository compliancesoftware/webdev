package br.com.compliancesoftware.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model entidade alertas do banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "alertas")
public class Alerta implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7000620691047259739L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "mensagem", nullable = false)
	private String mensagem;
	
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	@Column(name = "visto", nullable = false)
	private boolean visto;
	
	/**
	 * @return Retorna id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param Nova configuração para id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Retorna mensagem
	 */
	public String getMensagem() {
		return this.mensagem;
	}

	/**
	 * @param Nova configuração para mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return Retorna data
	 */
	public Calendar getData() {
		return this.data;
	}
	
	public String getFmtData()
	{
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return fmt.format(this.data.getTime());
	}

	/**
	 * @param Nova configuração para data
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	/**
	 * @return Retorna visto
	 */
	public boolean getVisto() {
		return this.visto;
	}

	/**
	 * @param Nova configuração para visto
	 */
	public void setVisto(boolean visto) {
		this.visto = visto;
	}
}
