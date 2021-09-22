import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindGoods extends JFrame {

    public FindGoods() {
        setTitle("查询商品");
        Container container = this.getContentPane();

        JLabel jl1 = new JLabel("执行查询商品操作");
        JButton jb1 = new JButton("按商品数量升序查询");
        JButton jb2 = new JButton("按商品价格升序查询");
        JLabel jl2 = new JLabel("按关键字查询商品");
        JTextField jtf = new JTextField();
        JButton jb3 = new JButton("确认");
        JButton jb4 = new JButton("返回上级菜单");

        ActionListener al1 = e -> {
            JFrame jf1 = new JFrame("商品信息");
            Container c1;
            c1 = jf1.getContentPane();
            JPanel j1 = new JPanel();

            //处理商品信息 使用JTable表格展示信息
            ArrayList<String> goods = DataBase.showGoodsByQuantityASC();
            Object[][] showGoods = new Object[goods.size()][3];
            for (int row = 0; row < goods.size(); row++) {
                StringTokenizer st = new StringTokenizer(goods.get(row), "_");
                showGoods[row][0] = st.nextToken();
                for (int column = 1; column < 3; column++) {
                    showGoods[row][column] = st.nextToken();
                }
            }
            //JTable初始化
            String[] name = {"商品名称", "商品价格", "商品数量"};
            JTable table = new JTable(showGoods, name);
            table.setEnabled(false);  //设置表格内容不可编辑
            j1.add(new JScrollPane(table));

            JButton jb = new JButton("返回上级菜单");
            ActionListener a = e1 -> {
                new FindGoods();
                jf1.dispose();
            };
            jb.addActionListener(a);
            j1.add(jb);

            c1.add(j1);

            jf1.setBounds(500, 200, 600, 400);
            jf1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf1.setResizable(false);  //页面不可调整大小
            jf1.setVisible(true);   //页面可见
            FindGoods.this.dispose();

        };
        ActionListener al2 = e -> {
            JFrame jf2 = new JFrame("商品信息");
            Container c2;
            c2 = jf2.getContentPane();
            JPanel j2 = new JPanel();

            //处理商品信息 使用JTable表格展示信息
            ArrayList<String> goods = DataBase.showGoodsByPriceASC();
            Object[][] showGoods = new Object[goods.size()][3];
            for (int row = 0; row < goods.size(); row++) {
                StringTokenizer st = new StringTokenizer(goods.get(row), "_");
                showGoods[row][0] = st.nextToken();
                for (int column = 1; column < 3; column++) {
                    showGoods[row][column] = st.nextToken();
                }
            }
            //JTable初始化
            String[] name = {"商品名称", "商品价格", "商品数量"};
            JTable table = new JTable(showGoods, name);
            table.setEnabled(false);  //设置表格内容不可编辑
            j2.add(new JScrollPane(table));

            JButton jb = new JButton("返回上级菜单");
            ActionListener a = e2 -> {
                new FindGoods();
                jf2.dispose();
            };
            jb.addActionListener(a);
            j2.add(jb);

            c2.add(j2);

            jf2.setBounds(500, 200, 600, 400);
            jf2.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf2.setResizable(false);  //页面不可调整大小
            jf2.setVisible(true);   //页面可见
            FindGoods.this.dispose();

        };
        ActionListener al3 = e -> {
            JFrame jf3 = new JFrame("商品信息");
            Container c3;
            c3 = jf3.getContentPane();
            JPanel j3 = new JPanel();

            //处理商品信息 使用JTable表格展示信息
            ArrayList<String> goods = DataBase.findGoodsByVagueName(jtf.getText());
            Object[][] showGoods = new Object[goods.size()][3];
            for (int row = 0; row < goods.size(); row++) {
                StringTokenizer st = new StringTokenizer(goods.get(row), "_");
                showGoods[row][0] = st.nextToken();
                for (int column = 1; column < 3; column++) {
                    showGoods[row][column] = st.nextToken();
                }
            }
            //JTable初始化
            String[] name = {"商品名称", "商品价格", "商品数量"};
            JTable table = new JTable(showGoods, name);
            table.setEnabled(false);  //设置表格内容不可编辑
            j3.add(new JScrollPane(table));

            JButton jb = new JButton("返回上级菜单");
            ActionListener a = e3 -> {
                new GoodsMaintaining();
                jf3.dispose();
            };
            jb.addActionListener(a);
            j3.add(jb);

            c3.add(j3);

            jf3.setBounds(500, 200, 600, 400);
            jf3.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf3.setResizable(false);  //页面不可调整大小
            jf3.setVisible(true);   //页面可见
            FindGoods.this.dispose();

        };
        ActionListener al4 = e -> {
            FindGoods.this.dispose();
            new GoodsMaintaining();
        };

        jb1.addActionListener(al1);
        jb2.addActionListener(al2);
        jb3.addActionListener(al3);
        jb4.addActionListener(al4);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl1);
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jl2);
        jPanel.add(jtf);
        jPanel.add(jb3);
        jPanel.add(jb4);

        container.add(jPanel);
        setBounds(500, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见

    }
}
