import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Cashier extends JFrame {
    String sName; //售货员姓名
    JTextField jtf = new JTextField(); //商品名称

    public Cashier(String sName) {
        this.setTitle("购物结算");
        Container container = this.getContentPane();

        this.sName = sName;

        JLabel jl = new JLabel("请输入商品关键字");
        JButton jb = new JButton("确认");
        ActionListener al = e -> {
            new PurchaseFrame();
            Cashier.this.dispose();
        };
        jb.addActionListener(al);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl);
        jPanel.add(jtf);
        jPanel.add(jb);

        container.add(jPanel);
        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }

    /**
     * 内部类 购买界面
     */
    class PurchaseFrame extends JFrame {
        double totalPrice;

        PurchaseFrame() {
            this.setTitle("购物结算");
            Container container = this.getContentPane();

            ArrayList<String> goods = DataBase.findGoodsByVagueName(jtf.getText());
            Object[][] showGoods = new Object[goods.size()][3];
            for (int row = 0; row < goods.size(); row++) {
                StringTokenizer st = new StringTokenizer(goods.get(row), "_");
                showGoods[row][0] = st.nextToken();
                for (int column = 1; column < 3; column++) {
                    showGoods[row][column] = st.nextToken();
                }
            }
            String[] columnLabel = {"商品名称", "商品价格", "商品数量"};
            JTable ShowGoods = new JTable(showGoods, columnLabel);
            ShowGoods.setEnabled(false); //内容不可编辑

            JLabel jl1 = new JLabel("请选择商品:");
            JTextField jtf1 = new JTextField();
            JLabel jl2 = new JLabel("请输入购买数量:");
            JTextField jtf2 = new JTextField();
            JButton jb1 = new JButton("查询应付总金额");
            JLabel jl3 = new JLabel("请输入实际缴费金额:");
            JTextField jtf3 = new JTextField();
            JButton jb2 = new JButton("找钱");
            JButton jb3 = new JButton("返回上级菜单");

            ActionListener al1 = e -> {
                String singleGoods = DataBase.getGoods(jtf1.getText());
                StringTokenizer st = new StringTokenizer(singleGoods, "_");
                st.nextToken();
                double gPrice = Double.parseDouble(st.nextToken());
                int gQuantity = Integer.parseInt(jtf2.getText());
                totalPrice = gPrice * gQuantity;
                JOptionPane.showMessageDialog(null, "总计: " + totalPrice);
            };
            ActionListener al2 = e -> {
                if (totalPrice > Double.parseDouble(jtf3.getText())) {
                    JOptionPane.showMessageDialog(null, "余额不足");
                } else {
                    JOptionPane.showMessageDialog(null, "找零:" + (Double.parseDouble(jtf3.getText()) - totalPrice));
                    DataBase.purchaseGoods(sName, jtf1.getText(), Integer.parseInt(jtf2.getText()));
                }
            };
            ActionListener al3 = e -> {
                new Cashier(sName);
                PurchaseFrame.this.dispose();
            };
            jb1.addActionListener(al1);
            jb2.addActionListener(al2);
            jb3.addActionListener(al3);

            JPanel jPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
            jPanel.setLayout(boxLayout);
            jPanel.add(new JScrollPane(ShowGoods));
            jPanel.add(jl1);
            jPanel.add(jtf1);
            jPanel.add(jl2);
            jPanel.add(jtf2);
            jPanel.add(jb1);
            jPanel.add(jl3);
            jPanel.add(jtf3);
            jPanel.add(jb2);
            jPanel.add(jb3);

            container.add(jPanel);
            setBounds(500, 100, 600, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);  //页面不可调整大小
            setVisible(true);   //页面可见
        }
    }
}
