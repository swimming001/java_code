import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REPL {
    // read：接收一个字符串，直接返回它（本次作业不做处理）
    public static String READ(String str) {
        return str;
    }

    // eval：接收 AST 和环境，直接返回 AST（本次作业不做处理）
    public static String EVAL(String ast, String env) {
        return ast;
    }

    // print：接收一个字符串，直接返回它（本次作业不做处理）
    public static String PRINT(String exp) {
        return exp;
    }

    // re：依次调用 READ 和 EVAL，返回结果
    public static String RE(String env, String str) {
        return EVAL(READ(str), env);
    }

    // 基础版：循环读取输入，原样输出，输入 "exit" 退出
    public static void REPL() {
        // TODO: YOUR CODE HERE
        // 提示：
        //   1. 使用 BufferedReader + InputStreamReader 读取标准输入
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new InputStreamReader(System.in));
        //   2. 循环读取每一行：
            while(true) {
                String line = reader.readLine();
                //      - 若读取到 null，跳过（continue）
                if (line == null) {
                    continue;
                }
                //      - 若读取到 "exit"，退出循环（break）
                if ("exit".equals(line)) {
                    System.out.println("退出程序~");
                    break;
                }
                //      - 否则，调用 PRINT(RE(null, line)) 并用 System.out.println 输出结果
                String result = PRINT(RE(null, line));
                System.out.println(result);
                //   3. 捕获 IOException，打印错误信息后退出循环
            }
            } catch(IOException e){
                System.err.println("读取输入出错：" + e.getMessage());
                return;
            }finally{
                try{
                    if(reader!=null){
                        reader.close();
                    }
                }catch (IOException e){
                    System.err.println("关闭输入流出错："+e.getMessage());
                }
            }
    }

    // 扩展 1：每次输入前打印行号提示，格式为 [n] >（使用 System.out.print，不换行）
    public static void REPL1() {
        // TODO: YOUR CODE HERE
        // 提示：
        //   1. 在 REPL() 的基础上，增加一个从 1 开始的计数器 lineNum
        //   2. 每次循环开始时，用 System.out.print("[" + lineNum + "] > ") 打印提示
        //   3. 打印提示后将 lineNum 自增
        //   4. 其余逻辑与 REPL() 相同
    }

    // 扩展 2：退出时统计并输出本次会话共处理的行数
    public static void REPL2() {
        // TODO: YOUR CODE HERE
        // 提示：
        //   1. 在 REPL() 的基础上，在循环外定义计数器 count = 0
        //   2. 每次成功处理一行输入（非 null、非 exit）后执行 count++
        //   3. 退出循环后，用 System.out.println 输出：
        //      "共处理 " + count + " 行输入，再见！"
    }

    public static void main(String[] args) {
        REPL();
    }

}

