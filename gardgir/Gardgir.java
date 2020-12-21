package gardgir;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class Gardgir {
	public static final String PROJECT_NAME = "sampleproject";
	private static GardgirJavaProjectChangesHandler gardgirJavaProjectChangesHandler;
	private static GardgirCallGraph gardgirCallGraph;
    
	public static void main(String args[]) {
		gardgiri();
	}

    public static void gardgiri() {
		gardgirJavaProjectChangesHandler = new GardgirJavaProjectChangesHandler(PROJECT_NAME, PROJECT_NAME);
		gardgirJavaProjectChangesHandler.buildJar();
		gardgirCallGraph = new GardgirCallGraph(GardgirJavaProject.OUTPUT_JAR_FILENAME, PROJECT_NAME);
		
		while (true) {
			//TODO: calculateJavaMethodWeights();
			//TODO: apply on graph
			for (StronglyConnectedComponent stronglyConnectedComponent: sinkComponents) {
				//TODO: insert return line in code
				//TODO: calculateJavaMethodWeights();
				//TODO: revert code changes
			}
			//TODO: mark node to not consider in graph
			//TODO: update latest version
		}

		//TODO: print deletions
		//TODO: restore code to the original one

	}

	private void calculateJavaMethodWeights() {

	}
}