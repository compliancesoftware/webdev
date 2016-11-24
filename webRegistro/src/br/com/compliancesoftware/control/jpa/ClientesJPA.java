package br.com.compliancesoftware.control.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.compliancesoftware.control.dao.ClientesDao;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.auxModels.ValidaEmail;
import br.com.compliancesoftware.model.auxModels.VerificaId;

/**
 * Implementação do gerenciador do banco de dados na tabela clientes.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Repository
public class ClientesJPA implements ClientesDao
{
	@PersistenceContext
	EntityManager manager;

	@Override
	public String adiciona(Cliente cliente) 
	{
		String codigo = cliente.getCodigo();
		String email = cliente.getEmail();
		String nome = cliente.getNome();
		
		if(isValido(codigo))
		{
			if(jaExisteComEsseCodigo(codigo))
			{
				return "<strong>Erro!</strong> Já existe um cliente cadastrado com este código.";
			}
			else
			{
				if(ValidaEmail.isValido(email))
				{
					if(jaExisteComEsseEmail(email))
					{
						return "<strong>Erro!</strong> Já existe um cliente cadastrado com este e-mail.";
					}
					else
					{
						if(jaExisteComEsseNome(nome))
						{
							return "<strong>Erro!</strong> Já existe um cliente cadastrado com este nome.";
						}
						else
						{
							manager.persist(cliente);
							return "<strong>OK!</strong> Cliente cadastrado.";
						}
					}
				}
				else
				{
					return "<strong>Erro!</strong> Endereço de e-mail inválido.";
				}
			}
		}
		else
		{
			return "<strong>Erro!</strong> Código inválido.(CPF ou CNPJ inválido)";
		}
	}
	
	private boolean isValido(String codigo)
	{
		return VerificaId.verifica(codigo);
	}

	@Override
	public String remove(Cliente cliente) 
	{
		if(jaTemTransacoes(cliente.getId()))
		{
			return "<strong>Erro!</strong> Este cliente já possui transações ativas e não pode ser excluido!";
		}
		else
		{
			cliente = pegaClientePorId(cliente.getId());
			manager.remove(cliente);
			return "<strong>OK!</strong> Cliente removido com êxito!";
		}
	}

	@Override
	public String altera(Cliente cliente) 
	{
		long id = cliente.getId();
		String codigo = cliente.getCodigo();
		String email = cliente.getEmail();
		String nome = cliente.getNome();
		
		if(isValido(codigo))
		{
			if(jaExisteComEsseCodigo(id,codigo))
			{
				return "<strong>Erro!</strong> Já existe um cliente cadastrado com este código.";
			}
			else
			{
				if(ValidaEmail.isValido(email))
				{
					if(jaExisteComEsseEmail(id,email))
					{
						return "<strong>Erro!</strong> Já existe um cliente cadastrado com este e-mail.";
					}
					else
					{
						if(jaExisteComEsseNome(id,nome))
						{
							return "<strong>Erro!</strong> Já existe um cliente cadastrado com este nome.";
						}
						else
						{
							manager.merge(cliente);
							return "<strong>OK!</strong> Cliente atualizado.";
						}
					}
				}
				else
				{
					return "<strong>Erro!</strong> Endereço de e-mail inválido.";
				}
			}
		}
		else
		{
			return "<strong>Erro!</strong> Código inválido.(CPF ou CNPJ inválido)";
		}
	}
	
	/**
	 * Verifica se já há um cliente cadastrado com o novo nome.
	 * @param id
	 * @param codigo
	 * @return
	 */
	private boolean jaExisteComEsseCodigo(long id, String codigo)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.codigo = :paramCodigo and c.id != :paramId");
		query.setParameter("paramCodigo", codigo);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se já há um cliente cadastrado com o novo nome.
	 * @param id
	 * @param codigo
	 * @return
	 */
	private boolean jaExisteComEsseCodigo(String codigo)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.codigo = :paramCodigo");
		query.setParameter("paramCodigo", codigo);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se já há um cliente cadastrado com o novo email.
	 * @param id
	 * @param email
	 * @return
	 */
	private boolean jaExisteComEsseEmail(long id, String email)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.email = :paramEmail and c.id != :paramId");
		query.setParameter("paramEmail", email);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se já há um cliente cadastrado com o este email.
	 * @param email
	 * @return
	 */
	private boolean jaExisteComEsseEmail(String email)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.email = :paramEmail");
		query.setParameter("paramEmail", email);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se já há um cliente cadastrado com o este nome.
	 * @param nome
	 * @return
	 */
	private boolean jaExisteComEsseNome(long id, String nome)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.nome = :paramNome and c.id != :paramId");
		query.setParameter("paramNome", nome);
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se já há um cliente cadastrado com este nome.
	 * @param nome
	 * @return
	 */
	private boolean jaExisteComEsseNome(String nome)
	{
		Query query = manager.createQuery("select c from Cliente as c where c.nome = :paramNome");
		query.setParameter("paramNome", nome);
		
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se o cliente passado por id já possui cadastro e movimentações
	 * @param id
	 * @return
	 */
	private boolean jaTemTransacoes(long id)
	{
		Query query = manager.createQuery("select r from Registro as r where r.cliente.id = :paramId");
		query.setParameter("paramId", id);
		
		@SuppressWarnings("unchecked")
		List<Registro> lista = query.getResultList();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public Cliente pegaClientePorId(Long id) 
	{
		Cliente cliente = manager.find(Cliente.class, id);
		return cliente;
	}

	private List<Cliente> pegaClientePorNome(String nome) 
	{
		String name = "%"+nome.toUpperCase()+"%";
		Query query = manager.createQuery("select c from Cliente as c where upper(c.nome) like :paramNome");
		query.setParameter("paramNome", name);
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}

	private List<Cliente> pegaClientePorCodigo(String codigo) 
	{
		String cod = codigo.replace(".", "").replace("-", "").replace("/", "");
		cod = "%"+cod+"%";
		Query query = manager.createQuery("select c from Cliente as c where c.codigo like :paramCod");
		query.setParameter("paramCod", cod);
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}

	private List<Cliente> pegaClientePorContato(String contato) 
	{
		String numero = contato.replace(".", "").replace("-", "").replace("/", "");
		numero = "%"+numero+"%";
		Query query = manager.createQuery("select c from Cliente as c where c.contato like :paramContato");
		query.setParameter("paramContato", numero);
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}
	
	private List<Cliente> pegaClientePorEmail(String email) 
	{
		String consulta = "%"+email.toUpperCase()+"%";
		Query query = manager.createQuery("select c from Cliente as c where upper(c.email) like :paramEmail");
		query.setParameter("paramEmail", consulta);
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}
	
	private List<Cliente> pegaClientePorResponsavel(String responsavel) 
	{
		String consulta = "%"+responsavel.toUpperCase()+"%";
		Query query = manager.createQuery("select c from Cliente as c where upper(c.responsavel) like :paramResponsavel");
		query.setParameter("paramResponsavel", consulta);
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}
	
	@Override
	public List<Cliente> listaClientes() 
	{
		Query query = manager.createQuery("select c from Cliente as c");
		@SuppressWarnings("unchecked")
		List<Cliente> lista = query.getResultList();
		return lista;
	}

	@Override
	public List<Cliente> pesquisaCliente(String pesquisa) 
	{
		List<Cliente> listaNome = pegaClientePorNome(pesquisa);
		List<Cliente> listaCodigo = pegaClientePorCodigo(pesquisa);
		List<Cliente> listaContato = pegaClientePorContato(pesquisa);
		List<Cliente> listaEmail = pegaClientePorEmail(pesquisa);
		List<Cliente> listaResponsavel = pegaClientePorResponsavel(pesquisa);
		
		List<Cliente> lista = new ArrayList<Cliente>();
		if(listaNome != null && listaNome.size() > 0)
		{
			for(Cliente cli:listaNome)
			{
				if(!(lista.contains(cli)))
					lista.add(cli);
			}
		}
		if(listaCodigo != null && listaCodigo.size() > 0)
		{
			for(Cliente cli:listaCodigo)
			{
				if(!(lista.contains(cli)))
					lista.add(cli);
			}
		}
		if(listaContato != null && listaContato.size() > 0)
		{
			for(Cliente cli:listaContato)
			{
				if(!(lista.contains(cli)))
					lista.add(cli);
			}
		}
		if(listaEmail != null && listaEmail.size() > 0)
		{
			for(Cliente cli:listaEmail)
			{
				if(!(lista.contains(cli)))
					lista.add(cli);
			}
		}
		if(listaResponsavel != null && listaResponsavel.size() > 0)
		{
			for(Cliente cli:listaResponsavel)
			{
				if(!(lista.contains(cli)))
					lista.add(cli);
			}
		}
		
		if(lista == null || lista.size() < 1)
			lista = null;
		
		return lista;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public int cadastradosMes(int mes) 
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
		
		Query query = manager.createQuery("select c from Cliente as c where c.dtInclusao > :paramInicio and c.dtInclusao < :paramFim");
		query.setParameter("paramInicio", inicio);
		query.setParameter("paramFim", fim);
		
		List<Cliente> lista =  query.getResultList();
		if(lista != null && lista.size() > 0)
			return lista.size();
		else
			return 0;
	}

	/**
	 * Verifica se há clientes cadastrados no banco.
	 */
	@Override
	public boolean temClienteCadastrado() 
	{
		List<Cliente> lista = listaClientes();
		if(lista != null && lista.size() > 0)
			return true;
		else
			return false;
	}
}
