package gardgir;

import gardgir.GardgirCallable;
import gardgir.GardgirCheck;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class Gardgir {
	public static final String PROJECT_NAME = "sampleproject";
    
	public static void main(String args[]) {
		gardgiri();
    }
    public static void gardgiri() {
		try {
			Iterable<Class> projectClassesList = GardgirClassFinder.findClassesByPackageName(PROJECT_NAME);
			for (Class projectClass: projectClassesList) {
				for (Method method: projectClass.getMethods()) {
					if (method.isAnnotationPresent(GardgirCheck.class)) {
						for (Annotation annotation: method.getDeclaredAnnotations()) {
							GardgirCheck gardgirCheckAnnotation = (GardgirCheck)annotation;
							@SuppressWarnings("unchecked")
							GardgirCallable tester = (GardgirCallable)(gardgirCheckAnnotation.tester().getDeclaredConstructor().newInstance());
							System.out.println(tester.call());
							System.out.println("Annotation in Method '" + method + "' : " + annotation);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}