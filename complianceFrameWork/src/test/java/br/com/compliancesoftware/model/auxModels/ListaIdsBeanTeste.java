/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Testes unitário da classe ListaIdsBean
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class ListaIdsBeanTeste 
{

	/**
	 * Teste de criação de lista de Long com base em uma String de long's separados por vírgula.
	 */
	@Test
	public void testaConstrucaoDeListaLong() 
	{
		List<Long> lista = new ArrayList<Long>();
		lista.add(1L);
		lista.add(2L);
		
		List<Long> teste = ListaIdsBean.constroiDe("1,2");
		
		assertEquals(lista,teste);
	}

	/**
	 * Testa a construção de uma String de long's separados por virgula a partir de uma List<> de um bean qualquer que contenha o método getId()
	 */
	@Test
	public void testaConstrucaoDeString()
	{
		Teste teste1 = new Teste(1);
		Teste teste2 = new Teste(2);
		
		List<Teste> listaBean = new ArrayList<Teste>();
		listaBean.add(teste1);
		listaBean.add(teste2);
		
		String lista = "1,2";
		
		String teste = ListaIdsBean.extraiDe(listaBean);
		
		assertEquals(lista,teste);
	}
	
	public class Teste
	{
		private long id;
		
		public Teste(long id)
		{
			this.id = id;
		}
		
		public void setId(long id)
		{
			this.id = id;
		}
		
		public long getId()
		{
			return this.id;
		}
	}
	
}
