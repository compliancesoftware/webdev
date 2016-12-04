package br.com.compliancesoftware.view.Relatorios.Registros;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.auxModels.FMT;

/**
 * Bean de adaptação de cliente para ser impresso no relatório.
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class RegistroAdapter 
{
	private String cliente;
	private String software;
	private String valor;
	private String contato;
	private String email;
	private String responsavel;
	private String cadastradoEm;
	private String cadastradoPor;
	private String validade;
	private String ativo;
	private String situacao;
	private String observacoes;
	
	public RegistroAdapter(Registro registro)
	{
		this.cliente = registro.getCliente().getNome();
		this.software = registro.getSoftware().getNome();
		this.valor = registro.getFmtValorComUnidade();
		this.contato = registro.getCliente().getFmtContato();
		this.email = registro.getCliente().getEmail();
		this.responsavel = registro.getCliente().getResponsavel();
		this.cadastradoEm = registro.getFmtIncluido();
		this.cadastradoPor = registro.getQuemIncluiu().getNome();
		this.validade = registro.getFmtValidade();
		if(registro.getAtivo())
			this.ativo = "Sim";
		else
			this.ativo = "Não";
		this.situacao = getSituacao(registro.getValidade(), registro.getAtivo());
		this.observacoes = registro.getObservacoes();
	}
	
	private String getSituacao(Calendar validade, boolean ativo)
	{
		Calendar hoje = FMT.getHoje();
		
		if(ativo)
		{
			if(validade.after(hoje))
			{
				return "Válido até "+FMT.getStringFromCalendar(validade);
			}
			else
			{
				return "Erro: Registo ativo para validade ultrapassada: "+FMT.getStringFromCalendar(validade);
			}
		}
		else
		{
			if(validade.after(hoje))
			{
				return "Registro desativado por motivos jurídicos. Validade: "+FMT.getStringFromCalendar(validade);
			}
			else
			{
				return "Falta de pagamento: "+FMT.getStringFromCalendar(validade);
			}
		}
	}
	
	public String getCadastradoEm() {
		return cadastradoEm;
	}

	public String getCadastradoPor() {
		return cadastradoPor;
	}
	
	public String getContato() {
		return contato;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public String getCliente() {
		return cliente;
	}

	public String getSoftware() {
		return software;
	}

	public String getValor() {
		return valor;
	}

	public String getValidade() {
		return validade;
	}

	public String getAtivo() {
		return ativo;
	}

	public String getSituacao() {
		return situacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public static List<RegistroAdapter> listaDeRegistros(List<Registro> listaRegistro)
	{
		List<RegistroAdapter> beanCollection = new ArrayList<RegistroAdapter>();
	    for(Registro registro : listaRegistro)
	    {
	    	RegistroAdapter adapter = new RegistroAdapter(registro);
	    	beanCollection.add(adapter);
	    }
	    return beanCollection;
	}
}
