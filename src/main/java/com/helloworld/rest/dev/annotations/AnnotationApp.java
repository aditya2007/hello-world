package com.helloworld.rest.dev.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AnnotationApp {
	public static void main(String[] args) throws NoSuchMethodException {
		AnnotationApp annotationApp = new AnnotationApp();
		Method method = AssetPersister.class.getDeclaredMethod("createAssets", String.class,
				Collection.class, List.class);

		Annotation[][] annotations = method.getParameterAnnotations();
		boolean isAnnotationPresent = annotationApp.isAnnotationPresent(annotations);

		System.out.println("Annotation present! " + isAnnotationPresent);

		System.out.println("Number of Annotations >> " + annotations.length);
		AnnotatedType[] annotatedTypes = method.getAnnotatedParameterTypes();
		for (AnnotatedType annotatedType : annotatedTypes) {
			System.out.println("parameter annotations type <<>> " + Arrays.toString(annotatedType.getAnnotations()));
		}
		//Annotation annotation = annotatedType.getAnnotation(Annotation.class);
		//System.out.println("parameter annotations >>> ");
		//System.out.println(Arrays.toString(method.getParameterAnnotations()[2]));
	}

	private boolean isAnnotationPresent(Annotation[][] annotations) {
		if (annotations == null)
			throw new IllegalArgumentException("Please pass a non-null array of Annotations.");
		for (int i = 0; i < annotations.length; i++) {
			for (Annotation annotation : annotations[i]) {
				System.out.println(annotation.annotationType());
				if ((annotation instanceof Mirror)) {
					return true;
				}
			}
		}
		return false;
	}
}
