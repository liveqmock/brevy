package com.ips.commons.support.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.aop.ClassFilter;


/**
 * @Description 扫描器
 * @author caobin
 * @date 2012-11-27
 * @version 1.0
 */
public class Scanner {
	/**
	 * 从package中获取所有的Class
	 * 
	 * @param basepackage 基包
	 * @param classFilter class过滤器
	 * @return
	 */
	public static Set<Class<?>> getClasses(String basepackage, ClassFilter classFilter) {

		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		boolean recursive = true;
		String packageName = basepackage;
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes, classFilter);
				} else if ("jar".equals(protocol)) {
					JarFile jar = ((JarURLConnection) url.openConnection())
							.getJarFile();
					Enumeration<JarEntry> entries = jar.entries();
					while (entries.hasMoreElements()) {
						JarEntry entry = entries.nextElement();
						String name = entry.getName();
						if (name.charAt(0) == '/') {
							name = name.substring(1);
						}
						if (name.startsWith(packageDirName)) {
							int idx = name.lastIndexOf('/');
							if (idx != -1) {
								packageName = name.substring(0, idx).replace(
										'/', '.');
							}
							if ((idx != -1) || recursive) {
								if (name.endsWith(".class")
										&& !entry.isDirectory()) {
									String className = name.substring(
											packageName.length() + 1,
											name.length() - 6);
									try {
										Class clazz = Class.forName(packageName
												+ '.' + className);
										if(classFilter != null){
											if(classFilter.matches(clazz)){
												classes.add(clazz);	
											}		
										}else{
											classes.add(clazz);
										}
									} catch (ClassNotFoundException e) {
										throw e;
									}
								}
							}
						}
					}

				}
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 * @param classFilter
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes, ClassFilter classFilter) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes, classFilter);
			} else {
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
					if(classFilter != null){
						if(classFilter.matches(clazz)){
							classes.add(clazz);	
						}		
					}else{
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	/*public static void main(String[] args) {
		Set<Class<?>> set = Scanner.getClasses("com.ips.psfp", new ClassFilter(){

			@Override
			public boolean matches(Class<?> clazz) {
				MessageElement annotation = clazz.getAnnotation(MessageElement.class);
				return annotation != null;
			}
			
		});
		for(Class<?> clazz : set){
			System.out.println(clazz.getName());
		}
	}*/
}
