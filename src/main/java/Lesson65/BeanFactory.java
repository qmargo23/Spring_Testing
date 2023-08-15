package Lesson65;

import Lesson65.annotations.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private static Map<String, Object> beansPool = new HashMap<>();

    public BeanFactory(String path) {


        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        String path = "Lesson65";//basePackage - базовый пакет
        {
            Enumeration<URL> res = null;
            try {
                res = classLoader
                        .getResources(path.replace('.', '/'));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (res.hasMoreElements()) {
                File dir = null;
                try {
                    dir = new File(res.nextElement().toURI());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                for (File f : dir.listFiles()) {
                    if (f.getName().endsWith("class")) {
                        String fileName = f.getName()
                                .substring(0, f.getName().lastIndexOf("."));
                        Class classObj = null;
                        try {
                            classObj = Class.forName(path + "." + fileName);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        if (classObj.isAnnotationPresent(Component.class)) {
                            Object obj = null;
                            try {
                                obj = classObj.newInstance();
                            } catch (InstantiationException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            beansPool.put(fileName.toLowerCase(), obj);
                        }
                    }
                }
            }

        }

    }

    public Object getBean(String beanName) {
        return beansPool.get(beanName.toLowerCase());
    }

    public static void main(String[] args){
//        beansPool.forEach((key,value)-> System.out.println(key));
    }
}




//        String path = "C:\\Users\\Elen\\IdeaProjects\\Spring_Testing\\src\\main\\java\\Lesson65";
//        File dir = new File(path);
//        for (String s : dir.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.toLowerCase().endsWith("java");
//            }
//        })) {
//            System.out.println(s);
