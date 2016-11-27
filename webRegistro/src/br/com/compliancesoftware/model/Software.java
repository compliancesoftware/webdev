package br.com.compliancesoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade softwares no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "softwares")
public class Software
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;

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
	 * @return Retorna nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param Nova configuração para nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna valor
	 */
	public double getValor() {
		return this.valor;
	}
	
	public String getFmtValor()
	{
		String val = String.format("%.2f", this.valor);
		return val;
	}
	
	public String getFmtValorComUnidade()
	{
		String val = String.format("%.2f", this.valor);
		return "R$"+val;
	}

	/**
	 * @param Nova configuração para valor
	 */
	public void setValor(String valor) 
	{
		try
		{
			this.valor = Double.parseDouble(valor);
		}
		catch(Exception e)
		{
			this.valor = 0;
			e.printStackTrace();
		}
	}

	/**
	 * @return Retorna ativo
	 */
	public boolean getAtivo() {
		return this.ativo;
	}

	/**
	 * @param Nova configuração para ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
