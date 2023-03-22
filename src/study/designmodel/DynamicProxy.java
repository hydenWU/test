package study.designmodel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的。反射机制在生成类的过程中比较高效，而asm在生成类之后的相关执行过程中比较高效（可以通过将asm生成的类进行缓存，这样解决asm生成类过程低效问题）。
jdk动态代理的应用前提是目标类必须基于统一的接口。因此，jdk动态代理有一定的局限性，cglib这种第三方类库实现的动态代理应用更加广泛，且在效率上更有优势。
 */
public class DynamicProxy {
    public static void main(String[] args) {

        InvocationHandler handler =  new JdkProxySubject(new RealSubject());
        ISubject subject =
                (ISubject)java.lang.reflect.Proxy.newProxyInstance(ISubject.class.getClassLoader(),
                        new Class[]{ISubject.class},handler);
        //调用方法
        subject.test();
        subject.toString();
    }
}

  interface ISubject{
    void test();
}
  class RealSubject implements ISubject{
    @Override
    public void test(){
        System.out.println("target method");
    }
}
  class JdkProxySubject implements InvocationHandler {


    //引入要代理的真实对象
    private RealSubject realSubject;

    //用构造器注入目标方法，给我们要代理的真实对象赋初值
    public JdkProxySubject(RealSubject realSubject){
        this.realSubject=realSubject;
    }
    //实现接口的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable{
        System.out.println("before");
        Object result = null;
        try{
            //调用目标方法
            //利用反射构造目标对象
            //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
            result=method.invoke(realSubject,args);

        }catch(Exception e){
            System.out.println("ex:"+e.getMessage());
            throw e;
        }finally{
            System.out.println("after");
        }
        return result;
    }
}

//生成的代理类：
/*
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import proxy.Person;

public final class $Proxy0 extends Proxy implements Person
{
  private static Method m1;
  private static Method m2;
  private static Method m3;
  private static Method m0;

  /**
  *注意这里是生成代理类的构造方法，方法参数为InvocationHandler类型，看到这，是不是就有点明白
  *为何代理对象调用方法都是执行InvocationHandler中的invoke方法，而InvocationHandler又持有一个
  *被代理对象的实例，不禁会想难道是....？ 没错，就是你想的那样。
  *
  *super(paramInvocationHandler)，是调用父类Proxy的构造方法。
  *父类持有：protected InvocationHandler h;
  *Proxy构造方法：
  *    protected Proxy(InvocationHandler h) {
  *         Objects.requireNonNull(h);
  *         this.h = h;
  *     }
  *
  */
//public $Proxy0(InvocationHandler paramInvocationHandler)
//        throws
//{
//    super(paramInvocationHandler);
//}
//
////这个静态块本来是在最后的，我把它拿到前面来，方便描述
//static
//  {
//          try
//          {
//          //看看这儿静态块儿里面有什么，是不是找到了giveMoney方法。请记住giveMoney通过反射得到的名字m3，其他的先不管
//          m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
//          m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
//          m3 = Class.forName("proxy.Person").getMethod("giveMoney", new Class[0]);
//          m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
//          return;
//          }
//          catch (NoSuchMethodException localNoSuchMethodException)
//          {
//          throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
//          }
//          catch (ClassNotFoundException localClassNotFoundException)
//          {
//          throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
//          }
//          }
//
///**
// *
// *这里调用代理对象的giveMoney方法，直接就调用了InvocationHandler中的invoke方法，并把m3传了进去。
// *this.h.invoke(this, m3, null);这里简单，明了。
// *来，再想想，代理对象持有一个InvocationHandler对象，InvocationHandler对象持有一个被代理的对象，
// *再联系到InvacationHandler中的invoke方法。嗯，就是这样。
// */
//public final void giveMoney()
//        throws
//        {
//        try
//        {
//        this.h.invoke(this, m3, null);
//        return;
//        }
//        catch (Error|RuntimeException localError)
//        {
//        throw localError;
//        }
//        catch (Throwable localThrowable)
//        {
//        throw new UndeclaredThrowableException(localThrowable);
//        }
//        }
//
//        //注意，这里为了节省篇幅，省去了toString，hashCode、equals方法的内容。原理和giveMoney方法一毛一样。
//
//        }
// */