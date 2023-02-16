package study.designmodel;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

//jdk：ThreadPoolExecutor 拒绝策略 RejectedExecutionHandler handler.rejectedExecution(command, this);
//策略模式：算法选择
public class Strategy {
    public static void main(String[] args) {

        PayStrategy wechatPay = new WEchatPay();
        Context context= new Context(wechatPay,"allen",100.0);
        context.pay();

        PayStrategy afbPay = new ZFBPay();
        context= new Context(afbPay,"allen",100.0);
        context.pay();
    }
}

//场景：支付，多种支付模式
class Context {
    private PayStrategy strategy;
    private String user;
    private double money;

    public Context(PayStrategy strategy, String user, double money) {
        this.strategy = strategy;
        this.user = user;
        this.money = money;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void pay(){
        strategy.pay(this);
    }
}

interface PayStrategy {
    void pay(Context cxt);
}

class WEchatPay implements PayStrategy {
    @Override
    public void pay(Context cxt) {
        System.out.println("微信支付："+ cxt.getUser()+":"+cxt.getMoney());
    }
}
class ZFBPay implements PayStrategy {
    @Override
    public void pay(Context cxt) {
        System.out.println("支付宝支付："+ cxt.getUser()+":"+cxt.getMoney());
    }
}


