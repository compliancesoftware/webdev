package br.com.compliancesoftware.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade relacional do banco de dados conectada aos Logs de ações do sistema.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "logs")
public class Log
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "acao", nullable = false)
	private String acao;
	
	@OneToOne
	@JoinColumn(name = "autor", nullable = false)
	private Perfil autor;
	
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

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
	 * @return Retorna acao
	 */
	public String getAcao() {
		return this.acao;
	}

	/**
	 * @param Nova configuração para acao
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * @return Retorna ator
	 */
	public Perfil getAutor() {
		return this.autor;
	}
	
	/**
	 * @param Nova configuração para ator
	 */
	public void setAutor(Perfil autor) {
		this.autor = autor;
	}
	
	/**
	 * @return Retorna data
	 */
	public Calendar getData() {
		return this.data;
	}
	
	public String getFmtData() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return fmt.format(this.data.getTime());
	}

	/**
	 * @param Nova configuração para data
	 */
	public void setData(Calendar data) 
	{
		if(data == null)
		{
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(System.currentTimeMillis());
			this.data = now;
		}
		else
			this.data = data;
	}
	
	
}
