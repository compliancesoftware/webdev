package br.com.compliancesoftware.model.auxModels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Serve como source do REST service para acesso remoto aos registros (consulta de cliente via software)
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class RegistroREST 
{
	private String cliente;
	private String software;
	private String valor;
	private String validade;
	private String plano;
	private boolean ativo;
	
	private static final String URL_CONNECTION = "http://localhost/webRegistro/consultaRegistro?cliente=";
	private static final String SOFTWARE = "webos";
	
	public void setPlano(int plano)
	{
		if(plano == 1)
			this.plano = "Mensal";
		else if(plano == 3)
			this.plano = "Trimestral";
		else if(plano == 6)
			this.plano = "Semestral";
		else if(plano == 12)
			this.plano = "Anual";
	}
	
	public void setPlanoAsString(String plano)
	{
		this.plano = plano;
	}
	
	public String getPlano()
	{
		return this.plano;
	}
	
	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public static RegistroREST unmarshall(String codigo)
	{
		try
		{
			URL add = new URL(URL_CONNECTION+codigo+"&software="+SOFTWARE);
			HttpURLConnection con = (HttpURLConnection)add.openConnection();
			InputStream content = con.getInputStream();
			
			XStream stream = new XStream(new DomDriver());
			stream.alias("registro", RegistroREST.class);
			
			RegistroREST reg = (RegistroREST)stream.fromXML(content);
			
			content.close();
			con.disconnect();
			
			return reg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
