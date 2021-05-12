package in.nit.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import in.nit.config.AppConfig;
import in.nit.config.Test;

public class AppInit 
	extends AbstractAnnotationConfigDispatcherServletInitializer
{

	//Provide Spring config file as input to web.xml(FC)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	//URL - Pattern
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new Test());
	}
	
}
