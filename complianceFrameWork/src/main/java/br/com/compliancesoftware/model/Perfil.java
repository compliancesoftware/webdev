package br.com.compliancesoftware.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.binary.Hex;

import br.com.compliancesoftware.model.auxModels.Hunter;

/**
 * Classe resposnável por facilitar o trâmite de dados de perfis de usuários para acesso.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name="perfis")
public class Perfil
{
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="nome", nullable=false, unique=true)
	private String nome;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="contato", nullable=false, unique=true)
	private String contato;
	
	@Column(name="foto", length=15000000, nullable=false)//Tamanho máximo de 15Mb
	private byte[] foto;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Column(name="permissao", nullable=false)
	private String permissao;/*Administrador, Comum, Desativado */
	
	@Column(name="ult_acesso", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ult_acesso;
	
	@Column(name="acesso", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar acesso;

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
	 * @return Retorna contato
	 */
	public String getContato() {
		return this.contato;
	}

	/**
	 * @param Nova configuração para contato
	 */
	public void setContato(String contato) {
		this.contato = contato;
	}

	/**
	 * @return Retorna foto
	 */
	public byte[] getFoto() {
		return this.foto;
	}

	/**
	 * @param Nova configuração para foto
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	/**
	 * Define a foto padrão caso a foto seja nula.
	 */
	public void setDefaultFoto() throws Exception
	{
		String hexImage = "89504E470D0A1A0A0000000D49484452000000C0000000C0080000000077B733DB00000239494441547801EDDA090AEB201446E1EE7F4F3F820409489080040948E84ADE3CCFF199780BE7ACC0AF73AFF7F17CF1000000602E0000000000000000000000000000000000000000000C08000000000000000000000000FB1A833E15E2BABF16608B4E3FE4E2F62A802379FD329F8E570024A7DFE6927940F1FA63BED8062CFA6B8B61409DF40F4DD52A6077FAA7DC6E13B03BE97EC1A3FFF95B040600D5E944AE9A034C3AD5640DB0E8648B2D40D1E98A2980D7E9BC25405243C90EE0706AC81D6600494D253300AFA6BC15C0A6C6362380A8C6A211805363CE06605773BB09C0AAE6561380A8E6A209405073C10440ED09C0F301E000C04B08407B7C91F153E23F7FCCF1739A3F341DFE52F2A79EB14AEBE75062B4C870B7D7789D0B0EAE98DA2FF9B866E5A2BB7DD580650FD66DDA179E583963E9AF7DED92C557568F6D05000000000000000000C05400009492D21C267D5308734AA59807EC3905AF3FE443CABB4D40CD4BD03F16965C4D016A8E5E27F331571B806DF16ACC2FDB68C0169DDAEB316D798C3C7D0F4333A026AF6EF9546F0694599D9BCB8D803CE982A67C13207B5D94CF3700CAA40B9BCAC5803AEBE2E67A256075BA3CB75E0638826E291CD7008AD34DB9720520EBC6727F40D4ADC5DE80A89B8B7D01AB6E6FED09D834A0AD1FE0701A903BBA01660D69EE05281A54E904081A54E803A81A56ED025835ACB50B206858A10BC06958AE0B4003EB01385E1D500000000000000000000000000000000000000000000000000000000000000000C0D7DE02BAA0BAA278A4C57C0000000049454E44AE426082";
		char[] hex = new char[hexImage.length()];
		for(int i = 0;i < hexImage.length();i++)
		{
			hex[i] = hexImage.charAt(i);
		}
		byte[] data = Hex.decodeHex(hex);
		this.foto = data;
	}

	/**
	 * @return Retorna senha
	 */
	public String getSenha() 
	{
		return Hunter.hunted(this.senha);
	}

	/**
	 * @param Nova configuração para senha
	 */
	public void setSenha(String senha) 
	{
		this.senha = Hunter.hunt(senha);
	}

	/**
	 * @return Retorna permissao
	 */
	public String getPermissao() {
		return this.permissao;
	}

	/**
	 * @param Nova configuração para permissao
	 */
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	/**
	 * @return Retorna ult_acesso
	 */
	public Calendar getUlt_acesso() {
		return this.ult_acesso;
	}

	public String getUltAcesso()
	{
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return fmt.format(this.ult_acesso.getTime());
	}
	
	/**
	 * @param Nova configuração para ult_acesso
	 */
	public void setUlt_acesso(Calendar ult_acesso) {
		this.ult_acesso = ult_acesso;
	}

	/**
	 * @return Retorna acesso
	 */
	public Calendar getAcesso() {
		return this.acesso;
	}
	
	/**
	 * @param Nova configuração para acesso
	 */
 	public void setAcesso(Calendar acesso) {
		this.acesso = acesso;
	}
	
	
	
}
