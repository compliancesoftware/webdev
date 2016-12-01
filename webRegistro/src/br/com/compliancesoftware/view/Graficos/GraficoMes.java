package br.com.compliancesoftware.view.Graficos;

import java.io.Serializable;

/**
 * Ajuda a montar o gráfico linear de valor x mês
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class GraficoMes implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7018097228105891314L;
	
	public static final String JANEIRO = "2001-01-01";
	public static final String FEVEREIRO = "2001-02-01";
	public static final String MARCO = "2001-03-01";
	public static final String ABRIL = "2001-04-01";
	public static final String MAIO = "2001-05-01";
	public static final String JUNHO = "2001-06-01";
	public static final String JULHO = "2001-07-01";
	public static final String AGOSTO = "2001-08-01";
	public static final String SETEMBRO = "2001-09-01";
	public static final String OUTUBRO = "2001-10-01";
	public static final String NOVEMBRO = "2001-11-01";
	public static final String DEZEMBRO = "2001-12-01";
	
	private String mes = "2001-01-01";
	private String valor = "0";
	/**
	 * @return Retorna mes
	 */
	public String getMes() {
		return this.mes;
	}
	/**
	 * @param Nova configuração para mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	/**
	 * Nova configuração para mes
	 * @param mes
	 */
	public void setMes(int mes){
		if(mes < 0 || mes > 12)
			mes = 0;
		
		String[] meses = new String[12];
		meses[0] = JANEIRO;
		meses[1] = FEVEREIRO;
		meses[2] = MARCO;
		meses[3] = ABRIL;
		meses[4] = MAIO;
		meses[5] = JUNHO;
		meses[6] = JULHO;
		meses[7] = AGOSTO;
		meses[8] = SETEMBRO;
		meses[9] = OUTUBRO;
		meses[10] = NOVEMBRO;
		meses[11] = DEZEMBRO;
		
		this.mes = meses[mes];
	}
	/**
	 * @return Retorna valor
	 */
	public String getValor() {
		return this.valor;
	}
	/**
	 * @param Nova configuração para valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
