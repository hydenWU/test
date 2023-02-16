package study.designmodel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

//线程池submit，需要callable，如果传入的是runnable，需要适配为callable:new RunnableAdapter<T>(task, result);
//作用：接口转换
public class Adapter {
    public static void main(String[] args) {

        OldUser oldUser = new OldUser();
        Charge jiaofei = new MyAdapter(oldUser);
        jiaofei.charge();

        Charge jiaofei2 = new MyAdapter2();
        jiaofei2.charge();
    }
}
// 目标接口，或称为标准接口
interface Charge {
    public void charge();
}

// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
 class OldUser {
    public void oldcharge() {
        System.out.println("OldUser charge");
    }
}

//对象适配
class MyAdapter implements Charge {
    private OldUser oldUser;

    public MyAdapter(OldUser oldUser) {
        this.oldUser = oldUser;
    }

    @Override
    public void charge() {
        oldUser.oldcharge();
    }
}

//类适配
class MyAdapter2 extends OldUser implements Charge {
    @Override
    public void charge() {
        super.oldcharge();
    }
}
