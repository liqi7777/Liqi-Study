package com.example.loadclass.classLoadTest;

/*
@author :yinzhengjie
Blog:http://www.cnblogs.com/yinzhengjie/tag/Java%E5%9F%BA%E7%A1%80/
EMAIL:y1053419035@qq.com
*/

/**
 * 此类用于演示获取class文件对象的三种方式:
 * 　　方式一:
 * 　　　　　　通过Object类中的getObject()方法
 * 　　方式二:
 * 　　　　　　通过 类名.class 获取到字节码文件对象（任意数据类型都具备一个class静态属性,看上去要比第一种方式简单）。
 * 　　方式三:
 * 　　　　　　通过Class类中的方法（将类名作为字符串传递给Class类中的静态方法forName即可）。
 * 　　第三种和前两种的区别：
 * 前两种你必须明确Person类型.后面是指定这种类型的字符串就行.这种扩展更强.我不需要知道你的类.我只提供字符串,按照配置文件加载就可以了。
 */
public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取class文件对象的方式一:通过某个对象获取
        Person p = new Person();
        Class c1 = p.getClass();    //通过调用Person类的父类的方法getClass获取文件对象
        System.out.println(c1);

        //获取class文件对象的方式二:通过类名获取
        Class c2 = Person.class;    //每个类型，包括基本和引用数据类型，都会赋予这个类型一个静态的属性，属性名字为class。
        System.out.println(c2);

        //获取class文件对象的方式三:通过Class类的静态方法获取
        Class c3 = Class.forName("com.example.loadclass.classLoadTest.Person");  //里面传入的字符串必须是你想要获取class文件对象的完整路径

        //c1和c2还有c3使用的都是同一个文件对象，因为这个文件对象在执行之前，只加载一次！因此下面的输出全为真！
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
        System.out.println(c2 == c3);
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
        System.out.println(c2.equals(c3));
    }
}
