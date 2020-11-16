package gardgir;

import java.io.BufferedReader;
import java.io.FileReader;

public class GardgirCallGraph {
	private static final String JAVA_CALL_GRAPH_JAR_FILE_PATH = "javacg-0.1-SNAPSHOT-static.jar";
	private static final String OUTPUT_FILE_PATH = "output.txt";
	private GardgirDirectedGraph graph;
	private String projectPackageName;
	private String projectJarFilePath;
	
	public static void main(String args[]) {
		GardgirCallGraph gardgirCallGraph = new GardgirCallGraph("build/sampleproject/sampleproject.jar", "sampleproject");
		gardgirCallGraph.constructGraph();
	}

	public GardgirCallGraph(String projectJarFilePath, String projectPackageName) {
		graph = new GardgirDirectedGraph();
		this.projectPackageName = projectPackageName;
		this.projectJarFilePath = projectJarFilePath;
	} 

	public void constructGraph() {
		try {
			String bashCommand = "java -jar " + JAVA_CALL_GRAPH_JAR_FILE_PATH + " " + projectJarFilePath + " > " + OUTPUT_FILE_PATH;
			Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", bashCommand});
			process.waitFor();
			try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FILE_PATH))) {
				String line;
				while ((line = br.readLine()) != null)
					addEdgeFromCallGraphString(line);
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void addEdgeFromCallGraphString(String callGraphString) {
		if (callGraphString.startsWith("M:") && countSubstrings(callGraphString, projectPackageName + ".") == 2) {
			String[] callGraphStringParts = callGraphString.split(" ");
			System.out.println(callGraphStringParts[0].substring(2));
			System.out.println(callGraphStringParts[1].substring(3));
			graph.addEdge(callGraphStringParts[0].substring(2), callGraphStringParts[1].substring(3));
		}
		else
			return;
	}

	private int countSubstrings(String s, String t) {
		return (s.length() - s.replace(t, "").length()) / t.length();
	}
}