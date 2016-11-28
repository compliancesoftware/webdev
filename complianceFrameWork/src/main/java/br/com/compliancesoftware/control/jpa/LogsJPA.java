package br.com.compliancesoftware.control.jpa;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.LogsDao;
import br.com.compliancesoftware.model.Log;
import br.com.compliancesoftware.model.Perfil;

/**
 * Implementação do DAO de Logs para JPA
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class LogsJPA implements LogsDao
{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void adiciona(Log log) 
	{
		manager.persist(log);
	}
	
	public static void adiciona(EntityManager man, Log log)
	{
		man.persist(log);
	}

	@Override
	public List<Log> lista(Calendar inicio, Calendar fim) 
	{
		Date dt_i;
		Date dt_f;
		
		String q = "select l from Log as l";
		Query query;
		if(inicio != null && fim != null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data >= :paramInicio and l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio == null && fim != null)
		{
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio != null && fim == null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			q += " where l.data >= :paramInicio";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
		}
		else
		{
			query = manager.createQuery(q);
		}
		@SuppressWarnings("unchecked")
		List<Log> lista = query.getResultList();
		
		return lista;
	}

	@Override
	public List<Log> lista(Calendar inicio, Calendar fim, Perfil perfil) 
	{
		Date dt_i;
		Date dt_f;
		
		String q = "select l from Log as l where autor.id = :paramAutor";
		Query query;
		if(inicio != null && fim != null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data >= :paramInicio and l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio == null && fim != null)
		{
			dt_f = new Date(fim.getTimeInMillis());
			q += " where l.data <= :paramFim";
			query = manager.createQuery(q);
			query.setParameter("paramFim", dt_f);
		}
		else if(inicio != null && fim == null)
		{
			dt_i = new Date(inicio.getTimeInMillis());
			q += " where l.data >= :paramInicio";
			query = manager.createQuery(q);
			query.setParameter("paramInicio", dt_i);
		}
		else
		{
			query = manager.createQuery(q);
		}
		query.setParameter("paramAutor", perfil.getId());
		
		@SuppressWarnings("unchecked")
		List<Log> lista = query.getResultList();
		
		return lista;
	}
	
}
