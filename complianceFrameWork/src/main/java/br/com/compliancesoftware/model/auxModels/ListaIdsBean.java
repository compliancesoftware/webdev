package br.com.compliancesoftware.model.auxModels;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe que auxilia na extração de ids de uma lista de bean.
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class ListaIdsBean 
{
	/**
	 * Extrai uma lista de ids separados por virgula a partir da lista passada por parametro.
	 */
	public static String extraiDe(List<?> listaBean)
	{
		try
		{
			if(listaBean != null && listaBean.size() > 0)
			{
				List<String> lista = new ArrayList<String>();
				
				for(Object item:listaBean)
				{
					Method metodo = item.getClass().getDeclaredMethod("getId");
					if(metodo != null)
					{
						lista.add(""+metodo.invoke(item));
					}
					else
						return null;
				}
				
				String list = "";
				
				for(String id : lista)
				{
					list += ","+id;
				}
				list = list.substring(1);
				
				return list;
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Constroi a lista de ids a partir da String passada.
	 * @param lista
	 * @return
	 */
	public static List<Long> constroiDe(String lista)
	{
		try
		{
			StringTokenizer st = new StringTokenizer(lista,",");
			ArrayList<Long> list = new ArrayList<Long>();
			while(st.hasMoreTokens())
			{
				list.add(Long.parseLong(st.nextToken()));
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
