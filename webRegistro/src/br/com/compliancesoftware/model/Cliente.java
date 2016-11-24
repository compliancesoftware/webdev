package br.com.compliancesoftware.model;

import java.io.Serializable;
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
 * Entidade clientes no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1414405737761987666L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Column(name = "codigo", nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "contato", nullable = false)
	private String contato;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "responsavel", nullable = false)
	private String responsavel;
	
	@Column(name = "endereco", nullable = false)
	private String endereco;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Column(name = "dt_inclusao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtInclusao;
	
	@OneToOne
	@JoinColumn(name = "quem_incluiu", nullable = false)
	private Perfil quemIncluiu;
	
	/**
	 * @return Retorna dtInclusao
	 */
	public Calendar getDtInclusao() {
		return this.dtInclusao;
	}
	
	public String getFmtDtInclusao()
	{
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.dtInclusao.getTime());
	}

	/**
	 * @param Nova configuração para dtInclusao
	 */
	public void setDtInclusao(Calendar dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	/**
	 * @return Retorna quemIncluiu
	 */
	public Perfil getQuemIncluiu() {
		return this.quemIncluiu;
	}

	/**
	 * @param Nova configuração para quemIncluiu
	 */
	public void setQuemIncluiu(Perfil quemIncluiu) {
		this.quemIncluiu = quemIncluiu;
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
	 * @return Retorna codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * @return Retorna codigo
	 */
	public String getFmtCodigo()
	{
		String cod = this.codigo;
		if(cod.length() == 11)//CPF
		{
			cod = ""+cod.charAt(0)+cod.charAt(1)+cod.charAt(2)+"."+cod.charAt(3)+cod.charAt(4)+cod.charAt(5)+"."+cod.charAt(6)+cod.charAt(7)+cod.charAt(8)+"-"+cod.charAt(9)+cod.charAt(10);
		}
		else if(cod.length() == 14)//CNPJ
		{
			cod = ""+cod.charAt(0)+cod.charAt(1)+"."+cod.charAt(2)+cod.charAt(3)+cod.charAt(4)+"."+cod.charAt(5)+cod.charAt(6)+cod.charAt(7)+"/"+cod.charAt(8)+cod.charAt(9)+cod.charAt(10)+cod.charAt(11)+"-"+cod.charAt(12)+cod.charAt(13);
		}
		return cod;
	}

	/**
	 * @param Nova configuração para codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna contato
	 */
	public String getContato() {
		return this.contato;
	}

	/**
	 * @return Retorna contato
	 */
	public String getFmtContato()
	{
		String c = this.contato;
		if(c.length() == 11)
		{
			c = "("+c.charAt(0)+c.charAt(1)+")"+c.charAt(2)+" "+c.charAt(3)+c.charAt(4)+c.charAt(5)+c.charAt(6)+"-"+c.charAt(7)+c.charAt(8)+c.charAt(9)+c.charAt(10);
		}
		else if(c.length() == 10)
		{
			c = "("+c.charAt(0)+c.charAt(1)+")"+c.charAt(2)+c.charAt(3)+c.charAt(4)+c.charAt(5)+"-"+c.charAt(6)+c.charAt(7)+c.charAt(8)+c.charAt(9);
		}
		return c;
	}
	
	/**
	 * @param Nova configuração para contato
	 */
	public void setContato(String contato) {
		this.contato = contato;
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
	 * @return Retorna responsavel
	 */
	public String getResponsavel() {
		return this.responsavel;
	}

	/**
	 * @param Nova configuração para responsavel
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return Retorna endereco
	 */
	public String getEndereco() {
		return this.endereco;
	}

	/**
	 * @param Nova configuração para endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna bairro
	 */
	public String getBairro() {
		return this.bairro;
	}

	/**
	 * @param Nova configuração para bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return Retorna cidade
	 */
	public String getCidade() {
		return this.cidade;
	}

	/**
	 * @param Nova configuração para cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return Retorna estado
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * @param Nova configuração para estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return Retorna cep
	 */
	public String getCep() {
		return this.cep;
	}

	/**
	 * @param Nova configuração para cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
}
