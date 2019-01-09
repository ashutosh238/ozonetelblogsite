package com.Ozonetel.BlogSite.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.Ozonetel")
public class WebResolver 
	{
		@Bean
		public InternalResourceViewResolver getViewResolver()
		{
			InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
			viewResolver.setPrefix("/WEB-INF/jsp/");
			return viewResolver;
		}

	}
