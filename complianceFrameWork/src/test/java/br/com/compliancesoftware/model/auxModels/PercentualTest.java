/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes da classe de Percentual
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class PercentualTest 
{

	@Test
	public void testaValorDoubleDoPercentual() 
	{
		Percentual percentual = new Percentual();
		percentual.setValor(100.00);
		
		assertEquals(100.00,percentual.getValor(),000.00);
	}

	@Test
	public void testaValorStringDoPercentual() 
	{
		Percentual percentual = new Percentual();
		percentual.setValor(100.00);
		
		assertEquals("100",percentual.getValorAsString());
	}
	
	@Test
	public void testaOverrideToString()
	{
		Percentual percentual = new Percentual();
		percentual.setValor(100.00);
		
		assertEquals("100",""+percentual);
	}
}
