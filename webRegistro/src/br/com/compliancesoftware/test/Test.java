package br.com.compliancesoftware.test;

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
	private static boolean testaCodigo(String codigo)
	{
		return VerificaId.verifica(codigo);
	}
}
