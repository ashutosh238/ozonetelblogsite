package com.Ozonetel.BlogSite.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.Ozonetel.BlogSite.config.ApplicationContextConfiguration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer

	{

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		return new Class[] {WebResolver.class,ApplicationContextConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		 
		return new String[] {"/"};
	}
		
	
	}
