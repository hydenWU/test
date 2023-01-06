package study.thread;

//关键点在：SingleTon的3个static变量的顺序，先准备阶段赋值为默认值，初始化解决按顺序执行，
//先singleTon = new SingleTon();调用构造方法，然后赋值cnt2（cnt1不用赋值）
//如果singleTon = new SingleTon();放到后边结果又不一样了
public class ClassLoaderTest {

    public static void main(String[] args) {
        //SingleTon singleTon = SingleTon.getInstance();
        System.out.println(SingleTon.cnt1);
        System.out.println(SingleTon.cnt2);
    }
}

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int cnt1;
    public static int cnt2 = 0;

    private SingleTon() {
        System.out.println("--SingleTon()");
        cnt1++;
        cnt2++;
    }
    public static SingleTon getInstance(){
            return singleTon;
    }

    private static int addCnt2(){
        System.out.println("--addCnt2");
        cnt2++;
        return cnt2;
    }
}