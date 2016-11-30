/**
 * 
 */
package br.com.compliancesoftware.control.jpa;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.EmpresaDao;
import br.com.compliancesoftware.model.Empresa;
import br.com.compliancesoftware.model.auxModels.VerificaId;

/**
 * Implementação JPA do acesso aos dados de empresa.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Repository
public class EmpresaJPA implements EmpresaDao
{
	@PersistenceContext
	EntityManager manager;
	
	/** (non-Javadoc)
	 * @see br.com.compliancesoftware.control.dao.EmpresaDao#primeiroAcesso()
	 */
	@Override
	public boolean primeiroAcesso() 
	{
		Query query = manager.createQuery("select e from Empresa as e");
		@SuppressWarnings("unchecked")
		List<Empresa> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return false;
		else
			return true;
	}

	/** (non-Javadoc)
	 * @see br.com.compliancesoftware.control.dao.EmpresaDao#getProximaVerificacao()
	 */
	@Override
	public Calendar getProximaVerificacao() 
	{
		Query query = manager.createQuery("select e from Empresa as e");
		Empresa empresa = (Empresa)query.getSingleResult();
		return empresa.getProximaVerificacao();
	}

	/** (non-Javadoc)
	 * @see br.com.compliancesoftware.control.dao.EmpresaDao#criaEmpresa(br.com.compliancesoftware.model.Empresa)
	 */
	@Override
	public String criaEmpresa(Empresa empresa) 
	{
		if(VerificaId.verifica(empresa.getCodigo()))
		{
			manager.persist(empresa);
			return "<strong>OK!</strong> Empresa cadastrada com êxito.";
		}
		else
		{
			return "<strong>Erro!</strong> O código de identificação está incorreto.";
		}
	}

	/** (non-Javadoc)
	 * @see br.com.compliancesoftware.control.dao.EmpresaDao#redefineDataVerificacao(java.util.Calendar)
	 */
	@Override
	public void redefineDataVerificacao(String data) 
	{
		Query query = manager.createQuery("select e from Empresa as e");
		Empresa empresa = (Empresa)query.getSingleResult();
		empresa.setProximaVerificacao(data);
	}

	/** (non-Javadoc)
	 * @see br.com.compliancesoftware.control.dao.EmpresaDao#getCodigo()
	 */
	@Override
	public String getCodigo() 
	{
		Query query = manager.createQuery("select e from Empresa as e");
		Empresa empresa = (Empresa)query.getSingleResult();
		return empresa.getCodigo();
	}
	
}
