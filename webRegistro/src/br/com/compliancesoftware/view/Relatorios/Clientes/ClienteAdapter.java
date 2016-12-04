package br.com.compliancesoftware.view.Relatorios.Clientes;

import java.util.ArrayList;
import java.util.List;

import br.com.compliancesoftware.model.Cliente;

/**
 * Bean de adaptação de cliente para ser impresso no relatório.
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class ClienteAdapter 
{
	private String nome;
	private String codigo;
	private String contato;
	private String email;
	private String responsavel;
	private String cadastradoEm;
	private String cadastradoPor;
	
	public ClienteAdapter(Cliente cliente)
	{
		this.nome = cliente.getNome();
		this.codigo = cliente.getFmtCodigo();
		this.contato = cliente.getFmtContato();
		this.email = cliente.getEmail();
		this.responsavel = cliente.getResponsavel();
		this.cadastradoEm = cliente.getFmtDtInclusao();
		this.cadastradoPor = cliente.getQuemIncluiu().getNome();
	}
	
	public String getCadastradoEm() {
		return cadastradoEm;
	}

	public String getCadastradoPor() {
		return cadastradoPor;
	}

	public String getNome() {
		return nome;
	}
	public String getCodigo() {
		return codigo;
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
	
	public static List<ClienteAdapter> listaDeClientes(List<Cliente> listaCliente)
	{
		List<ClienteAdapter> beanCollection = new ArrayList<ClienteAdapter>();
	    for(Cliente cliente : listaCliente)
	    {
	    	ClienteAdapter adapter = new ClienteAdapter(cliente);
	    	beanCollection.add(adapter);
	    }
	    return beanCollection;
	}
}
