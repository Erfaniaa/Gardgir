public class GardgirJavaProject {
	public static final String OUTPUT_JAR_FILENAME = "output.jar";
	private String javaProjectDirectoryRelativePath;
	private String javaProjectPackageName;

	GardgirJavaProject(String javaProjectDirectoryRelativePath, String javaProjectPackageName) {
		setJavaProjectDirectoryRelativePath(javaProjectDirectoryRelativePath);
		setJavaProjectPackageName(javaProjectPackageName);
	}

	public void setJavaProjectDirectoryRelativePath(String javaProjectDirectoryRelativePath) {
		this.javaProjectDirectoryRelativePath = javaProjectDirectoryRelativePath;
	}

	public void setJavaProjectPackageName(String javaProjectPackageName) {
		this.javaProjectPackageName = javaProjectPackageName;
	}

	public String getJavaProjectDirectoryRelativePath() {
		return javaProjectDirectoryRelativePath;
	}

	public String getJavaProjectPackageName() {
		return javaProjectPackageName;
	}

	public void buildJar() {
		try {
			String javacBashCommand = "javac " + getJavaProjectDirectoryRelativePath() + "/*.java";
			Process javacProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", javacBashCommand});
			javacProcess.waitFor();
			String jarBashCommand = "jar cvf " + OUTPUT_JAR_FILENAME + " " + getJavaProjectDirectoryRelativePath() + "/*.class";
			Process jarProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", jarBashCommand});
			jarProcess.waitFor();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}