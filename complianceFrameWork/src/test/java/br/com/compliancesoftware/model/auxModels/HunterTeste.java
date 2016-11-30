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
		assertEquals("MTE2fDMyfC03MHw3OXwtNTh8MzF8NTd8MTAwfC00NXw5NXwtNnwxNnwtNTN8LTEwN3wxMjZ8LTEx",Hunter.hunt("28/12/2016"));
	}

	@Test
	public void testaSeDesconverteCorretamente()
	{
		assertEquals("28/12/2016",Hunter.hunted("MTE2fDMyfC03MHw3OXwtNTh8MzF8NTd8MTAwfC00NXw5NXwtNnwxNnwtNTN8LTEwN3wxMjZ8LTEx"));
	}
	
}
