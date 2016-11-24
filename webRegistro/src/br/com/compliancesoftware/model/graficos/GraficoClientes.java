package br.com.compliancesoftware.model.graficos;

import java.io.Serializable;

/**
 * Auxiliar na gera��o do gr�fico de clientes atrasados x clientes em dia.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class GraficoClientes implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5089076457563803678L;
	
	private int atrasados = 0;
	private int emDia = 0;
	
	private String atrasadosPercentual = "0.00";
	private String emDiaPercentual = "0.00";
	/**
	 * @return Retorna atrasados
	 */
	public int getAtrasados() {
		return this.atrasados;
	}
	/**
	 * @param Nova configura��o para atrasados
	 */
	public void setAtrasados(int atrasados) {
		this.atrasados = atrasados;
	}
	/**
	 * @return Retorna emDia
	 */
	public int getEmDia() {
		return this.emDia;
	}
	/**
	 * @param Nova configura��o para emDia
	 */
	public void setEmDia(int emDia) {
		this.emDia = emDia;
	}
	/**
	 * @return Retorna atrasadosPercentual
	 */
	public String getAtrasadosPercentual() {
		return this.atrasadosPercentual;
	}
	/**
	 * @param Nova configura��o para atrasadosPercentual
	 */
	public void setAtrasadosPercentual(String atrasadosPercentual) {
		this.atrasadosPercentual = atrasadosPercentual;
	}
	/**
	 * @return Retorna emDiaPercentual
	 */
	public String getEmDiaPercentual() {
		return this.emDiaPercentual;
	}
	/**
	 * @param Nova configura��o para emDiaPercentual
	 */
	public void setEmDiaPercentual(String emDiaPercentual) {
		this.emDiaPercentual = emDiaPercentual;
	}
	
	public void calcula()
	{
		double atrasados = (double)this.atrasados;
		double emDia = (double)this.emDia;
		
		double total = atrasados + emDia;
		
		double pAtrasados = (atrasados/total)*100.00;
		double pEmDia = (emDia/total)*100.00;
		
		this.atrasadosPercentual = String.format("%.2f", pAtrasados);
		this.emDiaPercentual = String.format("%.2f", pEmDia);
		
	}
}
