package com.supinfo.timeweb.managedBean;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.ValidationException;

import com.supinfo.timetableasy.facade.FacadeRemote;




public class UserManagedBeanTest {
	
	private String login ;
	private String password;
	
	
	
	
	
	public String authentification(){
		
		
		try {
			Properties properties = new Properties();
			properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
			properties.put("java.naming.provider.url","localhost:1099");
			InitialContext context;
			context = new InitialContext(properties);
			FacadeRemote facade = (FacadeRemote) context.lookup("facadeSB/remote");
			facade.authentification(login, password);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.quartz.xml.ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "coco"; 
		
	}
	
	
	
	
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
