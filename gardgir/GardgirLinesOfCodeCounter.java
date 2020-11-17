package gardgir;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GardgirLinesOfCodeCounter {
	public static final int NOT_FOUND_INT = 0;
	public static final String JAVA_METHOD_REGEX = "^\\s*(public|private|static|protected|abstract|native|synchronized)\\s+([a-zA-Z_][a-zA-Z0-9<>._?, ]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\((\\s*([a-zA-Z_][a-zA-Z0-9<>._?, ]*\\s+[a-zA-Z_][a-zA-Z0-9_]*,)*\\s*([a-zA-Z_][a-zA-Z0-9<>._?, ]*\\s+[a-zA-Z_][a-zA-Z0-9_]*)\\s*)?\\)\\s*(\\{|\\{\\s*\\})?\\s*$";
	public static final int REGEX_METHOD_NAME_INDEX = 3;

	public static void main(String[] args) {
		System.out.println(countLinesOfCode("sampleproject/ClassA.java", "method1"));
	}

	public static int countLinesOfCode(String javaFilePath, String methodName) {
	 	try {
			BufferedReader br = new BufferedReader(new FileReader(javaFilePath));
			int lineNumber = 0;
			String line;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				Matcher matcher = Pattern.compile(JAVA_METHOD_REGEX).matcher(line);
				if (matcher.find() && matcher.group(REGEX_METHOD_NAME_INDEX).equals(methodName)) {
					int openBraces = 0, methodLinesOfCode = 1;
					while (countSubstrings(line, "{") == 0) {
						methodLinesOfCode++;
						if ((line = br.readLine()) == null)
							return methodLinesOfCode;
					}
					openBraces += countSubstrings(line, "{") - countSubstrings(line, "}");
					if (openBraces == 0)
						return methodLinesOfCode;
					while ((line = br.readLine()) != null) {
						methodLinesOfCode++;
						openBraces += countSubstrings(line, "{") - countSubstrings(line, "}");
						if (openBraces == 0)
							return methodLinesOfCode;
					}
				}
			}
			return NOT_FOUND_INT;
		} catch (Exception e) {
			e.printStackTrace();
			return NOT_FOUND_INT;
		}
	}

	private static int countSubstrings(String s, String t) {
		return (s.length() - s.replace(t, "").length()) / t.length();
	}

}