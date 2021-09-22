import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GoodsMaintaining extends JFrame {

    public GoodsMaintaining() {

        this.setTitle("商品维护");

        Container container = this.getContentPane();

        JButton jb1 = new JButton("添加商品");
        JButton jb2 = new JButton("更改商品");
        JButton jb3 = new JButton("删除商品");
        JButton jb4 = new JButton("显示所有商品");
        JButton jb5 = new JButton("查询商品");
        JButton jb6 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            new AddGoods();
            GoodsMaintaining.this.dispose();
        };
        ActionListener al2 = e -> {
            new ChangeGoods();
            GoodsMaintaining.this.dispose();
        };
        ActionListener al3 = e -> {
            new DeleteGoods();
            GoodsMaintaining.this.dispose();
        };
        ActionListener al4 = e -> {
            new ShowAllGoods();
            GoodsMaintaining.this.dispose();
        };
        ActionListener al5 = e -> {
            new FindGoods();
            GoodsMaintaining.this.dispose();
        };
        ActionListener al6 = e -> {
            GoodsMaintaining.this.dispose();
            new MainPage();
        };

        jb1.addActionListener(al1);
        jb2.addActionListener(al2);
        jb3.addActionListener(al3);
        jb4.addActionListener(al4);
        jb5.addActionListener(al5);
        jb6.addActionListener(al6);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);

        container.add(jPanel);

        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }

}
