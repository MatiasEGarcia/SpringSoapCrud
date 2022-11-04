package com.example.personSpringSoap.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter{


	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet,"/personSoap/*");/*where does my app work*/
	}
	
	/*/personSoap/person ->  it is a url that is formed, in this url we show the countriesSchema*/
	@Bean(name="person")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema personSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PersonPort");
		wsdl11Definition.setLocationUri("/personSoap");
		wsdl11Definition.setTargetNamespace("http://www.example.org/person");
		wsdl11Definition.setSchema(personSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema personSchema() {
		return new SimpleXsdSchema(new ClassPathResource("person.xsd"));
	}
	
}
