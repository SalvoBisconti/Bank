package com.advancia.stage.Banca.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet() {
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    // Specifica che vuoi utilizzare un ApplicationContext basato su annotazioni
	    servlet.setContextClass(org.springframework.web.context.support.AnnotationConfigWebApplicationContext.class);
	    // Imposta il contextConfigLocation a una stringa vuota per evitare la ricerca di un file XML. Poichè generava un errore poichè cercava un file XML che non c'era
	    servlet.setContextConfigLocation("");
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

    @Bean(name = "banca")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bancaSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BancaPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.example.com");
        wsdl11Definition.setSchema(bancaSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema bancaSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/banca.xsd"));
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws");  // URI del servizio SOAP  -> http://localhost:8080/ws/banca.wsdl per vedere il WSDL
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(marshaller());
        return webServiceTemplate;
    }
}

