package br.com.compliancesoftware.control.jpa;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.model.Perfil;
import br.com.compliancesoftware.model.auxModels.ValidaEmail;

/**
 * Acesso � tabela de Perfis do banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class PerfisJPA implements PerfisDao
{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Perfil getPerfilById(Long id) throws Exception 
	{
		Perfil perfil = manager.find(Perfil.class, id);
		return perfil;
	}

	@Override
	public Perfil getPerfilByName(String nome) throws Exception 
	{
		Query query = manager.createQuery("select p from Perfil as p where p.nome = :paramNome");
		query.setParameter("paramNome", nome);
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
		{
			return lista.get(0);
		}
		return null;
	}
	
	@Override
	public Perfil getPerfilByEmail(String email) throws Exception 
	{
		Query query = manager.createQuery("select p from Perfil as p where p.email = :paramEmail");
		query.setParameter("paramEmail", email);
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	/**
	 * Verifica se o perfil passado por id � o �nico ADM cadastrado.
	 * O uso deste m�todo � para saber at� quando pode-se alterar o n�vel de acesso de um usu�rio de ADM para CM
	 * @return
	 */
	private boolean unicoAdm(long id)
	{
		Query query = manager.createQuery("select p from Perfil as p where p.permissao = :paramPermissao and p.id != :paramId");
		query.setParameter("paramPermissao", "Administrador");
		query.setParameter("paramId", id);
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Verifica se j� existe algum usu�rio cadastrado com este endere�o de e-mail.
	 * @param id
	 * @param email
	 * @return
	 */
	private boolean jaExisteComEsteEmail(long id, String email)
	{
		Query query = manager.createQuery("select p from Perfil as p where p.email = :paramEmail and p.id != :paramId");
		query.setParameter("paramEmail", email);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se j� existe algum usu�rio cadastrado com este nome.
	 * @param id
	 * @param nome
	 * @return
	 */
	private boolean jaExisteComEsteNome(long id, String nome)
	{
		Query query = manager.createQuery("select p from Perfil as p where p.nome = :paramNome and p.id != :paramId");
		query.setParameter("paramNome", nome);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se j� existe algum usu�rio cadastrado com este n�mero de telefone.
	 * @param id
	 * @param contato
	 * @return
	 */
	private boolean jaExisteComEsteContato(long id, String contato)
	{
		Query query = manager.createQuery("select p from Perfil as p where p.contato = :paramContato and p.id != :paramId");
		query.setParameter("paramContato", contato);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Perfil> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * M�todo para atualizar um perfil
	 * Este m�todo deve verificar se o perfil est� sendo auto atualizado
	 * ou se � um usu�rio Administrador, caso seja um perfil Comum, 
	 * n�o pode atualizar outro.
	 * 
	 * Tamb�m � necess�rio fazer as devidas verifica��es de validade do Perfil como
	 * verificar se tem um Perfil cadastrado com mesmo nome ou e-mail
	 * e verificar se o e-mail � v�lido.
	 */
	@Override
	public String alteraPerfil(Perfil logado, Perfil perfil) throws Exception 
	{
		String permissao = logado.getPermissao();
		//Se o perfil logado for ADM, pode alterar qualquer perfil
		if(permissao.equals(ADM))
		{
			permissao = perfil.getPermissao(); //Agora uso a permiss�o do perfil a atualizar para saber se posso.
			//Se a permiss�o � ou pretende ser ADM
			if(permissao.equals(ADM))
			{
				return prossegue(perfil);
			}
			//Se a permiss�o n�o � ADM ou pretende ser CM ou DST, verifica se esta atualiza��o � poss�vel, uma vez que deve haver ao menos um perfil ADM
			else
			{
				if(unicoAdm(perfil.getId()))
				{
					return "<strong>Erro!</strong> Deve aver ao menos um Perfil Administrador.";
				}
				else
				{
					return prossegue(perfil);
				}
			}
		}
		//Se o perfil n�o for ADM, s� pode alterar a si mesmo.
		else
		{
			if(logado.getId() == perfil.getId())
			{
				return prossegue(perfil);
			}
			else
			{
				return "<strong>Erro!</strong> Voc� n�o possui permiss�es suficientes para esta atividade.";
			}
		}
	}

	/**
	 * Resumo para atualizar perfil.
	 * @param perfil
	 * @return
	 */
	private String prossegue(Perfil perfil) 
	{
		if(ValidaEmail.isValido(perfil.getEmail()))
		{
			long id = perfil.getId();
			
			if(jaExisteComEsteEmail(id,perfil.getEmail()))
			{
				return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este e-mail.";
			}
			else
			{
				if(jaExisteComEsteNome(id,perfil.getNome()))
				{
					return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este nome.";
				}
				else
				{
					if(jaExisteComEsteContato(id,perfil.getContato()))
					{
						return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este n�mero.";
					}
					else
					{
						manager.merge(perfil);
						return "<strong>OK!</strong> Perfil atualizado.";
					}
				}
			}
		}
		else
		{
			return "<strong>Erro!</strong> Endere�o de e-mail inv�lido.";
		}
	}

	
	/**
	 * M�todo para logar no sistema.
	 */
	@Override
	public HashMap<String, Object> login(Perfil batendo) throws Exception 
	{
		String nome = batendo.getNome();
		String senha = batendo.getSenha();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		Perfil perfil = getPerfilByName(nome);
		
		if(perfil == null)
		{
			perfil = getPerfilByEmail(nome);
			if(perfil == null)
			{
				result.put("Mensagem", "<strong>Erro!</strong> Nome de usu�rio incorreto.");
				result.put("Perfil", perfil);
				
				return result;
			}
		}
		if(perfil.getPermissao().equals(PerfisDao.DST))
		{
			result.put("Mensagem", "<strong>Info!</strong> Usu�rio desativado.");
			perfil = null;
			result.put("Perfil", perfil);
			
			return result;
		}
		if(perfil.getSenha().equals(senha))
		{
			result.put("Mensagem", "<strong>OK!</strong> Bem vindo "+nome+"!");
			result.put("Perfil", perfil);
			
			perfil.setUlt_acesso(perfil.getAcesso());
			Calendar agora = Calendar.getInstance();
			agora.setTimeInMillis(System.currentTimeMillis());
			perfil.setAcesso(agora);
			
			manager.merge(perfil);
			
			return result;
		}
		result.put("Mensagem", "<strong>Erro!</strong> Senha incorreta.");
		perfil = null;
		result.put("Perfil", perfil);
		
		return result;
	}

	/**
	 * M�todo para cadastrar um novo perfil.
	 */
	@Override
	public String adiciona(Perfil perfil) throws Exception 
	{
		if(ValidaEmail.isValido(perfil.getEmail()))
		{
			long id = perfil.getId();
			
			if(jaExisteComEsteEmail(id,perfil.getEmail()))
			{
				return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este e-mail.";
			}
			else
			{
				if(jaExisteComEsteNome(id,perfil.getNome()))
				{
					return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este nome.";
				}
				else
				{
					if(jaExisteComEsteContato(id,perfil.getContato()))
					{
						return "<strong>Erro!</strong> J� existe um usu�rio cadastrado com este n�mero.";
					}
					else
					{
						manager.persist(perfil);
						return "<strong>OK!</strong> Perfil cadastrado.";
					}
				}
			}
		}
		else
		{
			return "<strong>Erro!</strong> Endere�o de e-mail inv�lido.";
		}
	}
	
	/**
	 * Lista perfis cadastrados no sistema
	 */
	@Override
	public List<Perfil> getLista()
	{
		Query query = manager.createQuery("select p from Perfil as p");
		@SuppressWarnings("unchecked")
		List<Perfil> perfis = query.getResultList();
		if(perfis != null && perfis.size() > 0)
			return perfis;
		return null;
	}

	/**
	 * Parametriza o banco de dados caso seja seu primeiro uso
	 */
	@Override
	public void primeiroUso()
	
	{
		List<Perfil> lista = getLista();
		if(lista == null || lista.size()<1)
		{
			Perfil adm = new Perfil();
			Calendar agora = Calendar.getInstance();
			agora.setTimeInMillis(System.currentTimeMillis());
			adm.setAcesso(agora);
			adm.setContato("81996729491");
			try 
			{
				adm.setDefaultFoto();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			adm.setEmail("douglas.ceo@compliancesoftware.com.br");
			adm.setNome("Administrador");
			adm.setPermissao("Administrador");
			adm.setSenha("adm");
			adm.setUlt_acesso(agora);
			
			manager.persist(adm);
		}
	}
	
}
