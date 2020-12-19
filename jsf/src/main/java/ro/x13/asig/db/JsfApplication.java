package ro.x13.asig.db;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.ServletContextAware;

@SpringBootApplication
@EnableAspectJAutoProxy	//proxy logging & caching
public class JsfApplication implements ServletContextAware {  //extends SpringBootServletInitializer {	//

	public static void main(String[] args) {
		SpringApplication.run(JsfApplication.class, args);
	}
/*
	@Bean
	public static CustomScopeConfigurer viewScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
		return configurer;
	}
*/
	@Bean
	public ServletRegistrationBean<FacesServlet> jsfRegistrationBean() {
		ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<>(
				new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

/* TODO
	@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherRegistrationBean() {
		ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>(
				new DispatcherServlet(), "/");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
*/
	@Override
	public void setServletContext(ServletContext servletContext) {
		// Iniciar el contexto de JSF
		// http://stackoverflow.com/a/25509937/1199132
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
	}


}
