import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class ChangeGoods extends JFrame {
    public static JTextField jtf = new JTextField();

    public ChangeGoods() {
        setTitle("更改商品");

        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行更改商品操作");
        JLabel jl2 = new JLabel("输入更改商品名称");
        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            //展示商品现有信息
            String gName = jtf.getText();
            String result;
            if (gName.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入商品名称");
            } else {
                result = DataBase.getGoods(gName);
                if (result.equals("")) {
                    JOptionPane.showMessageDialog(null, "该商品不存在");
                } else {
                    StringTokenizer st = new StringTokenizer(result, "_");
                    st.nextToken();
                    String gprice = st.nextToken();
                    String gquantity = st.nextToken();
                    JOptionPane.showMessageDialog(null, "商品名称：" + gName + "\n" + "商品价格：" + gprice + "\n" + "商品数量：" + gquantity);

                    ChangeGoods.this.dispose();
                    //修改商品信息
                    new SuccessFrame();
                }
            }

        };
        ActionListener al2 = e -> {
            ChangeGoods.this.dispose();
            new GoodsMaintaining();
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
     * 查询成功，更改商品信息
     */

    class SuccessFrame extends JFrame {
        SuccessFrame() {
            setTitle("更改商品信息");

            Container container = this.getContentPane();

            JLabel jl1 = new JLabel("选择您要更改的内容");
            JLabel j1 = new JLabel("更改商品名称");
            JTextField jtf1 = new JTextField();
            JButton jb1 = new JButton("确认");
            JLabel j2 = new JLabel("更改商品价格");
            JTextField jtf2 = new JTextField();
            JButton jb2 = new JButton("确认");
            JLabel j3 = new JLabel("更改商品数量");
            JTextField jtf3 = new JTextField();
            JButton jb3 = new JButton("确认");
            JButton jb4 = new JButton("返回上级菜单");

            ActionListener al1 = e -> {
                DataBase.changeGoodsName(jtf1.getText(), jtf.getText());
            };
            ActionListener al2 = e -> {
                DataBase.changeGoodsPrice(Double.parseDouble(jtf2.getText()), jtf.getText());
            };
            ActionListener al3 = e -> {
                DataBase.changeGoodsQuantity(Double.parseDouble(jtf3.getText()), jtf.getText());
            };
            ActionListener al4 = e -> {
                new ChangeGoods();
                SuccessFrame.this.dispose();
            };

            jb1.addActionListener(al1);
            jb2.addActionListener(al2);
            jb3.addActionListener(al3);
            jb4.addActionListener(al4);

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
            jPanel.add(j3);
            jPanel.add(jtf3);
            jPanel.add(jb3);
            jPanel.add(jb4);

            container.add(jPanel);
            setBounds(500, 100, 600, 600);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

}
