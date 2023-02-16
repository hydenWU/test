package study.designmodel;

//单例模式：最佳枚举，其次静态内部类；
public class Single {
    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        manager.doSomething();

        Single2 instance = Single2.instance;
        instance.doSomething();
    }
}

  class Manager {
    private Manager(){}
    public static Manager getInstance(){
        return  ManagerHolder.instance;
    }

    private static class ManagerHolder {
        private static Manager instance = new Manager();
    }

      public void doSomething(){
        System.out.println("doing....");
    }
  }
