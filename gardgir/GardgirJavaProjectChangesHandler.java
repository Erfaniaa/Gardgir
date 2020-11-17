import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class GardgirJavaProjectChangesHandler extends GardgirJavaProject {
	static final String BACKUP_DIRECTORY_SUFFIX = "_backup";
	static final String LAST_VERSION_DIRECTORY_SUFFIX = "_last_version";

	public GardgirJavaProjectChangesHandler() {
		
	}

	public void copyFiles(String fromDirectory, String toDirectory) throws IOException {
        try {
			Path sourcePath = Paths.get(fromDirectory);
			Path destinationPath = Paths.get(toDirectory);
			Files.createDirectories(destinationPath);
			Stream<Path> files = Files.walk(sourcePath);
			files.forEach(file -> {
				try {
					Files.copy(file, destinationPath.resolve(sourcePath.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			});
			files.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void removeDirectory(String directoryPath) {
		deleteRecursive(new File(directoryPath));
	}

	private static boolean deleteRecursive(File directory) {
		return Arrays.stream(directory.listFiles()).allMatch((f) -> f.isDirectory() ? deleteRecursive(f) : f.delete()) && directory.delete();
	}
	
	public void backupProject() {
		copyFiles(getJavaProjectDirectoryRelativePath(), getJavaProjectDirectoryRelativePath() + BACKUP_DIRECTORY_SUFFIX);
	}

	public void updateLastVersion() {
		copyFiles(getJavaProjectDirectoryRelativePath(), getJavaProjectDirectoryRelativePath() + LAST_VERSION_DIRECTORY_SUFFIX);
	}

	public void restoreToLastVersion() {
		copyFiles(getJavaProjectDirectoryRelativePath() + LAST_VERSION_DIRECTORY_SUFFIX, getJavaProjectDirectoryRelativePath());
	}

	public void restoreToBackup() {
		copyFiles(getJavaProjectDirectoryRelativePath() + BACKUP_DIRECTORY_SUFFIX, getJavaProjectDirectoryRelativePath());
	}

	public void removeExtraDirectories() {
		removeDirectory(getJavaProjectDirectoryRelativePath() + LAST_VERSION_DIRECTORY_SUFFIX);
		removeDirectory(getJavaProjectDirectoryRelativePath() + BACKUP_DIRECTORY_SUFFIX);
	}

	public void addLineToFile(String filePath, int lineIndex, String line) {
		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path);
		lines.add(lineIndex, line);
		Files.write(path, lines);
	}
	
}