package pl.marekk.nlp.dataset.application.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
class LoggingAspect {
    @Around("@annotation(pl.marekk.nlp.dataset.application.log.LogMethodInvocation)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        final LocalDateTime start = LocalDateTime.now();
        final Object proceed = joinPoint.proceed();
        final LocalDateTime end = LocalDateTime.now();
        final Duration duration = Duration.between(start, end);

        log.info("method {} was finished with success in {} seconds", methodName, duration.toSeconds());
        return proceed;
    }

}
