package br.com.compliancesoftware.control.dao.filtros;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Auxilio na listagem de registros
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class FiltroRegistro 
{
	public static final int AMBOS = 2;
	public static final int ATIVOS = 1;
	public static final int DESATIVADOS = 0;
	
	private String cliente = null;
	
	private String software = null;
	
	private double desconto = 0;
	
	private double valor = 0;

	private Calendar validadeInicio = null;
	private Calendar validadeFim = null;
	
	private int ativo = AMBOS;
	
	/**
	 * @return Retorna ativo
	 */
	public int getAtivo() {
		return this.ativo;
	}
	/**
	 * @param Nova configuração para ativo
	 */
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	/**
	 * @return Retorna cliente
	 */
	public String getCliente() {
		return this.cliente;
	}
	/**
	 * @param Nova configuração para cliente
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Retorna software
	 */
	public String getSoftware() {
		return this.software;
	}
	/**
	 * @param Nova configuração para software
	 */
	public void setSoftware(String software) {
		this.software = software;
	}
	/**
	 * @return Retorna desconto
	 */
	public double getDesconto() {
		return this.desconto;
	}
	/**
	 * @param Nova configuração para desconto
	 */
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	/**
	 * @return Retorna valor
	 */
	public double getValor() {
		return this.valor;
	}
	/**
	 * @param Nova configuração para valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna validadeInicio
	 */
	public Calendar getValidadeInicio() {
		return this.validadeInicio;
	}
	/**
	 * @param Nova configuração para validadeInicio
	 */
	public void setValidadeInicio(String validadeInicio) {
		try
		{
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar data = Calendar.getInstance();
			data.setTime(fmt.parse(validadeInicio));
			this.validadeInicio = data;
		}
		catch(Exception e)
		{
			this.validadeInicio = null;
			e.printStackTrace();
		}
	}
	/**
	 * @return Retorna validadeFim
	 */
	public Calendar getValidadeFim() {
		return this.validadeFim;
	}
	/**
	 * @param Nova configuração para validadeFim
	 */
	public void setValidadeFim(String validadeFim) {
		try
		{
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar data = Calendar.getInstance();
			data.setTime(fmt.parse(validadeFim));
			this.validadeFim = data;
		}
		catch(Exception e)
		{
			this.validadeFim = null;
			e.printStackTrace();
		}
	}
	
}
