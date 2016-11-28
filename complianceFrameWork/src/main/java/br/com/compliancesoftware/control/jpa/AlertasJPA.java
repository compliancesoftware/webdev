package br.com.compliancesoftware.control.jpa;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.AlertasDao;
import br.com.compliancesoftware.model.Alerta;

/**
 * Implementação do DAO de Alertas para JPA.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class AlertasJPA implements AlertasDao
{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public String notifica(Alerta alerta) 
	{
		manager.persist(alerta);
		return "<strong>OK!</strong> Notificação criada com êxito.";
	}

	@Override
	public String altera(Alerta alerta) 
	{
		manager.merge(alerta);
		return "<strong>OK!</strong> Notificação atualizada.";
	}

	@Override
	public List<Alerta> lista(Calendar inicio, Calendar fim) 
	{
		String q = "select a from Alerta as a";
		Query query;
		if(inicio != null && fim != null)
		{
			q += " where a.data >= :paramInicio and a.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", inicio);
			query.setParameter("paramFim", fim);
		}
		else if(inicio == null && fim != null)
		{
			q += " where a.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramFim", fim);
		}
		else if(inicio != null && fim == null)
		{
			q += " where a.data >= :paramInicio";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", inicio);
		}
		else
		{
			query = manager.createQuery(q);
		}
		@SuppressWarnings("unchecked")
		List<Alerta> lista = query.getResultList();
		
		return lista;
	}

	@Override
	public List<Alerta> listaPorVisibilidade(Calendar inicio, Calendar fim, boolean visto) 
	{
		String q = "select a from Alerta as a where a.visto = :paramVisto";
		Query query;
		if(inicio != null && fim != null)
		{
			q += " and a.data >= :paramInicio and a.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", inicio);
			query.setParameter("paramFim", fim);
		}
		else if(inicio == null && fim != null)
		{
			q += " and a.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramFim", fim);
		}
		else if(inicio != null && fim == null)
		{
			q += " and a.data >= :paramInicio";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", inicio);
		}
		else
		{
			query = manager.createQuery(q);
		}
		query.setParameter("paramVisto", visto);
		
		@SuppressWarnings("unchecked")
		List<Alerta> lista = query.getResultList();
		
		return lista;
	}
	
	@Override
	public int conta()
	{
		Query query = manager.createQuery("select a from Alerta as a where a.visto = :paramVisto");
		query.setParameter("paramVisto", false);
		@SuppressWarnings("unchecked")
		List<Alerta> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.size();
		return 0;
	}

	@Override
	public Alerta getAlerta(long id) 
	{
		Query query = manager.createQuery("select a from Alerta as a where a.id = :paramId");
		query.setParameter("paramId", id);
		@SuppressWarnings("unchecked")
		List<Alerta> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public void primeiroUso() 
	{
		List<Alerta> lista = lista(null,null);
		if(lista == null || lista.size()<1)
		{
			Alerta primeiro = new Alerta();
			primeiro.setMensagem("Bem vindo ao WebRegistro! Seu sistema de registros de clientes online.");
			Calendar hoje = Calendar.getInstance();
			hoje.setTimeInMillis(System.currentTimeMillis());
			primeiro.setData(hoje);
			primeiro.setVisto(false);
			
			manager.persist(primeiro);
		}
	}
}
