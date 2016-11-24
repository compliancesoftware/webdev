package br.com.compliancesoftware.control.dao.filtros;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Usado na listagem de alertas
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class FiltroAlerta 
{
	private Calendar inicio = null;
	private Calendar fim = null;
	private boolean lido = false;
	
	private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat reverseFmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getInicio()
	{
		if(inicio != null)
			return fmt.format(inicio.getTime());
		return null;
	}
	
	public String getFim()
	{
		if(fim != null)
			return fmt.format(fim.getTime());
		return null;
	}
	
	public void setInicio(String inicio)
	{
		if(inicio == null || inicio.equals(null) || inicio.equals(""))//TODO verificar pq o filtro não filtra pelos lidos ou não
			this.inicio = null;
		else
		{
			this.inicio = Calendar.getInstance();
			try
			{
				this.inicio.setTime(reverseFmt.parse(inicio));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				this.inicio = null;
			}
		}
	}
	
	public void setFim(String fim)
	{
		if(fim == null || fim.equals(null) || fim.equals(""))
			this.fim = null;
		else
		{
			this.fim = Calendar.getInstance();
			try
			{
				this.fim.setTime(reverseFmt.parse(fim));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				this.fim = null;
			}
		}
	}
	
	/**
	 * @return Retorna inicio
	 */
	public Calendar getInicioCalendar() {
		return this.inicio;
	}
	/**
	 * @param Nova configuração para inicio
	 */
	public void setInicioCalendar(Calendar inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return Retorna fim
	 */
	public Calendar getFimCalendar() {
		return this.fim;
	}
	/**
	 * @param Nova configuração para fim
	 */
	public void setFimCalendar(Calendar fim) {
		this.fim = fim;
	}
	/**
	 * @return Retorna lido
	 */
	public boolean getLido() {
		return this.lido;
	}
	/**
	 * @param Nova configuração para lido
	 */
	public void setLido(boolean lido) {
		this.lido = lido;
	}
	
	
}
