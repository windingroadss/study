package aop;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Performance {
//  @Around("execution(* aop.*(..))")
//  public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
//    Object result = null;
//    try {
//      long start = System.currentTimeMillis();
//      result = proceedingJoinPoint.proceed();
//      long end = System.currentTimeMillis();
//
//      System.out.println("수행 시간 : " + (end - start));
//    } catch (Throwable throwable) {
//      System.out.println("exception! ");
//    }
//    return result;
//  }
}
