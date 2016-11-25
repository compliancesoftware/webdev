package br.com.compliancesoftware.test;

import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.auxModels.VerificaId;

/**
 * Classe de testes de ferramentas.
 * @author Compliance Software (By Douglas Fernandes) {@link douglasf.filho@gmail.com}
 *
 */
public class Test 
{

	public static void main(String[] args) 
	{
		System.out.println(testaCodigo("07.788.452/0002-00"));
	}

	/**
	 * Método para validar o CNPJ ou o CPF.
	 * @param cnpj
	 * @return
	 */
	public static boolean testaCodigo(String codigo)
	{
		return VerificaId.verifica(codigo);
	}
	
	/**
	 * Imprime o conteúdo do filtro.
	 * @param filtro
	 */
	public static void testaFiltroRegistro(FiltroRegistro filtro)
	{
		System.out.println("Cliente: "+filtro.getCliente());
		System.out.println("Software: "+filtro.getSoftware());
		System.out.println("Valor: "+filtro.getValor());
		System.out.println("Desconto: "+filtro.getDesconto());
		System.out.println("Validade entre: "+filtro.getValidadeInicio());
		System.out.println("e: "+filtro.getValidadeFim());
		System.out.println("Ativo: "+filtro.getAtivo());
	}
}
