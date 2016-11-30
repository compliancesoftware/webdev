package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes de unidade da classe Hunter
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class HunterTeste 
{

	@Test
	public void testaSeConverteCorretamente() 
	{
		assertEquals("MnwzNHwxMTZ8LTE1fDI5fC05MnwyNHw5N3w1N3wtOTh8MzJ8MTA0fC02MXwtODF8MjF8MTI1",Hunter.hunt(""));
	}

	@Test
	public void testaSeDesconverteCorretamente()
	{
		assertEquals("",Hunter.hunted("MnwzNHwxMTZ8LTE1fDI5fC05MnwyNHw5N3w1N3wtOTh8MzJ8MTA0fC02MXwtODF8MjF8MTI1"));
	}
	
}
