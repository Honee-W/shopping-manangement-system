import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    int count = 0; //登录次数计数器


    public Register() {
        this.setTitle("前台收银登录");
        Container container = new Container();
        container = this.getContentPane();

        JLabel jl = new JLabel("请注意:用户信息输入错误3次及以上自动退出系统");
        JLabel jl1 = new JLabel("请输入用户名:");
        JTextField jtf1 = new JTextField();
        JLabel jl2 = new JLabel("请输入密码:");
        JTextField jtf2 = new JTextField();
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            int result = -1;//初始用户不存在
            String sName = jtf1.getText();
            String sPassword = jtf2.getText();
            result = DataBase.confirmUser(sName, sPassword);
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "登录成功");
                new Cashier(sName);
                Register.this.dispose();
            } else {
                count++;
                JOptionPane.showMessageDialog(null, "登陆失败\n您还有" + (3 - count) + "次登录机会");
            }
            if (count >= 3) {
                System.exit(0);
            }
        };
        ActionListener al2 = e -> {
            new MainPage();
            Register.this.dispose();
        };
        jb1.addActionListener(al1);
        jb2.addActionListener(al2);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl);
        jPanel.add(jl1);
        jPanel.add(jtf1);
        jPanel.add(jl2);
        jPanel.add(jtf2);
        jPanel.add(jb1);
        jPanel.add(jb2);

        container.add(jPanel);

        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }
}
