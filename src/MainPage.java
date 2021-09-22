import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainPage extends JFrame {

    public MainPage() {

        //设置页面外部框架
        this.setTitle("系统主界面");


        //设置页面内容面板
        Container container = this.getContentPane();


        JButton jb1 = new JButton("商品维护");
        JButton jb2 = new JButton("前台收银");
        JButton jb3 = new JButton("购物管理");
        JButton jb4 = new JButton("退出");

        ActionListener al1 = e -> {
            new GoodsMaintaining();
            MainPage.this.dispose();//打开新页面同时关闭上级页面
        };

        ActionListener al2 = e -> {
            new Register();
            MainPage.this.dispose();
        };

        ActionListener al3 = e -> {
            new SalesManagement();
            MainPage.this.dispose();
        };

        ActionListener al4 = e -> System.exit(1);

        jb1.addActionListener(al1);
        jb2.addActionListener(al2);
        jb3.addActionListener(al3);
        jb4.addActionListener(al4);

        //JFrame中加Jpanel；Jpanel中加按钮文字框等控件

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);

        container.add(jPanel);


        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见

    }

}
