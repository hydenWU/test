package study.dynamics;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

//这里需要重写openOutStream()方法，不输出字节码文件到文件，而是直接保存在一个输出流中。
public class MyJavaClassFileObject extends SimpleJavaFileObject {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
 
    public MyJavaClassFileObject(String name, JavaFileObject.Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
    }
 
    public byte[] getBytes() {
        return outputStream.toByteArray();
    }
 
    //编译时候会调用openOutputStream获取输出流,并写数据
    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }
}