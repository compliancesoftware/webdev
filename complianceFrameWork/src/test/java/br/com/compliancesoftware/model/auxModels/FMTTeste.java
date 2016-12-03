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
		
		assertEquals("02/12/2016",FMT.getStringFromCalendar(hoje));
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
		
		assertEquals(hoje,FMT.getCalendarFromString("02/12/2016"));
	}
	
}
