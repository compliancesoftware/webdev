package br.com.compliancesoftware.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.compliancesoftware.control.Controller.SystemController;
import br.com.compliancesoftware.control.dao.PerfisDao;
import br.com.compliancesoftware.model.Perfil;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter
{
	@Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object controller) throws Exception
	 {
		Perfil logado = (Perfil)request.getSession().getAttribute("logado");
	      String uri = request.getRequestURI();
	      
	      System.out.println("Solicita��o: "+uri);
	      
	      if(uri.endsWith("login") || uri.endsWith("entrar") || uri.contains("resources") || uri.contains("tags/") || uri.contains("error"))
	      {
	    	  return true;
	      }

	      if(logado != null) 
	      {
	    	  //Adiciona aqui as requisi��es que devem ser acessadas apenas por perfis ADM
	    	  ArrayList<String> requests = new ArrayList<String>();
	    	  requests.add("/logs");
	    	  requests.add("cadastrarNovoPerfil");
	    	  requests.add("cadastraPerfil");
	    	  requests.add("cadastrarNovoPerfil");
	    	  
	    	  for(String s:requests)
	    	  {
	    		  if(uri.contains(s))
		    	  {
		    		  if(logado.getPermissao().equals(PerfisDao.ADM))
		    		  {
		    			  System.out.println("Sess�o autorizada: "+uri);
		    	    	  return true;
		    		  }
		    		  else
		    		  {
		    			  SystemController.setMsg("<strong>Erro!</strong> Voc� n�o possui permiss�es suficientes para esta atividade.");
			    		  response.sendRedirect("home");
			    		  return false;
		    		  }
		    	  }
	    	  }
	    	  
	    	  System.out.println("Sess�o autorizada: "+uri);
	    	  return true;
	      }
	      
	      System.out.println("Acesso negado...Redirecionando...");
	      response.sendRedirect("login");
	      return false;
	 }
}
