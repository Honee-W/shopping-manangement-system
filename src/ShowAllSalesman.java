import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowAllSalesman extends JFrame {
    public ShowAllSalesman(){
        setTitle("显示所有售货员");
        Container container = this.getContentPane();

        JLabel jl = new JLabel("执行显示所有售货员操作");

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jPanel.add(jl);

        ArrayList<String> allSalesman = DataBase.showAllSalesman();
        if(allSalesman.size()<=0){
            JOptionPane.showMessageDialog(null,"当前系统内无售货员");
        }else{
            Object[][] showAllSalesman = new Object[allSalesman.size()][2];
            for(int row = 0; row<allSalesman.size();row++ ){
                StringTokenizer st = new StringTokenizer(allSalesman.get(row),"_");
                showAllSalesman[row][0] = st.nextToken();
                for(int column = 1 ;column<2;column++){
                    showAllSalesman[row][column] = st.nextToken();
                }
            }
            String[] tableLabel = {"售货员姓名","售货员密码"};
            JTable jTable = new JTable(showAllSalesman,tableLabel);
            jTable.setEnabled(false);

            jPanel.add(jTable);
        }

        JButton jb = new JButton("确认");
        ActionListener al = e -> {
          ShowAllSalesman.this.dispose();
          new SalesmanMaintaining();
        };
        jb.addActionListener(al);
        jPanel.add(jb);

        container.add(jPanel);
        setBounds(500, 100, 600, 600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
