import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindSalesman extends JFrame {
    public FindSalesman(){
        setTitle("查询售货员");
        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行查询售货员操作");
        JLabel jl2 = new JLabel("请输入要查询的售货员姓名关键字");
        JTextField jtf = new JTextField();
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            if(jtf.getText().equals("")){
                JOptionPane.showMessageDialog(null,"请正确输入售货员姓名关键字");
            }else {
                ArrayList<String> salesmen = DataBase.findSalesmanByVagueName(jtf.getText());
                if (salesmen.size() <= 0) {
                    JOptionPane.showMessageDialog(null, "不存在此售货员");
                } else {
                    JFrame jFrame = new JFrame("售货员信息");
                    Container container1 = jFrame.getContentPane();

                    Object[][] showSalesmen = new Object[salesmen.size()][2];
                    for(int row =0; row<salesmen.size();row++){
                        StringTokenizer st = new StringTokenizer(salesmen.get(row),"_");
                        showSalesmen[row][0] = st.nextToken();
                        for(int column=1; column<2;column++){
                            showSalesmen[row][column] = st.nextToken();
                        }
                    }
                    String[] tableLabel = {"售货员姓名","售货员密码"};
                    JTable table = new JTable(showSalesmen,tableLabel);
                    table.setEnabled(false);  //设置表格内容不可编辑

                    JButton jb = new JButton("确认");
                    ActionListener al = e1 -> {
                        jFrame.dispose();
                        new FindSalesman();
                    };
                    jb.addActionListener(al);

                    JPanel jPanel = new JPanel();
                    BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
                    jPanel.setLayout(boxLayout);
                    jPanel.add(new JScrollPane(table));
                    jPanel.add(jb);

                    container1.add(jPanel);
                    jFrame.setBounds(500, 200, 600, 400);
                    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    jFrame.setResizable(false);  //页面不可调整大小
                    jFrame.setVisible(true);   //页面可见

                    FindSalesman.this.dispose();
                }
            }
        };
        ActionListener al2 = e -> {
            new SalesmanMaintaining();
            FindSalesman.this.dispose();
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
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }
}
