package br.com.compliancesoftware.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;

import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.auxModels.FMT;
import br.com.compliancesoftware.model.auxModels.ListaIdsBean;
import br.com.compliancesoftware.model.auxModels.VerificaId;

/**
 * Classe de testes de ferramentas.
 * @author Compliance Software (By Douglas Fernandes) {@link douglasf.filho@gmail.com}
 *
 */
public class Test 
{

	public static void main(String[] args) 
	{
		String distancia = testaDistanciaEntreDoisPontosGoogleAPI();
		System.out.println("Distância: "+distancia);
	}

	/**
	 * Cria dois pontos pela google api e testa a conexão entre os dois.
	 * @return
	 */
	public static String testaDistanciaEntreDoisPontosGoogleAPI()
	{
		try
		{
			GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCNgo7zFt-FiFjZtuPzhuDz7VUu87kIFhU");
			context.setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
			
			String[] origins = new String[]{"-8.191422, -34.919100"};
			String[] destinations = new String[]{"-8.190086, -34.927242"};
			
			DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context , origins, destinations).await();
			return distanceMatrix.rows[0].elements[0].distance.humanReadable;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cria uma lista de ids separada por vírgula a partir do bean passado.
	 * @return
	 */
	public static String testaListaDeIdsAPartirDeListaBean()
	{
		Cliente cliente1 = new Cliente();
		cliente1.setId(1);
		cliente1.setNome("Teste1");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2);
		cliente2.setNome("Teste2");
		
		ArrayList<Cliente> listaBean = new ArrayList<Cliente>();
		listaBean.add(cliente1);
		listaBean.add(cliente2);
		
		return ListaIdsBean.extraiDe(listaBean);
	}
	
	/**
	 * Cria uma List<Long> de ids a partir da lista em forma de String
	 * @return
	 */
	public static void testaListaDeIdsAPartirDeListaString(String lista)
	{
		List<Long> list = ListaIdsBean.constroiDe(lista);
		for(Long item : list)
		{
			System.out.println("Id: "+item);
		}
	}
	
	/**
	 * Converte um Calendar com a data de hoje em uma String
	 * @return
	 */
	public static String testaConversaoDeHoje()
	{
		return FMT.getHojeAsString();
	}
	
	/**
	 * Método para validar o CNPJ ou o CPF.
	 * @param cnpj
	 * @return
	 */
	public static boolean testaCodigo(String codigo)
	{
		return VerificaId.verifica(codigo);
	}
	
	/**
	 * Imprime o conteúdo do filtro.
	 * @param filtro
	 */
	public static void testaFiltroRegistro(FiltroRegistro filtro)
	{
		System.out.println("Cliente: "+filtro.getCliente());
		System.out.println("Software: "+filtro.getSoftware());
		System.out.println("Valor: "+filtro.getValor());
		System.out.println("Desconto: "+filtro.getDesconto());
		System.out.println("Validade entre: "+filtro.getValidadeInicio());
		System.out.println("e: "+filtro.getValidadeFim());
		System.out.println("Ativo: "+filtro.getAtivo());
	}
}
