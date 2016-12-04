package br.com.compliancesoftware.control.Controller;

import java.awt.Image;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
import br.com.compliancesoftware.model.Registro;
import br.com.compliancesoftware.model.auxModels.FMT;
import br.com.compliancesoftware.model.auxModels.ListaIdsBean;
import br.com.compliancesoftware.view.Relatorios.Clientes.ClienteAdapter;
import br.com.compliancesoftware.view.Relatorios.Registros.RegistroAdapter;
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
	
	/**
	 * Emite o relatório de clientes.
	 * @param lista
	 * @param response
	 */
	@RequestMapping("relatorioDeClientes")
	@ResponseBody
	public void relatorioDeClientes(String lista, HttpServletResponse response)
	{
		try
		{
			if(lista == null || lista.equals(null) || lista.replace(",", "").trim().equals(""))
			{
				throw new Exception("Lista vazia.");
			}
			
			List<Cliente> listaCliente = new ArrayList<Cliente>();
		    List<Long> ids = ListaIdsBean.constroiDe(lista);
		    for(Long id : ids)
		    {
		    	Cliente cli = clientesDao.pegaClientePorId(id);
		    	listaCliente.add(cli);
		    }
		    List<ClienteAdapter> bean= ClienteAdapter.listaDeClientes(listaCliente);
		    
		    JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(bean);
		    imprimeRelatorio("Clientes/clientes.jasper","Clientes cadastrados",beanDataSource,response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			SystemController.setMsg("<strong>Erro!</strong> Erro ao tentar imprimir relatório. Verifique se a listagem foi bem sucedida.");
			try
			{
				response.sendRedirect("home");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Emite o relatório de registros
	 * @param lista
	 * @param response
	 */
	@RequestMapping("relatorioDeRegistros")
	@ResponseBody
	public void relatorioDeRegistros(String lista, HttpServletResponse response)
	{
		try
		{
			if(lista == null || lista.equals(null) || lista.replace(",", "").trim().equals(""))
			{
				throw new Exception("Lista vazia.");
			}
			
			List<Registro> listaRegistro = new ArrayList<Registro>();
		    List<Long> ids = ListaIdsBean.constroiDe(lista);
		    for(Long id : ids)
		    {
		    	Registro reg = registrosDao.pegaRegistroPorId(id);
		    	listaRegistro.add(reg);
		    }
		    List<RegistroAdapter> bean= RegistroAdapter.listaDeRegistros(listaRegistro);
		    
		    JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(bean);
		    imprimeRelatorio("Registros/registros.jasper","Registros cadastrados",beanDataSource,response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			SystemController.setMsg("<strong>Erro!</strong> Erro ao tentar imprimir relatório. Verifique se a listagem foi bem sucedida.");
			try
			{
				response.sendRedirect("home");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Gera relatório com base nos parâmetros passados.
	 * @param local
	 * @param titulo
	 * @param beanDataSource
	 * @param response
	 */
	private void imprimeRelatorio(String local, String titulo, JRBeanCollectionDataSource beanDataSource, HttpServletResponse response)
	{
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/br/com/compliancesoftware/view/Relatorios/"+local);
			InputStream imageStream = this.getClass().getResourceAsStream("/br/com/compliancesoftware/view/Relatorios/relatorios_fundo.png");
			Image background = ImageIO.read(imageStream);
			
		    Map<String,Object> params = new HashMap<String,Object>();
		    params.put("titulo", titulo);
		    params.put("data", FMT.getHojeAsString());
		    params.put("background", background);
		    
		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanDataSource);

		    final OutputStream outStream = response.getOutputStream();
		    
		    String fileName = local.substring(local.indexOf("/")+1).replace(".jasper", ".pdf");
	    	
	    	response.setContentType("application/pdf");
		    response.setHeader("Content-disposition", "inline; filename="+fileName);
		    
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		    
		    outStream.close();
		    jasperStream.close();
		    imageStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
