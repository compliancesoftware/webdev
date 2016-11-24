package br.com.compliancesoftware.control.dao;

import java.util.List;

import br.com.compliancesoftware.model.Cliente;

/**
 * Interface que gerencia dados de clientes no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface ClientesDao 
{
	public String adiciona(Cliente cliente);
	public String remove(Cliente cliente);
	public String altera(Cliente cliente);
	public Cliente pegaClientePorId(Long id);
	public List<Cliente> listaClientes();
	public List<Cliente> pesquisaCliente(String pesquisa);//Pesquisa por nome, codigo, contato, email e responsavel
	public int cadastradosMes(int mes);
	public boolean temClienteCadastrado();
}
