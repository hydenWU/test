package study.dynamics;
// Java动态执行代码字符串
//https://blog.csdn.net/qq_30436011/article/details/126461448
public class Main {
    public static void main(String[] args) throws Exception {
        DynamicCompiler dynamicCompiler = new DynamicCompiler();
        String code = "package study.dynamics;\n" +
                "\n" +
                "/**\n" +
                " * Created by wld on 23/1/6.\n" +
                " */\n" +
                "public class Test {\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"hello world,DynamicCompiler\"\n" + ";" +
                "    }\n" +
                "}\n";
        Class<?> clazz = dynamicCompiler.compileToClass("study.dynamics.Test", code);
        System.out.println(clazz.newInstance());
    }
}