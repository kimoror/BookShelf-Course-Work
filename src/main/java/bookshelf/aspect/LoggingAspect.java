package bookshelf.aspect;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){}

    /**
     * Aspect for logging methods
     * @param joinPoint - method that will be logged
     * @return result of method
     * @throws Throwable
     */
    @Around("executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        Object returnValue = joinPoint.proceed();

        long spendTime = System.currentTimeMillis() - startTime;

        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName()).append(".");
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> message.append(" аrgs: ").append(arg).append("."));
        message.append(" Time: ").append(spendTime).append(" milliseconds.");

//        message.append(" Return: ").append(returnValue.toString());

        log.info(message.toString());

        return returnValue;
    }

}
