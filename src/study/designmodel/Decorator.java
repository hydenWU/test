package study.designmodel;

import java.io.BufferedInputStream;
import java.util.Collections;

//io字节流变为缓冲字符流:BufferedInputStream、Collections.synchronizedMap() UnmodifiableCollection
//作用：功能增强，装饰器模式是继承关系的一种替代方案，避免子类膨胀
public class Decorator {
    public static void main(String[] args) {
        input in = new inputstream();
        input dec = new MyDecorator(in);
        dec.print();
    }
}


interface input {
    public void print();
}

 class inputstream implements input {
    public void print() {
        System.out.println("inputstream");
    }
}


 abstract class AbstractDecorator implements input {
    protected input in;
}

//模拟缓冲功能得字节流
class MyDecorator extends AbstractDecorator {
    public MyDecorator(input in){
        this.in = in;
    }
    @Override
    public void print() {
        System.out.println("do before function...");
        in.print();
        System.out.println("do after function...");
    }
}
