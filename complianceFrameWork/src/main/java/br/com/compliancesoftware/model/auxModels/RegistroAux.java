package br.com.compliancesoftware.model.auxModels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Auxilia na passagem de parametros da camada de view para o controller
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class RegistroAux 
{
	private long id;
	
	private long cliente;
	
	private String software;
	
	private double desconto;
	
	private double valor;

	private boolean ativo;
	
	private String observacoes;
	
	private Calendar validade;
	
	private int plano; //varia de 0-mensal | 1-trimestral | 2-semestral | 3-anual
	
	public int getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		try
		{
			this.plano = Integer.parseInt(plano);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.plano = 1;
		}
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setValidade(String data)
	{
		try
		{
			Calendar val = Calendar.getInstance();
			val.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(data));
			this.validade = val;
		}
		catch(Exception e)
		{
			this.validade = null;
			e.printStackTrace();
		}
	}
	
	public Calendar getValidade()
	{
		return this.validade;
	}

	public long getCliente() {
		return cliente;
	}

	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		try
		{
			this.desconto = Double.parseDouble(desconto);
		}
		catch(Exception e)
		{
			this.desconto = 0;
			e.printStackTrace();
		}
	}

	public double getValor() {
		return valor;
	}

	public void setValor(String valor) {
		try
		{
			this.valor = Double.parseDouble(valor);
		}
		catch(Exception e)
		{
			this.valor = 0;
			e.printStackTrace();
		}
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
		
}
