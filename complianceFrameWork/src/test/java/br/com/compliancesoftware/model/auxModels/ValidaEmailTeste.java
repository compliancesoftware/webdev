/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes de unidade da classe ValidaEmail
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class ValidaEmailTeste 
{

	@Test
	public void testaEmailValido() 
	{
		String email = "douglasf.filho@gmail.com";
		assertTrue(ValidaEmail.isValido(email));
	}

	@Test
	public void testaEmailInvalido() 
	{
		String email = "douglasf.filhogmail.com";
		assertFalse(ValidaEmail.isValido(email));
	}
	
}
