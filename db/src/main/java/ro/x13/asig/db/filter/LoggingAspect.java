package ro.x13.asig.db.filter;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {


    @Before("@annotation(a)")
    public void logMyAspect(JoinPoint jp, Loggable a) {
        long startTime = System.currentTimeMillis();
        String methodName = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
        //log.info("Executing method: " + methodName);
        ContextLocal.set(methodName, startTime);
    }

    @After("@annotation(a)")
    public void afterMethodStatistics(JoinPoint jp, Loggable a) {
        String methodName = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();

        long start = (Long)ContextLocal.get(methodName);
        log.info("Executed method: " + methodName + " in " + (System.currentTimeMillis() - start) + " ms");
    }

}
