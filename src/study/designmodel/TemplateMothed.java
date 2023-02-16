package study.designmodel;

//模板方法：抽象类，固定算法骨架，差异化子类实现
//jdk：比如AQS、ClassLoader findClass
public class TemplateMothed {
    public static void main(String[] args) {
        PrintFlower p = new MyPrintFlower();
        p.print();
    }
}

abstract class PrintFlower {
    public void print(){
        String color = getColor();
        System.out.println("获取花色："+color);
        System.out.println("打印完成");
    }
    abstract protected String getColor();
}

class MyPrintFlower extends PrintFlower{
    @Override
    protected String getColor(){
        return "red";
    }
}