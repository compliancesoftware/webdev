package br.com.compliancesoftware.model.auxModels;

import java.util.ArrayList;

/**
 * Ajudar a construir uma lista de double equivalente ao select de percentual para html
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class Percentual 
{
	private double valor;
	private String strvalor;
	
	public void setValor(double valor)
	{
		this.valor = valor;
		this.strvalor = ""+(int)valor;
	}
	
	public double getValor()
	{
		return this.valor;
	}
	
	public String getValorAsString()
	{
		return this.strvalor;
	}
	
	@Override
	public String toString()
	{
		return this.strvalor;
	}
	
	public static ArrayList<Percentual> getListaZeroACem()
	{
		ArrayList<Percentual> lista = new ArrayList<Percentual>();
		
		Percentual perc;
		for(double d = 0d;d < 100d; d += 1d)
		{
			perc = new Percentual();
			perc.setValor(d);
			lista.add(perc);
		}
		return lista;
	}
	
}
