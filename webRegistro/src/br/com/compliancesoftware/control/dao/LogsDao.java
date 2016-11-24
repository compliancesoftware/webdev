package br.com.compliancesoftware.control.dao;

import java.util.Calendar;
import java.util.List;

import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;

/**
 * Indica como manipular os dados de logs no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface LogsDao 
{
	public void adiciona(Log log);
	public List<Log> lista(Calendar inicio, Calendar fim);
	public List<Log> lista(Calendar inicio, Calendar fim, Perfil perfil);
}
