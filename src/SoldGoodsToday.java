import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SoldGoodsToday extends JFrame {
    public SoldGoodsToday() {
        this.setTitle("当日卖出商品信息");
        Container container = this.getContentPane();

        JLabel jl = new JLabel("今日已售出商品:");

        JButton jb = new JButton("返回上级菜单");
        ActionListener al = e -> {
            new SalesManagement();
            SoldGoodsToday.this.dispose();
        };
        jb.addActionListener(al);

        //表格展示今日售出单品信息
        ArrayList<String> goods = DataBase.getSoldGoodsToday();
        Object[][] soldGoods = new Object[goods.size()][5];
        String gName;
        for (int row = 0; row < goods.size(); row++) {
            StringTokenizer st = new StringTokenizer(goods.get(row), "_");
            gName = st.nextToken();
            soldGoods[row][0] = gName;
            for (int column = 1; column < 5; column++) {
                if (column == 4) {
                    int gQuantity = Integer.parseInt(DataBase.getSingleGoodsQuantity(gName));
                    if (gQuantity < 10) {
                        soldGoods[row][column] = "该商品已不足10件";
                    } else {
                        soldGoods[row][column] = "";
                    }
                } else {
                    soldGoods[row][column] = st.nextToken();
                }
            }
        }
        String[] tableLabel = {"商品名称", "商品价格", "商品数量", "销量", "备注"};
        JTable table = new JTable(soldGoods, tableLabel);

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);

        jPanel.add(jl);
        jPanel.add(new JScrollPane(table));
        jPanel.add(jb);

        container.add(jPanel);
        setBounds(500, 100, 600, 600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
