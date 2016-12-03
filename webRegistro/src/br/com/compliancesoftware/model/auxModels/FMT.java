/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Ajuda a formatar e parsear datas
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class FMT 
{
	private static final String format = "dd/MM/yyyy";
	
	/**
	 * Método pra criar um Calendar a partir de uma String
	 * @param data
	 * @return
	 */
	public static Calendar getCalendarFromString(String data)
	{
		try
		{
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			Calendar date = Calendar.getInstance();
			date.setTime(fmt.parse(data));
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Método para criar uma String de data a partir de um Calendar
	 * @param calendar
	 * @return
	 */
	public static String getStringFromCalendar(Calendar calendar)
	{
		try
		{
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			String date = fmt.format(calendar.getTime());
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getHojeAsString()
	{
		try
		{
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			SimpleDateFormat fmt = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");
			String date = fmt.format(hoje.getTime());
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
