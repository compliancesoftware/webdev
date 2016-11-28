package br.com.compliancesoftware.control.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.SoftwaresDao;
import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.Software;

/**
 * Implementação do gerenciador do banco de dados na tabela softwares.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class SoftwaresJPA implements SoftwaresDao
{
	@PersistenceContext
	EntityManager manager;

	@Override
	public String adiciona(Software software) 
	{
		Software conflito = getSoftwareByNome(software.getNome());
		if(conflito == null)
		{
			manager.persist(software);
			return "<strong>OK!</strong> Plano cadastrado!";
		}
		else
		{
			return "<strong>Erro!</strong> Já existe um plano de software cadastrado com esse nome!";
		}
	}

	/**
	 * Verifica se o software em questão já está em uso
	 * @return
	 */
	private boolean jaTemTransacoes(Long id)
	{
		Query query = manager.createQuery("select r from Registro as r where r.software.id = :paramId");
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public String remove(Long id) 
	{
		if(jaTemTransacoes(id))
		{
			return "<strong>Erro!</strong> Este Plano já está em uso!";
		}
		else
		{
			Software remover = getSoftwareById(id);
			manager.remove(remover);
			return "<strong>OK!</strong> Plano descadastrado!";
		}
	}

	@Override
	public List<Software> lista() 
	{
		Query query = manager.createQuery("select s from Software as s");
		@SuppressWarnings("unchecked")
		List<Software> lista = query.getResultList();
		return lista;
	}
	
	@Override
	public List<Software> listaPorNome(String nome) 
	{
		String name = "%"+nome.toUpperCase()+"%";
		Query query = manager.createQuery("select s from Software as s where upper(s.nome) like :paramNome");
		query.setParameter("paramNome", name);
		@SuppressWarnings("unchecked")
		List<Software> lista = query.getResultList();
		return lista;
	}

	@Override
	public Software getSoftwareById(Long id) 
	{
		Query query = manager.createQuery("select s from Software as s where s.id = :paramId");
		query.setParameter("paramId", id);
		@SuppressWarnings("unchecked")
		List<Software> lista = query.getResultList();
		if(lista == null || lista.size() < 1)
			return null;
		else
			return lista.get(0);
	}

	@Override
	public Software getSoftwareByNome(String nome) 
	{
		Query query = manager.createQuery("select s from Software as s where s.nome = :paramNome");
		query.setParameter("paramNome", nome);
		@SuppressWarnings("unchecked")
		List<Software> lista = query.getResultList();
		if(lista == null || lista.size() < 1)
			return null;
		else
			return lista.get(0);
	}

	@Override
	public void primeiroUso() 
	{
		List<Software> lista = lista();
		if(lista == null || lista.size()<1)
		{
			Software soft = new Software();
			soft.setAtivo(true);
			soft.setNome("webdelivery");
			soft.setValor("100.00");
			
			manager.persist(soft);
		}
	}
	
	
}
