import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class AddGoods extends JFrame {
    public static JTextField jtf1 = new JTextField();
    public static JTextField jtf2 = new JTextField();
    public static JTextField jtf3 = new JTextField();


    public AddGoods() {
        setTitle("添加商品");

        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行添加商品操作");
        JLabel jl2 = new JLabel("添加商品名称:");
        JLabel jl3 = new JLabel("添加商品价格:");
        JLabel jl4 = new JLabel("添加商品数量:");
        JLabel jl5 = new JLabel("是否继续:");


        JButton jb1 = new JButton("是");
        JButton jb2 = new JButton("否");
        JButton jb3 = new JButton("返回上级菜单");
        ActionListener al1 = e -> {
            DataBase.addGoods();
            AddGoods.this.dispose();
            new AddGoods();
        };
        ActionListener al2 = e -> AddGoods.this.dispose();
        ActionListener al3 = e -> {
            new GoodsMaintaining();
            AddGoods.this.dispose();
        };
        jb1.addActionListener(al1);
        jb2.addActionListener(al2);
        jb3.addActionListener(al3);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl1);
        jPanel.add(jl2);
        jPanel.add(jtf1);
        jPanel.add(jl3);
        jPanel.add(jtf2);
        jPanel.add(jl4);
        jPanel.add(jtf3);
        jPanel.add(jl5);
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);

        container.add(jPanel);
        setBounds(500, 200, 600, 400);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
