import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class ChangeSalesman extends JFrame {
    public static JTextField jtf = new JTextField();

    public ChangeSalesman() {
        setTitle("更改售货员");

        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行更改售货员操作");
        JLabel jl2 = new JLabel("输入更改的售货员姓名");
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            //展示售货员信息
            String sName = jtf.getText();
            if (sName.equals("")) {
                JOptionPane.showMessageDialog(null, "请正确输入售货员姓名");
            } else {
                String singleSalesman = DataBase.getSalesman(sName);
                if (singleSalesman.equals("")) {
                    JOptionPane.showMessageDialog(null, "该售货员不存在");
                } else {
                    StringTokenizer st = new StringTokenizer(singleSalesman, "_");
                    JOptionPane.showMessageDialog(null, "售货员姓名:" + st.nextToken() + "\n售货员密码" + st.nextToken());
                    new SuccessFrame();
                }
            }
        };
        ActionListener al2 = e -> {
            new SalesmanMaintaining();
            ChangeSalesman.this.dispose();
        };
        jb1.addActionListener(al1);
        jb2.addActionListener(al2);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl1);
        jPanel.add(jl2);
        jPanel.add(jtf);
        jPanel.add(jb1);
        jPanel.add(jb2);

        container.add(jPanel);
        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * 内部类
     * 查找到指定售货员后修改信息
     */
    class SuccessFrame extends JFrame {
        SuccessFrame() {
            this.setTitle("更改售货员信息");
            Container container = this.getContentPane();

            JLabel jl1 = new JLabel("选择您要更改的内容");
            JLabel j1 = new JLabel("更改售货员姓名");
            JTextField jtf1 = new JTextField();
            JButton jb1 = new JButton("确认");
            JLabel j2 = new JLabel("更改售货员密码");
            JTextField jtf2 = new JTextField();
            JButton jb2 = new JButton("确认");
            JButton jb3 = new JButton("返回上级菜单");

            ActionListener al1 = e -> {
                DataBase.changeSalesmanName(jtf1.getText(), jtf.getText());
            };
            ActionListener al2 = e -> {
                DataBase.changeSalesmanPassword(jtf2.getText(), jtf.getText());
            };
            ActionListener al3 = e -> {
                new ChangeSalesman();
                ChangeSalesman.SuccessFrame.this.dispose();
            };

            jb1.addActionListener(al1);
            jb2.addActionListener(al2);
            jb3.addActionListener(al3);

            JPanel jPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
            jPanel.setLayout(boxLayout);

            jPanel.add(jl1);
            jPanel.add(j1);
            jPanel.add(jtf1);
            jPanel.add(jb1);
            jPanel.add(j2);
            jPanel.add(jtf2);
            jPanel.add(jb2);
            jPanel.add(jb3);

            container.add(jPanel);
            setBounds(500, 100, 600, 600);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
