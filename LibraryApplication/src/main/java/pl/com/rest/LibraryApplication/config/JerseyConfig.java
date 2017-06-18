package pl.com.rest.LibraryApplication.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.com.rest.LibraryApplication.exception.*;
import pl.com.rest.LibraryApplication.resources.BookResources;
import pl.com.rest.LibraryApplication.resources.UserResources;

/**
 * Created by Kasia on 13.06.2017.
 */

@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        register(UserResources.class);
        register(BookResources.class);
        register(NotFoundExceptionMapper.class);
        register(AppExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(MethodArgumentNotValidException.class);
        register(PoweredByResponseFilter.class);
        register(ConstraintViolationExceptionMapper.class);
        register(MethodArgumentNotValidExceptionMapper.class);


        packages("io.swagger.jaxrs.listing");

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Library");
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("");
        beanConfig.setResourcePackage("pl.com.rest.LibraryApplication");
        beanConfig.setScan(true);

        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);


    }
}

