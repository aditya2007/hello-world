package com.helloworld.rest.dev.aspect;

import com.helloworld.rest.dev.annotations.AnnotationApp;
import com.helloworld.rest.dev.annotations.AssetPersister;
import com.helloworld.rest.dev.annotations.Mirror;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class MirrorAspect {

	@Before("execution(* *.*(.., @com.helloworld.rest.dev.annotations.Mirror (*), ..))")
	public void beforePersistence(JoinPoint joinPoint) {
		log.info("beforePersistence() of MirrorAspect here");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
		Object[] args = joinPoint.getArgs();

		Mirror mirrorAnnotation = methodSignature.getMethod().getAnnotation(Mirror.class);
		Transactional txAnnotation = methodSignature.getMethod().getAnnotation(Transactional.class);

		for (int i = 0; i < args.length && i < annotations.length; i++) {
			for (Annotation annotation : annotations[i]) {
				if (!(annotation instanceof Mirror)) {
					continue;
				}
			}
		}

	}

	@AfterReturning(value = "execution(* *.*(.., @com.helloworld.rest.dev.annotations.Mirror (*), ..))",
			returning = "result")
	public void afterPersistence(JoinPoint joinPoint, Object result) {
		log.info("afterPersistence() of MirrorAspect here");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
		Object[] args = joinPoint.getArgs();

		Mirror mirrorAnnotation = methodSignature.getMethod().getAnnotation(Mirror.class);

		for (int i = 0; i < args.length && i < annotations.length; i++) {
			for (Annotation annotation : annotations[i]) {
				if (!(annotation instanceof Mirror)) {
					continue;
				}
			}
		}

	}
}
