package gardgir;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GardgirClassFinder {

	public static Iterable<Class> findClassesByPackageName(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			URI uri;
			try {
				uri = new URI(resource.toString());
				dirs.add(new File(uri.getPath()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Class> classes = new ArrayList<Class>();
		for (File directory : dirs)
		{
			classes.addAll(findClassesByDirectoryAndPackageName(directory, packageName));
		}

		return classes;
	}

	public static List<Class> findClassesByDirectoryAndPackageName(File directory, String packageName) throws ClassNotFoundException
	{
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists())
		{
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files)
		{
			if (file.isDirectory())
			{
				classes.addAll(findClassesByDirectoryAndPackageName(file, packageName + "." + file.getName()));
			}
			else if (file.getName().endsWith(".class"))
			{
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}