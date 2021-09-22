import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddSalesman extends JFrame {
    public static JTextField jtf1 = new JTextField();
    public static JTextField jtf2 = new JTextField();

    public AddSalesman() {
        setTitle("添加售货员");

        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行添加售货员操作");
        JLabel jl2 = new JLabel("添加售货员姓名:");
        JLabel jl3 = new JLabel("添加售货员密码:");
        JLabel jl4 = new JLabel("是否继续:");


        JButton jb1 = new JButton("是");
        JButton jb2 = new JButton("否");
        JButton jb3 = new JButton("返回上级菜单");
        ActionListener al1 = e -> {
            DataBase.addSalesman();
            AddSalesman.this.dispose();
            new AddSalesman();
        };
        ActionListener al2 = e -> AddSalesman.this.dispose();
        ActionListener al3 = e -> {
            new SalesmanMaintaining();
            AddSalesman.this.dispose();
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
