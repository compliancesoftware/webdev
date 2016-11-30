package br.com.compliancesoftware.control.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.RegistrosDao;
import br.com.compliancesoftware.control.dao.filtros.FiltroRegistro;
import br.com.compliancesoftware.model.Registro;

/**Implementação do gerenciador do banco de dados na tabela registros.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class RegistrosJPA implements RegistrosDao
{
	@PersistenceContext
	EntityManager manager;

	/**
	 * Verifica se já existe um cliente cadastrado com o software em questão no ato de <b>NOVO REGISTRO</b>
	 * @param registro
	 * @return
	 */
	private boolean jaExiste(Registro registro)
	{
		Query query = manager.createQuery("select r from Registro as r where r.cliente.id = :paramCliente and r.software.id = :paramSoftware");
		query.setParameter("paramCliente", registro.getCliente().getId());
		query.setParameter("paramSoftware", registro.getSoftware().getId());
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Método para registrar um novo software para um determinado cliente.
	 */
	@Override
	public String adiciona(Registro registro) 
	{
		if(jaExiste(registro))
		{
			return "<strong>Erro!</strong> Este software já está registrado para este cliente.";
		}
		else
		{
			manager.persist(registro);
			return "<strong>OK!</strong> Cliente registrado!";
		}
	}
	
	@Override
	public String remove(Registro registro) 
	{
		Registro remover = pegaRegistroPorId(registro.getId());
		manager.remove(remover);
		return "<strong>OK!</strong> Registro removido com êxito!";
	}

	@Override
	public String altera(Registro registro) 
	{
		Query query = manager.createQuery("select r from Registro as r where r.cliente.id = :paramCliente and r.software.id = :paramSoftware and r.id != :paramId");
		query.setParameter("paramCliente", registro.getCliente().getId());
		query.setParameter("paramSoftware", registro.getSoftware().getId());
		query.setParameter("paramId", registro.getId());
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
		{
			return "<strong>Erro!</strong> Já há um registro deste software para este cliente!";
		}
		else
		{
			manager.merge(registro);
			return "<strong>OK!</strong> Registro atualizado com êxito!";
		}
	}
	
	@Override
	public List<Registro> lista()
	{
		Query query = manager.createQuery("select r from Registro as r");
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista;
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> lista(FiltroRegistro filtro) 
	{
		String q = "select r from Registro as r where 1 = 1";
		ArrayList<HashMap<String,Object>> params = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> param;
		
		String cliente = filtro.getCliente();
		if(cliente != null && !cliente.equals(""))
		{
			q += " and upper(r.cliente.nome) like :paramCliente";
			cliente = "%"+cliente.toUpperCase()+"%";
			param = new HashMap<String,Object>();
			param.put("tipo", "String");
			param.put("nome", "paramCliente");
			param.put("valor", cliente);
			params.add(param);
		}
		
		String software = filtro.getSoftware();
		if(software != null && !software.equals(""))
		{
			q += " and upper(r.software.nome) like :paramSoftware";
			software = "%"+software.toUpperCase()+"%";
			param = new HashMap<String,Object>();
			param.put("tipo", "String");
			param.put("nome", "paramSoftware");
			param.put("valor", software);
			params.add(param);
		}
		
		double desconto = filtro.getDesconto();
		if(desconto >= 0 && desconto <= 100)
		{
			q += " and r.desconto = :paramDesconto";
			param = new HashMap<String,Object>();
			param.put("tipo", "double");
			param.put("nome", "paramDesconto");
			param.put("valor", desconto);
			params.add(param);
		}
		
		double valor = filtro.getValor();
		if(valor > 0 && valor <= 100)
		{
			q += " and r.valor = :paramValor";
			param = new HashMap<String,Object>();
			param.put("tipo", "double");
			param.put("nome", "paramValor");
			param.put("valor", valor);
			params.add(param);
		}
		
		Calendar vIni = filtro.getValidadeInicio();
		Calendar vFim = filtro.getValidadeFim();
		if((vIni != null) && (vFim != null))
		{
			q += " and r.validade >= :paramValIni and r.validade <= :paramValFim";
			
			param = new HashMap<String,Object>();
			param.put("tipo", "Calendar");
			param.put("nome", "paramValIni");
			param.put("valor", vIni);
			params.add(param);
			
			param = new HashMap<String,Object>();
			param.put("tipo", "Calendar");
			param.put("nome", "paramValFim");
			param.put("valor", vFim);
			params.add(param);
		}
		else if((vIni == null) && (vFim != null))
		{
			q += " and r.validade <= :paramValFim";
			
			param = new HashMap<String,Object>();
			param.put("tipo", "Calendar");
			param.put("nome", "paramValFim");
			param.put("valor", vFim);
			params.add(param);
		}
		else if((vIni != null) && (vFim == null))
		{
			q += " and r.validade >= :paramValIni";
			
			param = new HashMap<String,Object>();
			param.put("tipo", "Calendar");
			param.put("nome", "paramValIni");
			param.put("valor", vIni);
			params.add(param);
		}
		
		int ativo = filtro.getAtivo();
		if(ativo == FiltroRegistro.ATIVOS)
		{
			boolean ativado = true;
			q += " and r.ativo = :paramAtivo";
			
			param = new HashMap<String,Object>();
			param.put("tipo", "boolean");
			param.put("nome", "paramAtivo");
			param.put("valor", ativado);
			params.add(param);
		}
		else if(ativo == FiltroRegistro.DESATIVADOS)
		{
			boolean ativado = false;
			q += " and r.ativo = :paramAtivo";
			
			param = new HashMap<String,Object>();
			param.put("tipo", "boolean");
			param.put("nome", "paramAtivo");
			param.put("valor", ativado);
			params.add(param);
		}
		
		int plano = filtro.getPlano();
		if(plano > 0 && plano < 13)
		{
			q += " and r.plano = :paramPlano";
			param = new HashMap<String,Object>();
			param.put("tipo", "int");
			param.put("nome", "paramPlano");
			param.put("valor", plano);
			params.add(param);
		}
		
		Query query = manager.createQuery(q);
		for(HashMap<String,Object> item : params)
		{
			String tipo = (String)item.get("tipo");
			String nome = (String)item.get("nome");
			if(tipo.equals("String"))
			{
				query.setParameter(nome, (String)item.get("valor"));
			}
			else if(tipo.equals("int"))
			{
				query.setParameter(nome, (int)item.get("valor"));
			}
			else if(tipo.equals("Calendar"))
			{
				query.setParameter(nome, (Calendar)item.get("valor"));
			}
			else if(tipo.equals("double"))
			{
				query.setParameter(nome, (double)item.get("valor"));
			}
			else if(tipo.equals("boolean"))
			{
				query.setParameter(nome, (boolean)item.get("valor"));
			}
		}
		
		return query.getResultList();
	}

	@Override
	public List<Registro> listaAtrasados() 
	{
		Query query = manager.createQuery("select r from Registro as r where r.ativo = :paramAtivo");
		query.setParameter("paramAtivo", false);
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista;
		else
			return null;
	}

	@Override
	public int listaEmDia() 
	{
		Query query = manager.createQuery("select r from Registro as r where r.ativo = :paramAtivo");
		query.setParameter("paramAtivo", true);
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.size();
		else
			return 0;
	}

	@Override
	public Registro pegaRegistroPorId(Long id) 
	{
		Query query = manager.createQuery("select r from Registro as r where r.id = :paramId");
		query.setParameter("paramId", id);
		return (Registro)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int listaRegistrados(int mes) 
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		
		if(mes < 0 || mes > 11)
			mes = 0;
		
		Calendar inicio = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();
		
		inicio.set(Calendar.DAY_OF_MONTH, 1);
		if(mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11)
		{
			fim.set(Calendar.DAY_OF_MONTH, 31);
		}
		else if(mes == 2)
		{
			GregorianCalendar verificaBissexto = new GregorianCalendar();
			verificaBissexto.setTimeInMillis(System.currentTimeMillis());
			
			if(verificaBissexto.isLeapYear(hoje.get(Calendar.YEAR)))
			{
				fim.set(Calendar.DAY_OF_MONTH, 29);
			}
			else
			{
				fim.set(Calendar.DAY_OF_MONTH, 28);
			}
		}
		else
		{
			fim.set(Calendar.DAY_OF_MONTH, 30);
		}
		inicio.set(Calendar.MONTH, mes);
		fim.set(Calendar.MONTH, mes);
		inicio.set(Calendar.YEAR, hoje.get(Calendar.YEAR));
		inicio.set(Calendar.YEAR, hoje.get(Calendar.YEAR));
		
		Query query = manager.createQuery("select r from Registro as r where r.incluido > :paramInicio and r.incluido < :paramFim");
		query.setParameter("paramInicio", inicio);
		query.setParameter("paramFim", fim);
		
		List<Registro> lista =  query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.size();
		else
			return 0;
	}

	/**
	 * Retorna uma instância de Calendar configurada com a data de hoje.
	 * @return
	 */
	private Calendar agora()
	{
		Calendar hoje = Calendar.getInstance();
		hoje.setTimeInMillis(System.currentTimeMillis());
		return hoje;
	}
	
	/**
	 * Método que deve ser usado a cada vez que o sistema for logado para atualiza a situação cadastral dos registros
	 * Em suma, deve ser verificada a data de validade do registro e, se ela for menor que hoje, desativa o registro.
	 * Retorna a quantidade de registros que foram desativados.
	 */
	@Override
	public long atualiza() 
	{
		Query query = manager.createQuery("update Registro as r set r.ativo = :paramAtivo where r.validade < :paramValidade and r.ativo = :paramAtual");
		query.setParameter("paramAtivo", false);
		query.setParameter("paramValidade", agora());
		query.setParameter("paramAtual", true);
		long atualizados = (long)query.executeUpdate();
		return atualizados;
	}

	/**
	 * Método para listar registros de um determinado cliente
	 */
	@Override
	public List<Registro> pegaRegistrosDeCliente(Long id) 
	{
		Query query = manager.createQuery("select r from Registro as r where r.cliente.id = :paramId");
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista;
		else
			return null;
	}

	/**
	 * Método usado para localizar um registro de um determinado cliente para um determinado software
	 */
	@Override
	public Registro localiza(String cliente, String software) 
	{
		Query query = manager.createQuery("select r from Registro as r where r.cliente.codigo = :paramCliente and r.software.nome = :paramSoftware");
		query.setParameter("paramCliente", cliente);
		query.setParameter("paramSoftware", software);
		
		return (Registro)query.getSingleResult();
	}
	
}
