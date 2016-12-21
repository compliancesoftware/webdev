package br.com.compliancesoftware.model;

/**
 * Classe que representa um perfil de usuário do sistema.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Perfil 
{
	public enum Permissao
	{
		ADM("Administrador"),USR("Usuário"),DST("Desativado");
		
		public String value;
		
		Permissao(String value)
		{
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return this.value;
		}
	}
}
