/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes unitários da classe EnviaEmail
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class EnviaEmailTeste 
{

	@Test
	public void testaEnvioDeEmail() 
	{
		assertEquals("<strong>OK!</strong> E-mail enviado!",EnviaEmail.envia("douglasf.filho@gmail.com", "JUnit teste", "testando com JUnit 4"));
	}

}
