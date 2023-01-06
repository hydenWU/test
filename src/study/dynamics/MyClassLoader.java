package study.dynamics;

//这里需要注意，默认的ClassLoader的defineClass()方法第一个参数接受的是全限定类名，classData是字节数组
public class MyClassLoader extends ClassLoader {
 
    public Class loadClass(String fullName, MyJavaClassFileObject javaClassObject) {
        byte[] classData = javaClassObject.getBytes();
        return this.defineClass(fullName, classData, 0, classData.length);
    }
}