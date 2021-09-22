import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class DeleteGoods extends JFrame {

    public DeleteGoods() {

        this.setTitle("删除商品");
        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行删除商品操作");
        JLabel jl2 = new JLabel("输入删除的商品名称");
        JTextField jtf1 = new JTextField();
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            //显示商品信息
            String result = DataBase.getGoods(jtf1.getText());
            String gprice;
            String gquantity;
            if (result.equals("")) {
                JOptionPane.showMessageDialog(null, "该商品不存在");
            } else {
                StringTokenizer st = new StringTokenizer(result, "_");
                st.nextToken();
                gprice = st.nextToken();
                gquantity = st.nextToken();
                JOptionPane.showMessageDialog(null, "商品信息:\n商品名称 " + jtf1.getText() + "\n商品价格 " + gprice + "\n商品数量 " + gquantity);
                //确认是否删除商品
                int choice = JOptionPane.showConfirmDialog(null, "是否确定删除", "是否确定删除", JOptionPane.YES_NO_OPTION);
                //确认删除 choice=0 ; 取消删除 choice=1
                if (choice == 0) {
                    if (DataBase.getGoods(jtf1.getText()).equals("")) {
                        JOptionPane.showMessageDialog(null, "该商品不存在");
                    }else{
                        DataBase.deleteGoods(jtf1.getText());

                    }
                }
            }
        };
        ActionListener al2 = e -> {
            DeleteGoods.this.dispose();
            new GoodsMaintaining();
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
