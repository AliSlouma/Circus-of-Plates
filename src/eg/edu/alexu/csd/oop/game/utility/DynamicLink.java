package eg.edu.alexu.csd.oop.game.utility;

import eg.edu.alexu.csd.oop.game.object.Shape;
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

    private ArrayList<Class<? extends Shapes>> Classes;
    private static DynamicLink dynamicLink;

    private DynamicLink(ArrayList classes) {
        Classes = (ArrayList<Class<? extends Shapes>>) classes;

        String url = "C:\\Users\\3arrows\\IdeaProjects\\Circus-Of-Plates007";
        File[] files = new File(url).listFiles();

        if (files == null)
            throw new NullPointerException();

        for (File file :  files)
        {
            if (!file.isDirectory() && file.getName().endsWith(".jar")) {
                loadJars(file.getPath());
            }
        }
    }

    public static DynamicLink makeInstance()
    {
        if (dynamicLink == null)
        {
            dynamicLink = new DynamicLink(new ArrayList());
        }
        return dynamicLink;
    }

    public void loadJars(String jarPath)
    {
        try {
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = { new URL("jar:file:" + jarPath +"!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                Class loadClass = cl.loadClass(className);
                if (checkInterfaces(loadClass.getInterfaces(), className.replaceAll("\\..*", ""))){
                    this.Classes.add(loadClass);
                }
            }
        } catch (ClassNotFoundException | IOException e) {

        }
    }

    private boolean checkInterfaces(Class[] interfaces, String jarName) {

        ArrayList<String> requiredInterface = new ArrayList<>(Arrays.asList("GameObject", "Shapes"));

        if (interfaces.length == 0) return false;
        int k = 0;

        for (Class anInterface : interfaces) {
            String replace = anInterface.toString().replace( "interface "+ jarName + ".", "");
            if (!requiredInterface.contains(replace)) {
                System.out.println(replace + '\n');
                return false;
            }
            else k++;

            if (k == requiredInterface.size())
            {
                break;
            }
        }
        return true;
    }

    public Shapes getShape(int x, int y) {

        try {
            Class randClass = Classes.get((int) (Math.random() * Classes.size()));
            Constructor[] cons = randClass.getDeclaredConstructors();
            Shapes shape = null;

            for (int i = 0; i < cons.length; i++)
            {
                if (cons[i].getParameterCount() == 2 && cons[i].getParameterTypes()[0].toString().equals(int.class.toString()) && cons[i].getParameterTypes()[1].toString().equals(int.class.toString())) {
                    shape = cons[i].newInstance(x, y);
                }
            }
            return shape;
            throw new NullPointerException();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException();
        }
    }
    private <T> T castObj(Object o) throws IOException, ClassNotFoundException {
        if (o != null) {
            ByteArrayOutputStream baous = new ByteArrayOutputStream();
            {
                ObjectOutputStream oos = new ObjectOutputStream(baous);
                try {
                    oos.writeObject(o);
                } finally {
                    try {
                        oos.close();
                    } catch (Exception e) {
                    }
                }
            }

            byte[] bb = baous.toByteArray();
            if (bb != null && bb.length > 0) {
                ByteArrayInputStream bais = new ByteArrayInputStream(bb);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return (T) ois.readObject();
            }
        }
        return null;
    }
}
