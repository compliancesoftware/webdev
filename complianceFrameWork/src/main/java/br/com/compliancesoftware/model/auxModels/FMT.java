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
	
	/**
	 * Pega a data de hoje como uma String no formato como o exemplo "Sexta-feira, 02 de Dezembro de 2016".
	 * @return
	 */
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
	
	/**
	 * Retorna um Calendar com a data de hoje, as horas, minutos, segundos e milisegundos estarão zerados para que haja uma melhor comparação a nível de data.
	 * @return
	 */
	public static Calendar getHoje()
	{
		try
		{
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			String zerada = getStringFromCalendar(hoje);
			hoje = getCalendarFromString(zerada);
			return hoje;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
