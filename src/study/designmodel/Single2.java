package study.designmodel;

//单例模式：最佳枚举，其次静态内部类；
 enum Single2 {
    instance;

    public void doSomething(){
      System.out.println("doing from enum....");
    }
}
