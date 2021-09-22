import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class DeleteSalesman extends JFrame {
    public DeleteSalesman() {
        this.setTitle("删除售货员");
        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行删除售货员操作");
        JLabel jl2 = new JLabel("输入删除的售货员姓名");
        JTextField jtf1 = new JTextField();
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            //显示售货员信息
            String result = DataBase.getSalesman(jtf1.getText());
            String sname;
            String spassword;
            if (result.equals("")) {
                JOptionPane.showMessageDialog(null, "该售货员不存在");
            } else {
                StringTokenizer st = new StringTokenizer(result, "_");
                sname = st.nextToken();
                spassword = st.nextToken();
                JOptionPane.showMessageDialog(null, "售货员信息:"+"售货员姓名 " + sname + "\n售货员密码 " + spassword);
                //确认是否删除商品
                int choice = JOptionPane.showConfirmDialog(null, "是否确定删除", "是否确定删除", JOptionPane.YES_NO_OPTION);
                //确认删除 choice=0 ; 取消删除 choice=1
                if (choice == 0) {
                    if (DataBase.getSalesman(jtf1.getText()).equals("")) {
                        JOptionPane.showMessageDialog(null, "该售货员不存在");

                    }else{
                        DataBase.deleteSalesman(jtf1.getText());
                    }
                }
            }
        };
        ActionListener al2 = e -> {
            DeleteSalesman.this.dispose();
            new SalesManagement();
        };

        jb1.addActionListener(al1);
        jb2.addActionListener(al2);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl1);
        jPanel.add(jl2);
        jPanel.add(jtf1);
        jPanel.add(jb1);
        jPanel.add(jb2);

        container.add(jPanel);

        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }
}
