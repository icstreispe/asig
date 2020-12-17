package ro.x13.asig.db.filter;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ViewAspect {

    @Autowired
    private CacheService cacheService;

    @Pointcut("within(@ro.x13.asig.db.filter.Cacheable *)")
    public void beanAnnotated() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("publicMethod() && beanAnnotated()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {}

    //@Around("@annotation(a)")
    @Around("publicMethodInsideAClassMarkedWithAtMonitor()")
    public Object execute(ProceedingJoinPoint jp) throws Throwable{

        String key = jp.getTarget().hashCode() + "/" + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();    //TODO safe???

        Object cachedValue = cacheService.getData(key);

        if (cachedValue == null) {
            cachedValue = jp.proceed();
            cacheService.cacheData(key, cachedValue);
            log.info("Cache miss " + key);
        } else {
            log.info("Cached hit " + key);
        }
        return cachedValue;
    }

}
