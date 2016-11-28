package br.com.compliancesoftware.model.auxModels;

import br.com.compliancesoftware.model.Registro;

/**
 * Serve como source do REST service para acesso remoto aos registros (consulta de cliente via software)
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class RegistroREST 
{
	private String cliente;
	private String software;
	private String valor;
	private String validade;
	private String plano;
	private boolean ativo;
	
	public RegistroREST(Registro registro)
	{
		this.cliente = registro.getCliente().getNome();
		this.software = registro.getSoftware().getNome();
		this.valor = registro.getFmtValorComUnidade();
		this.validade = registro.getFmtValidade();
		int plano = registro.getPlano();
		if(plano == 1)
			this.plano = "Mensal";
		else if(plano == 3)
			this.plano = "Trimestral";
		else if(plano == 6)
			this.plano = "Semestral";
		else if(plano == 12)
			this.plano = "Anual";
		
		this.ativo = registro.getAtivo();
	}

	public void setPlano(int plano)
	{
		if(plano == 1)
			this.plano = "Mensal";
		else if(plano == 3)
			this.plano = "Trimestral";
		else if(plano == 6)
			this.plano = "Semestral";
		else if(plano == 12)
			this.plano = "Anual";
	}
	
	public String getPlano()
	{
		return this.plano;
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
