import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SalesManagement extends JFrame {
    public SalesManagement() {
        this.setTitle("购物管理");
        Container container = this.getContentPane();

        JButton jb1 = new JButton("查看已售商品列表");
        JButton jb2 = new JButton("售货员管理");
        JButton jb3 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            new SoldGoodsToday();
            SalesManagement.this.dispose();
        };
        ActionListener al2 = e -> {
            new SalesmanMaintaining();
            SalesManagement.this.dispose();
        };
        ActionListener al3 = e -> {
            SalesManagement.this.dispose();
            new MainPage();
        };
        jb1.addActionListener(al1);
        jb2.addActionListener(al2);
        jb3.addActionListener(al3);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);

        container.add(jPanel);
        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }
}
