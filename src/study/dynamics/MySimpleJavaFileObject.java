package study.dynamics;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

//自定义的Java源码文件类
public class MySimpleJavaFileObject extends SimpleJavaFileObject {
 
    private String contents = null;
    private String className;
 
    public MySimpleJavaFileObject(String className, String contents) {
        super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.className = className;
        this.contents = contents;
    }
 
    public CharSequence getCharContent(boolean ignoredEncodingErrors) throws IOException {
        return contents;
    }
 
    public String getClassName() {
        return className;
    }
}