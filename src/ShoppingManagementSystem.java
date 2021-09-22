import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
   测试类
 */
public class ShoppingManagementSystem {
    private static final PrintWriter stdOut = new PrintWriter(System.out, true);
    private static final PrintWriter stdErr = new PrintWriter(System.err, true);
    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public ShoppingManagementSystem() {
        new MainPage();
        new DataBase();
    }

    public static void main(String[] args) {
        stdOut.println("欢迎使用商品管理系统");
        new ShoppingManagementSystem();
    }

}
