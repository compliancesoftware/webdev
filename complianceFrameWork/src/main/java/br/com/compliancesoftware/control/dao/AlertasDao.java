package br.com.compliancesoftware.control.dao;

import java.util.Calendar;
import java.util.List;

import br.com.compliancesoftware.model.Alerta;

/**
 * Gerenciador de informações na tabela alertas no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface AlertasDao 
{
	public String notifica(Alerta alerta);
	public String altera(Alerta alerta);
	public Alerta getAlerta(long id);
	public List<Alerta> lista(Calendar inicio, Calendar fim);
	public List<Alerta> listaPorVisibilidade(Calendar inicio, Calendar fim, boolean visto);
	public int conta();
	public void primeiroUso();
}
