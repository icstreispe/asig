package ro.x13.asig.db.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

@Configuration
public class ServletConfiguration {

    /**
     * Customize what needs to be resolved FacesServlet The suffix is registered as Bean
     * <p>
     * Required, XML configuration is not available
     * Reason: It may be related to the project startup loading sequence
     *
    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());   //myfaces only

        FacesServlet facesServlet = new FacesServlet();
        ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<>(facesServlet, "*.xhtml");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
    */



    /*
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        //More parameters...
        //servletContext.addListener(ContextLoaderListener.class);
        //servletContext.addListener(RequestContextListener.class);
        servletContext.addListener(StartupServletContextListener.class);
    }
*/

    /*
    @Bean
    ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
        //spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        //FacesServlet registration
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }
    */




/*
    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        //necessary to myfaces be enabled and work in spring boot, once servlets are loaded dynamically.
        servletContext.setInitParameter("org.apache.myfaces.INITIALIZE_ALWAYS_STANDALONE", "true");

        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");

        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

        servletContext.setInitParameter("org.apache.myfaces.EXPRESSION_FACTORY", "com.sun.el.ExpressionFactoryImpl");
        servletContext.setInitParameter("org.apache.myfaces.CACHE_EL_EXPRESSIONS", "alwaysRecompile");

    }
*/
/*
    // Register ServletContextListener, necessary for Myfaces.
    @Bean
    ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean1()  {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean<ServletContextListener>();
        bean.setListener(new StartupServletContextListener());
        return bean;
    }

    @Bean
    RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
 */
}
