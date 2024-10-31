package com.ssafy.sandbox.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // 권장되는 로그 형식 [메서드 이름] - [상태] - [메시지] - [추가 정보]
    // 1. 모든 컨트롤러 시작 전 로그 남기기
    @Before("execution(* com.ssafy.sandbox..controller..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        if (args.length == 0) {
            log.info("[{}] - [START]", methodName);
            return;
        }
        log.info("[{}] - [START] - [요청 파라미터]: {}", methodName, args);
    }

    // RestController의 응답 값
    @AfterReturning(pointcut = "execution(* com.ssafy.sandbox..controller..*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();

        // Controller의 응답 값 (Model 값)
        if (result instanceof ModelAndView modelAndView) {
            log.info("[{}] - [END] - 뷰 이름: {}, 모델 데이터: {}\n", name, modelAndView.getViewName(), modelAndView.getModel());
            return;
        }

        // RestController의 응답 값 (실제 값)
        log.info("[{}] - [END] - 응답 값: {}\n", name, result);
    }

    @Around("execution(* com.ssafy.sandbox..service..*(..))")
    public Object serviceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint: 실제 메서드를 호출할 수 있는 객체
        // 메서드 이름, 시간 측청
        String fullPathClassName = joinPoint.getSignature().getDeclaringTypeName();
        String className = fullPathClassName.substring(fullPathClassName.lastIndexOf(".") + 1);
        String methodName = className + "." + joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        log.debug("[{}] - [START] - [요청 파라미터]: {}", methodName, args);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("[{}] - [메서드 실행 중 예외 발생: {}]", methodName, e.getMessage());
            throw e;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
//        log.debug("[{}] - [END] - [실행 시간: {}ms] - [응답 값]: {}", methodName, executionTime, result.toString().replace(", ", "," + System.lineSeparator())); // DB 조회한 결과를 보려면 이걸로 하기
        log.debug("[{}] - [END] - [실행 시간: {}ms] - [응답 값]: {}\n", methodName, executionTime, result);

        return result;
    }


}