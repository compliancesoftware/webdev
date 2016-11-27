package br.com.compliancesoftware.model.auxModels;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.compliancesoftware.model.Registro;

/**
 * Serve como source do REST service para acesso remoto aos registros (consulta de cliente via software)
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
@XStreamAlias("registro")
public class RegistroREST 
{
	private String cliente;
	private String software;
	private String valor;
	private String validade;
	private boolean ativo;
	
	public RegistroREST(Registro registro)
	{
		this.cliente = registro.getCliente().getNome();
		this.software = registro.getSoftware().getNome();
		this.valor = registro.getFmtValorComUnidade();
		this.validade = registro.getFmtValidade();
		this.ativo = registro.getAtivo();
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
