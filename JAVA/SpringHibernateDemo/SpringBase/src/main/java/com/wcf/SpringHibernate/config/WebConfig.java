package com.wcf.SpringHibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//����Mvc
@EnableWebMvc
//�������ɨ��
@ComponentScan("com.wcf.SpringHibernate")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	//����JSP��ͼ������
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	//���þ�̬��Դ�Ĵ���
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	/*
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource datasource)
	{
		LocalSessionFactoryBean sfb=new LocalSessionFactoryBean();
		sfb.setDataSource(datasource);
		sfb.setPackagesToScan(new String[]{"com.wcf.SpringHibernate.domain"});
		Properties props=new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		sfb.setHibernateProperties(props);
		return sfb;
	}
	*/

	

}