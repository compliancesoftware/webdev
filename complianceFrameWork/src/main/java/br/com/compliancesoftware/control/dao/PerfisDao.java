package br.com.compliancesoftware.control.dao;

import java.util.HashMap;
import java.util.List;

import br.com.compliancesoftware.model.Perfil;

/**
 * Indica como manipular os dados de perfis no banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public interface PerfisDao 
{
	public static final String ADM = "Administrador";
	public static final String CM = "Comum";
	public static final String DST = "Desativado";
	
	public String adiciona(Perfil novoPerfil) throws Exception;
	public Perfil getPerfilById(Long id) throws Exception;
	public Perfil getPerfilByName(String nome) throws Exception;
	public Perfil getPerfilByEmail(String email) throws Exception;
	public String alteraPerfil(Perfil logado, Perfil perfil) throws Exception;
	public HashMap<String,Object> login(Perfil batendo) throws Exception;//Recebe um HashMap com os atributos Mensagem e Perfil da resultado do perfil que bate na porta
	public List<Perfil> getLista() throws Exception;
	public void primeiroUso();
}
