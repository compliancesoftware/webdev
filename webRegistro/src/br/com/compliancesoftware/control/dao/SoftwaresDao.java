package br.com.compliancesoftware.control.dao;

import java.util.List;

import br.com.compliancesoftware.model.Software;

/**
 * Interface que gerencia dados de softwares no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface SoftwaresDao 
{
	public String adiciona(Software software);
	public String remove(Long id);
	public List<Software> lista();
	public List<Software> listaPorNome(String nome);
	public Software getSoftwareById(Long id);
	public Software getSoftwareByNome(String nome);
	public void primeiroUso();
}
