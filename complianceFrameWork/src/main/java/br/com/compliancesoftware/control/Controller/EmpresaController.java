/**
 * 
 */
package br.com.compliancesoftware.control.Controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compliancesoftware.control.dao.EmpresaDao;
import br.com.compliancesoftware.model.Empresa;
import br.com.compliancesoftware.model.auxModels.FMT;

/**
 * Controller que facilita o cadastro de uma nova empresa proprietária do software.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Transactional
@Controller
public class EmpresaController 
{
	@Qualifier("empresaJPA")
	@Autowired
	private EmpresaDao dao;
	
	/**
	 * Método para acessar a página de cadastro da empresa.
	 * @return
	 */
	@RequestMapping("cadastrarEmpresa")
	public String cadastrarEmpresa(HttpSession session)
	{
		if(!dao.primeiroAcesso())
		{
			session.invalidate();
			return "redirect:login";
		}
		else
		{
			return "empresa/cadastrar";
		}
	}
	
	/**
	 * Método para cadastro da empresa.
	 * @return
	 */
	@RequestMapping("cadastraEmpresa")
	public String cadastraEmpresa(Empresa empresa, HttpSession session)
	{
		if(!dao.primeiroAcesso())
		{
			session.invalidate();
			return "redirect:login";
		}
		else
		{
			Calendar data = Calendar.getInstance();
			data.setTimeInMillis(System.currentTimeMillis());
			data.add(Calendar.DAY_OF_MONTH, -1);
			String hoje = FMT.getStringFromCalendar(data);
			
			empresa.setProximaVerificacao(hoje);
			String mensagem = dao.criaEmpresa(empresa);
			LoginController.setMsg(mensagem);
			return "redirect:login";
		}
	}
}
