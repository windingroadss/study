package aop.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Performance {

    @Pointcut("@annotation(Deprecated)")
    public void deprecated() {}

    @Pointcut("execution(* aop.example..*(..))")
    public void calculator() {}

    @Around("deprecated() && calculator()")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        return getObject(proceedingJoinPoint);
    }

    @Around("calculator()")
    public Object calculatePerformanceTime2(ProceedingJoinPoint proceedingJoinPoint) {
        return getObject(proceedingJoinPoint);
    }

    private Object getObject(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            String signature = proceedingJoinPoint.getSignature().toLongString();

            System.out.println("signature : " + signature);
            System.out.println("수행 시간 : " + (end - start));
        } catch (Throwable throwable) {
            System.out.println("exception! ");
        }
        return result;
    }
}
