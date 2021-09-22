import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowAllGoods extends JFrame {

    public ShowAllGoods() {
        this.setTitle("展示商品信息");
        Container container = this.getContentPane();

        JLabel jl = new JLabel("显示所有商品");
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl);

        //处理商品信息 使用JTable表格展示信息
        ArrayList<String> goods = DataBase.showAllGoods();
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
        jPanel.add(new JScrollPane(table));

        //货量不够时提醒补货
        for (int count = 0; count < goods.size(); count++) {
            StringTokenizer st = new StringTokenizer(goods.get(count), "_");
            String gName = st.nextToken();//商品名称
            st.nextToken();//商品价格
            if (Integer.parseInt(st.nextToken()) < 10) {
                JLabel jLabel = new JLabel("缺货提醒：" + gName + " 该商品已不足10件\n");
                jPanel.add(jLabel);
            }
        }

        JButton jb = new JButton("返回上级菜单");
        ActionListener al = e -> {
            new GoodsMaintaining();
            ShowAllGoods.this.dispose();
        };
        jb.addActionListener(al);
        jPanel.add(jb);

        container.add(jPanel);
        setBounds(500, 100, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);  //页面不可调整大小
        setVisible(true);   //页面可见
    }

}
