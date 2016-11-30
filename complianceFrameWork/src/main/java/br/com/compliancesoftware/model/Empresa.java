/**
 * 
 */
package br.com.compliancesoftware.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.compliancesoftware.model.auxModels.FMT;
import br.com.compliancesoftware.model.auxModels.Hunter;

/**
 * Entidade que contém as principais informaçoes da empresa a nível de controle de licença
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Entity
@Table(name = "empresa")
public class Empresa 
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Column(name = "codigo", nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "proxima_verificacao", nullable = false)
	private String proximaVerificacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Calendar getProximaVerificacao() 
	{
		try
		{
			String data = Hunter.hunted(this.proximaVerificacao);
			return FMT.getCalendarFromString(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getFmtProximaVerificacao(){
		return FMT.getStringFromCalendar(getProximaVerificacao());
	}

	public void setProximaVerificacao(String proximaVerificacao) 
	{
		try
		{
			this.proximaVerificacao = Hunter.hunt(proximaVerificacao);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
