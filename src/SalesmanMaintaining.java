import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SalesmanMaintaining extends JFrame {
    public SalesmanMaintaining() {
        this.setTitle("售货员管理");
        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("1.添加售货员");
        JLabel jl2 = new JLabel("2.更改售货员");
        JLabel jl3 = new JLabel("3.删除售货员");
        JLabel jl4 = new JLabel("4.显示所有售货员");
        JLabel jl5 = new JLabel("5.查询售货员");
        JLabel jl6 = new JLabel("请选择，在下方输入数字");
        JTextField jtf = new JTextField();
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            switch (jtf.getText()) {
                case "1":
                    new AddSalesman();
                    SalesmanMaintaining.this.dispose();
                    break;
                case "2":
                    new ChangeSalesman();
                    SalesmanMaintaining.this.dispose();
                    break;
                case "3":
                    new DeleteSalesman();
                    SalesmanMaintaining.this.dispose();
                    break;
                case "4":
                    new ShowAllSalesman();
                    SalesmanMaintaining.this.dispose();
                    break;
                case "5":
                    new FindSalesman();
                    SalesmanMaintaining.this.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "请重新输入数字");
            }
        };
        ActionListener al2 = e -> {
            SalesmanMaintaining.this.dispose();
            new SalesManagement();
        };
        jb1.addActionListener(al1);
        jb2.addActionListener(al2);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);

        jPanel.add(jl1);
        jPanel.add(jl2);
        jPanel.add(jl3);
        jPanel.add(jl4);
        jPanel.add(jl5);
        jPanel.add(jl6);
        jPanel.add(jtf);
        jPanel.add(jb1);
        jPanel.add(jb2);

        container.add(jPanel);
        setBounds(500, 100, 600, 600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
