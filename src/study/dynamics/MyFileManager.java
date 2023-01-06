package study.dynamics;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

//这里需要重写的方法时getJavaFileOutput()方法，输出我们自己写的Java字节码文件类。
public class MyFileManager extends ForwardingJavaFileManager {
 
    private MyJavaClassFileObject javaClassObject;
 
    protected MyFileManager(StandardJavaFileManager standardJavaFileManager) {
        super(standardJavaFileManager);
    }
 
    public MyJavaClassFileObject getJavaClassObject(){
        return javaClassObject;
    }
 
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        this.javaClassObject = new MyJavaClassFileObject(className, kind);
        return javaClassObject;
    }
}