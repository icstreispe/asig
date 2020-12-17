package ro.x13.asig.db.filter;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * valid doar per request
 */

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public @interface RequestScopedController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}
