/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes de unidade da classe VerificaId
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class VerificaIdTeste 
{

	@Test
	public void testaCPFValido() 
	{
		assertTrue(VerificaId.verifica("08468331406"));
		assertTrue(VerificaId.verifica("084.683.314-06"));
	}

	@Test
	public void testaCNPJValido() 
	{
		assertTrue(VerificaId.verifica("07788452000200"));
		assertTrue(VerificaId.verifica("07.788.452/0002-00"));
	}
}
