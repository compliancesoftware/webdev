package br.com.compliancesoftware.control.dao;

import java.util.List;

import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.Registro;

/**
 * Interface de conexão com o banco de dados na tabela de registros.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface RegistrosDao 
{
	public String adiciona(Registro registro);
	public String remove(Registro registro);
	public String altera(Registro registro);
	public Registro pegaRegistroPorId(Long id);
	public List<Registro> pegaRegistrosDeCliente(Long id);
	public List<Registro> lista();
	public List<Registro> lista(FiltroRegistro filtro);
	public int listaRegistrados(int mes);
	public int listaAtrasados();
	public int listaEmDia();
	public long atualiza();
}
