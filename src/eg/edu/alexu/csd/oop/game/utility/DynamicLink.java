package eg.edu.alexu.csd.oop.game.utility;

import eg.edu.alexu.csd.oop.game.object.Shapes;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DynamicLink {

    private ArrayList<Class<Shapes>> Classes;
    private static DynamicLink dynamicLink;

    private DynamicLink(ArrayList classes) {
        Classes = (ArrayList<Class<Shapes>>) classes;

        String url = "lib";
        File[] files = new File(url).listFiles();

        if (files == null)
            throw new NullPointerException();

        for (File file : files) {
            if (!file.isDirectory() && file.getName().endsWith(".jar")) {
                loadJars(file.getPath());
            }
        }
    }

    public static DynamicLink makeInstance() {
        if (dynamicLink == null) {
            dynamicLink = new DynamicLink(new ArrayList());
        }
        return dynamicLink;
    }

    public void loadJars(String jarPath) {
        try {
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + jarPath + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class loadClass = cl.loadClass(className);
                if (checkInterfaces(loadClass.getInterfaces())) {
                    this.Classes.add((Class<Shapes>) loadClass);
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Cant load");
        }
    }

    private boolean checkInterfaces(Class[] interfaces) {

        ArrayList<String> requiredInterface = new ArrayList<>(Arrays.asList("interface eg.edu.alexu.csd.oop.game.GameObject", "interface eg.edu.alexu.csd.oop.game.object.Shapes"));

        if (interfaces.length == 0) return false;

        for (Class anInterface : interfaces) {
            String replace = anInterface.toString();
            if (!requiredInterface.contains(replace)) {
                return false;
            }
        }
        return true;
    }

    public Shapes getShape(int x, int y) {

        try {
            Class randClass = Classes.get((int) (Math.random() * Classes.size()));
            Constructor[] cons = randClass.getDeclaredConstructors();

            for (Constructor con : cons) {
                if (con.getParameterCount() == 2 && con.getParameterTypes()[0].toString().equals(int.class.toString()) && con.getParameterTypes()[1].toString().equals(int.class.toString())) {
                    return (Shapes) con.newInstance(x, y);
                }
            }
            throw new NullPointerException();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException();
        }
    }

}
