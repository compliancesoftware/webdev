/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

/**
 * Testes unitários da classe FMT
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class FMTTeste 
{

	@Test
	public void testaConverter() 
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis()); 
		
		/**
		 * TODO Cada vez que for testar, deve lembrar de colocar a String data igual a data de hoje.
		 */
		assertEquals("04/12/2016",FMT.getStringFromCalendar(hoje));
	}

	@Test
	public void testaDesconverter() 
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		hoje.set(Calendar.SECOND, 0);
		hoje.set(Calendar.MILLISECOND, 0);
		hoje.set(Calendar.MINUTE, 0);
		hoje.set(Calendar.HOUR_OF_DAY, 0);
		
		/**
		 * TODO Cada vez que for testar, deve lembrar de colocar a String data igual a data de hoje.
		 */
		assertEquals(hoje,FMT.getCalendarFromString("04/12/2016"));
	}
	
	@Test
	public void testaHojeComoString()
	{
		//TODO Lembre de sempre trocar esta da para a atual no mesmo formato.
		String hoje ="Domingo, 04 de Dezembro de 2016";
		String teste = FMT.getHojeAsString();
		
		assertEquals(hoje,teste);
	}
	
	@Test
	public void testaHojeCalendar()
	{
		Calendar hoje = Calendar.getInstance();
		//TODO lembre de trocar para a data atual no momento do teste.
		hoje.set(Calendar.DAY_OF_MONTH, 4);
		hoje.set(Calendar.MONTH,11);
		hoje.set(Calendar.YEAR, 2016);
		hoje.set(Calendar.HOUR_OF_DAY, 0);
		hoje.set(Calendar.MINUTE, 0);
		hoje.set(Calendar.SECOND, 0);
		hoje.set(Calendar.MILLISECOND, 0);
		
		Calendar teste = FMT.getHoje();
		
		assertEquals(hoje,teste);
	}
}
