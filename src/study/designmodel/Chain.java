package study.designmodel;

//责任链模式：使用场景：servlet内的filter，spring中的interceptor都是采用责任链设计模式，多个interceptor是按顺序执行的。
//多条件流程判断：权限控制
public class Chain {
    public static void main(String[] args) {
        AbstractHandler step1 = new Step1();
        AbstractHandler step2 = new Step2();
        step1.setNext(step2);

        step1.handle(90);
    }
}

//抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接
abstract class AbstractHandler {
    protected AbstractHandler next;
    public void setNext( AbstractHandler next){
        this.next = next;
    }
    public abstract void handle(int score);//参数作为上下文内存
}

class Step1 extends AbstractHandler {
    @Override
    public void handle(int score) {
        if (score < 80) {
            System.out.println("Step1 deal, over!");
        } else {
            this.next.handle(score);
        }
    }
}
class Step2 extends AbstractHandler {
    @Override
    public void handle(int score) {
        if (score > 80) {
            System.out.println("Step2 deal, over!");
        }
    }
}