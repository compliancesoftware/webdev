/**
 * 
 */
package br.com.compliancesoftware.control.dao;

import java.util.Calendar;

import br.com.compliancesoftware.model.Empresa;

/**
 * Interface de acesso as informa��es de empresa.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public interface EmpresaDao 
{
	/**
	 * Verifica se � o primeiro acesso ao sistema.
	 * @return
	 */
	public boolean primeiroAcesso();
	
	/**
	 * Verifica qual a data da proxima verificacao.
	 * @return
	 */
	public Calendar getProximaVerificacao();
	
	/**
	 * M�todo para pegar o CPF ou CNPJ da empresa cadastrada.
	 * @return
	 */
	public String getCodigo();
	
	/**
	 * Cria a empresa no banco de dados em caso de primeiro acesso.
	 * @param empresa
	 */
	public String criaEmpresa(Empresa empresa);
	
	/**
	 * Redefine a data de verifica��o.
	 * @param data
	 */
	public void redefineDataVerificacao(String data);
}
