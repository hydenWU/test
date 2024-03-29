package study.dynamics;

import java.lang.*;
import java.util.Arrays;
import java.util.List;
 
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
 
public class ScriptEngineTest {
 
public static void main(String[] args) throws Exception {
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine engine = sem.getEngineByName("javascript");     //python or jython,  向上下文中存入变量
   engine.put("msg", "just a test");
   //定义类user
   String str = "msg += '!!!';var user = {name:'tom',age:23,hobbies:['football','basketball']}; ";
   engine.eval(str);
 
   //从上下文引擎中取值
   String msg = (String) engine.get("msg");
//   String name = (String) engine.get("user");
//   String[] hb = (String[]) engine.get("hobbies");
   System.out.println(msg);
  System.out.println(engine.get("user") );
 
   //定义数学函数
   engine.eval("function add (a, b) {c = a + b; return c; }");
 
    //取得调用接口
    Invocable jsInvoke = (Invocable) engine;
 
  //定义加法函数
 
  Object result1 = jsInvoke.invokeFunction("add", new Object[] { 10, 5 });
 
  System.out.println(result1);
 
  //调用加法函数,注意参数传递的方法
 
//  Adder adder = jsInvoke.getInterface(Adder.class);
//
//  int result2 = adder.add(10, 35);
//
//  System.out.println(result2);
 
  //定义run()函数
 
  engine.eval("function run() {print('www.java2s.com');}");
 
  Invocable invokeEngine = (Invocable) engine;
 
  Runnable runner = invokeEngine.getInterface(Runnable.class);
  //定义线程运行之
 
  Thread t = new Thread(runner);
 
  t.start();
 
  t.join();
 
  //导入其他java包
 
  String jsCode = "importPackage(java.util); var list2 = Arrays.asList(['A', 'B', 'C']); ";
 
  engine.eval(jsCode);
 
  List<String> list2 = (List<String>) engine.get("list2");
 
  for (String val : list2) { System.out.println(val);}
 
  }
 
}