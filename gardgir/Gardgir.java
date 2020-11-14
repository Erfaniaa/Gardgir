package gardgir;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public static const String PROJECT_NAME = "sampleproject";

class Gardgir {
    public static void main(String args[]) {
		gardgiri();
    }
    public static void gardgiri() {
		Iterable<Class> projectClassesList = GardgirClassFinder.findClassesByPackageName(PROJECT_NAME);

		for (Class projectClass: projectClassesList) {
			for (Method method: projectClass.getMethods()) {
				if (method.isAnnotationPresent(GardgirCheck.class)) {
					for (Annotation annotation: method.getDeclaredAnnotations()) {
						System.out.println("Annotation in Method '" + method + "' : " + annotation);
					}
				}
			}
		}
    }
}