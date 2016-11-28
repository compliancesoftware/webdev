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
 * Entidade registros no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "registros")
public class Registro 
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "cliente", nullable = false)
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "software", nullable = false)
	private Software software;
	
	@Column(name = "desconto", nullable = false)
	private double desconto;
	
	@Column(name = "valor", nullable = false)
	private double valor;

	@Column(name = "validade", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar validade;
	
	@Column(name = "incluido", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar incluido;
	
	@OneToOne
	@JoinColumn(name = "quem_incluiu", nullable = false)
	private Perfil quemIncluiu;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@Column(name = "observacoes")
	private String observacoes;
	
	@Column(name = "plano", nullable = false)
	private int plano;
	
	public int getPlano() {
		return plano;
	}
	
	public String getFmtPlano(){
		if(this.plano == 1)
		{
			return "Mensal";
		}
		else if(this.plano == 3)
		{
			return "Trimestral";
		}
		else if(this.plano == 6)
		{
			return "Semestral";
		}
		else
		{
			return "Anual";
		}
	}

	public void setPlano(int plano) {
		this.plano = plano;
	}

	/**
	 * @return Retorna incluido
	 */
	public Calendar getIncluido() {
		return this.incluido;
	}

	/**
	 * @param Nova configuração para incluido
	 */
	public void setIncluido(Calendar incluido) {
		this.incluido = incluido;
	}

	/**
	 * @return Retorna quem_incluiu
	 */
	public Perfil getQuemIncluiu() {
		return this.quemIncluiu;
	}

	/**
	 * @param Nova configuração para quem_incluiu
	 */
	public void setQuemIncluiu(Perfil quem_incluiu) {
		this.quemIncluiu = quem_incluiu;
	}
	
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
	 * @return Retorna cliente
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * @param Nova configuração para cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna software
	 */
	public Software getSoftware() {
		return this.software;
	}

	/**
	 * @param Nova configuração para software
	 */
	public void setSoftware(Software software) {
		this.software = software;
	}

	/**
	 * @return Retorna desconto
	 */
	public double getDesconto() {
		return this.desconto;
	}

	/**
	 * @param Nova configuração para desconto
	 */
	public void setDesconto(double desconto) {
		this.desconto = desconto;
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
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return Retorna validade
	 */
	public Calendar getValidade() {
		return this.validade;
	}
	
	public String getFmtValidade()
	{
		return new SimpleDateFormat("dd/MM/yyyy").format(this.validade.getTime());
	}

	/**
	 * @param Nova configuração para validade
	 */
	public void setValidade(Calendar validade) {
		this.validade = validade;
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
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
