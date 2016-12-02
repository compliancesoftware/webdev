package br.com.compliancesoftware.control.Controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.compliancesoftware.control.dao.ClientesDao;
import br.com.compliancesoftware.control.dao.RegistrosDao;
import br.com.compliancesoftware.control.dao.SoftwaresDao;
import br.com.compliancesoftware.model.Cliente;
import br.com.compliancesoftware.model.auxModels.FMT;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Controller com foco na geração de relatórios em pdf
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
@Transactional
@Controller
public class RelatoriosController 
{
	@Qualifier("clientesJPA")
	@Autowired
	private ClientesDao clientesDao;
	
	@Qualifier("registrosJPA")
	@Autowired
	private RegistrosDao registrosDao;
	
	@Qualifier("softwaresJPA")
	@Autowired
	private SoftwaresDao softwaresDao;
	
	@RequestMapping("relatorioDeClientesCadastradosPdf")
	@ResponseBody
	public void relatorioDeClientesCadastrados(HttpServletResponse response)
	{
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/br/com/compliancesoftware/view/Relatorios/Clientes/clientes.jasper");
		    Map<String,Object> params = new HashMap<String,Object>();
		    params.put("titulo", "Clientes cadastrados no sistema.");
		    params.put("data", FMT.getHojeAsString());
		    
		    List<Cliente> listaCliente = clientesDao.listaClientes();
		    JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(listaCliente);
		    
		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		    JasperPrint jasperPrint = null;
		    if(listaCliente != null && listaCliente.size() > 0)
		    	jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanDataSource);
		    else
		    	jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

		    response.setContentType("application/pdf");
		    response.setHeader("Content-disposition", "inline; filename=clientes.pdf");

		    final OutputStream outStream = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		    outStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
