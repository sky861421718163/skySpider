package com.sky.spider.advance.anno;
public class ParseAnn {
    public static void main(String[] args) {
        try {
            // 使用类加载器加载类
            Class c = Class.forName("com.lsky.anno.Child");
            // 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            // 上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
            if (isExist) {
                // 拿到注解实例，解析类上面的注解
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.value());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

