import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * 连接数据库
 * 提供更改后台数据的方法
 */

/**
 * 正确连接的同时需要导入对应数据库的jre
 */
public class DataBase {
    static final String url = "jdbc:postgresql://localhost:5432/shoppingmanagementsystem";
    static final String username = "postgres";
    static final String password = "wzq20010915";
    static ResultSet resultSet = null;
    static Connection connection = null;
    static Statement statement = null;

    public DataBase() {

        try {
            /*连接数据库的几句均需要写在方法里*/
            //Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            //Create a connection to the database
            connection = DriverManager.getConnection(url, username, password);
            //Create a statement object
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用POSTGRESQL时  模式名.关系名  方可正常使用!!
     * 添加商品
     */
    public static void addGoods() {
        double gid = Math.round(Math.random() * Math.pow(10, 5)); //自动生成5位ID
        String SQLUpdate;
        //!!!!字符串比较使用equals()方法
        // 错误示范1 AddGoods.jtf1.getText() != null
        // 错误示范2 AddGoods.jtf1.getText().equals(null)
        // null 不等于 ""
        if (AddGoods.jtf1.getText().equals("") || Double.parseDouble(AddGoods.jtf2.getText()) < 0 || Integer.parseInt(AddGoods.jtf3.getText()) < 0 || AddGoods.jtf2.getText().equals("") || AddGoods.jtf3.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "不符合规则，商品添加失败!");
        } else {
            if (gid > 10000) {
                SQLUpdate = "INSERT INTO public.goods VALUES"
                        + "(" + "'" + gid + "'" + "," + "'" + AddGoods.jtf1.getText() + "'" + "," + "'" + Double.parseDouble(AddGoods.jtf2.getText()) + "'" + ","
                        + "'" + Integer.parseInt(AddGoods.jtf3.getText()) + "'" + ")";
            } else {
                SQLUpdate = "INSERT INTO public.goods VALUES"
                        + "(" + "'" + gid * 10 + "'" + "'" + AddGoods.jtf1.getText() + "'" + "," + "'" + Double.parseDouble(AddGoods.jtf2.getText()) + "'" + ","
                        + "'" + Integer.parseInt(AddGoods.jtf3.getText()) + "'" + ")";
            }
            try {
                statement.executeUpdate(SQLUpdate);
                JOptionPane.showMessageDialog(null, "商品添加成功!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, "连接数据库出错，商品添加失败!");
            }
        }

    }

    /**
     * 查询商品信息
     *
     * @param gName
     * @return
     */
    public static String getGoods(String gName) {
        String SQLQuery = "SELECT * FROM public.goods WHERE gname=" + "'" + gName + "'";
        String gname = null;
        double gprice = 0;
        double gquantity = 0;
        String result = null;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                gname = resultSet.getString(2);
                gprice = resultSet.getDouble(3);
                gquantity = resultSet.getDouble(4);
                result = gname + "_" + gprice + "_" + gquantity;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "该商品不存在");
        }
        return result;
    }

    /**
     * 修改商品名称
     *
     * @param NewName
     * @param OldName
     */
    public static void changeGoodsName(String NewName, String OldName) {
        String SQLUpdate = "UPDATE public.goods SET gname=" + "'" + NewName + "'"
                + "WHERE gname=" + "'" + OldName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "修改成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    /**
     * 修改商品价格
     *
     * @param price
     * @param OldName
     */
    public static void changeGoodsPrice(double price, String OldName) {
        String SQLUpdate = "UPDATE public.goods SET gprice = " + price + "WHERE gname=" + "'" + OldName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "修改成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    /**
     * 修改商品数量
     *
     * @param quantity
     * @param OldName
     */
    public static void changeGoodsQuantity(double quantity, String OldName) {
        String SQLUpdate = "UPDATE public.goods SET gquantity = " + quantity + "WHERE gname=" + "'" + OldName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "修改成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    /**
     * 删除商品
     *
     * @param gName
     */
    public static void deleteGoods(String gName) {
        String SQLUpdate = "DELETE FROM public.goods WHERE gname=" + "'" + gName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "删除成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "删除失败");
        }
    }

    /**
     * 展示所有商品
     *
     * @return
     */
    public static ArrayList<String> showAllGoods() {
        ArrayList<String> goods = new ArrayList<>();

        String SQLQuery = "SELECT gname,gprice,gquantity FROM public.goods";
        String singleGoods;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                //通过列名返回信息
                singleGoods = resultSet.getString("gname") + "_" + resultSet.getString("gprice") + "_" + resultSet.getString("gquantity");
                goods.add(singleGoods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return goods;
    }

    /**
     * 按照商品数量升序展示所有商品
     *
     * @return ORDER BY 某一列的值 ASC-------降序
     * ORDER BY 某一列的值 DESC------升序
     */
    public static ArrayList<String> showGoodsByQuantityASC() {
        ArrayList<String> goods = new ArrayList<>();

        String SQLQuery = "SELECT gname,gprice,gquantity FROM public.goods ORDER BY gquantity ASC";
        String singleGoods;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                //通过列名返回信息
                singleGoods = resultSet.getString("gname") + "_" + resultSet.getString("gprice") + "_" + resultSet.getString("gquantity");
                goods.add(singleGoods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return goods;
    }

    /**
     * 按照商品价格升序展示所有商品
     *
     * @return
     */
    public static ArrayList<String> showGoodsByPriceASC() {
        ArrayList<String> goods = new ArrayList<>();

        String SQLQuery = "SELECT gname,gprice,gquantity FROM public.goods ORDER BY gprice ASC";
        String singleGoods;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                //通过列名返回信息
                singleGoods = resultSet.getString("gname") + "_" + resultSet.getString("gprice") + "_" + resultSet.getString("gquantity");
                goods.add(singleGoods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return goods;
    }

    /**
     * 通过商品名称模糊查询商品
     *
     * @param VagueName
     * @return LIKE / NOT LIKE
     * % 表示任意个字符  _ 表示单个任意字符
     */
    public static ArrayList<String> findGoodsByVagueName(String VagueName) {
        String SQLQuery = "SELECT gname,gprice,gquantity FROM public.goods WHERE gname LIKE" + "'%" + VagueName + "%'";
        ArrayList<String> goods = new ArrayList<>();
        String singleGoods;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                singleGoods = resultSet.getString("gname") + "_" + resultSet.getString("gprice") + "_" + resultSet.getString("gquantity");
                goods.add(singleGoods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return goods;
    }

    /**
     * 通过商品名称查询sid
     *
     * @param sName
     * @return
     */
    public static String getGoodsId(String sName) {
        String SQLQuery = "SELECT gid FROM public.goods WHERE gname =" + "'" + sName + "'";
        String gid = null;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                gid = resultSet.getString("gid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return gid;
    }

    /**
     * 通过商品名称查询商品数量
     *
     * @param gName
     * @return
     */
    public static String getSingleGoodsQuantity(String gName) {
        String SQLQuery = "SELECT gquantity FROM public.goods WHERE gname=" + "'" + gName + "'";
        String gQuantity = null;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                gQuantity = resultSet.getString("gquantity");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return gQuantity;
    }

    /**
     * 用户登录确认
     *
     * @param sName
     * @param sPassword
     * @return
     */
    public static int confirmUser(String sName, String sPassword) {
        String SQLQuery = "SELECT * FROM public.salesman WHERE sname=" + "'" + sName + "' AND spaswd=" + "'" + sPassword + "'";
        int result = -1; //初始为不存在用户
        try {
            resultSet = statement.executeQuery(SQLQuery);
            //初始指向第0行 调用resultSet.next()后指向第1行
            while (resultSet.next()) {
                String truePassword = resultSet.getString("spaswd");
                String trueName = resultSet.getString("sname");
                if (trueName.equals(sName) && truePassword.equals(sPassword)) {
                    result = 0;
                } else {
                    result = -1;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "不存在该用户");
            return -1;
        }
        return result;
    }

    /**
     * 通过售货员姓名查询 sid
     *
     * @param sName
     * @return
     */
    public static String getSalesmanId(String sName) {
        String SQLQuery = "SELECT sid FROM public.salesman WHERE sname =" + "'" + sName + "'";
        String sid = null;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                sid = resultSet.getString("sid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return sid;
    }

    /**
     * 购买特定数量的特定商品
     *
     * @param gName
     * @param gQuantity
     */
    public static void purchaseGoods(String sName, String gName, int gQuantity) {
        String SQLUpdate1 = "UPDATE public.goods SET gquantity = gquantity -" + "'" + gQuantity + "'" + "WHERE gname=" + "'" + gName + "'";
        String SQLUpdate2;
        String gid = getGoodsId(gName);
        String sid = getSalesmanId(sName);
        double gsid = Math.round(Math.random() * Math.pow(10, 5)); //自动生成6位id
        if (gsid > 10000) {
            SQLUpdate2 = "INSERT INTO public.gsales VALUES (" + gsid + "," + gid + "," + sid + "," + "Current_Date" + "," + gQuantity + ")";
        } else {
            SQLUpdate2 = "INSERT INTO public.gsales VALUES (" + gsid * 10 + "," + gid + "," + sid + "," + "Current_Date" + "," + gQuantity + ")";
        }
        try {
            statement.executeUpdate(SQLUpdate1);
            statement.executeUpdate(SQLUpdate2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "购买失败");
        }
    }

    /**
     * 查询当天销售商品信息
     *
     * @return
     */
    public static ArrayList<String> getSoldGoodsToday() {
        ArrayList<String> soldGoods = new ArrayList<>();
        String SQLQuery = "SELECT gname,gprice,gquantity,snum FROM public.gsales JOIN public.goods ON public.gsales.gid = public.goods.gid WHERE public.gsales.sdate = current_date";
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                String singleGoods = resultSet.getString("gname") + "_" + resultSet.getString("gprice") + "_" + resultSet.getString("gquantity") + "_" + resultSet.getString("snum");
                soldGoods.add(singleGoods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败");
        }
        return soldGoods;
    }

    /**
     * 增加售货员
     */
    public static void addSalesman() {
        double sid = Math.round(Math.random() * Math.pow(10, 5));//自动生成5位id
        String SQLUpdate;
        //!!!!!字符串比较内容采用equals()方法
        //!!!!!  ""不等于null
        if (AddSalesman.jtf1.getText().equals("") || AddSalesman.jtf2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "不符合规则，请重新输入");
        } else {
            if (sid > 10000) {
                SQLUpdate = "INSERT INTO public.salesman VALUES(" + "'" + sid + "'" +
                        "," + "'" + AddSalesman.jtf2.getText() + "'" +
                        "," + "'" + AddSalesman.jtf1.getText() + "'" + ")";
            } else {
                SQLUpdate = "INSERT INTO public.salesman VALUES(" + "'" + sid * 10 + "'" +
                        "," + "'" + AddSalesman.jtf2.getText() + "'" +
                        "," + "'" + AddSalesman.jtf1.getText() + "'" + ")";
            }
            try {
                statement.executeUpdate(SQLUpdate);
                JOptionPane.showMessageDialog(null, "售货员添加成功");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, "售货员添加失败");
            }
        }
    }

    /**
     * 通过姓名查找售货员
     *
     * @return
     */
    public static String getSalesman(String sName) {
        String SQLQuery = "SELECT sname,spaswd FROM public.salesman WHERE sname=" + "'" + sName + "'";
        String singleSalesman = null;
        try {
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                singleSalesman = resultSet.getString("sname") + "_" + resultSet.getString("spaswd");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "该售货员不存在");
        }
        return singleSalesman;
    }

    /**
     * 更改售货员姓名
     *
     * @param newName
     * @param oldName
     */
    public static void changeSalesmanName(String newName, String oldName) {
        String SQLUpdate = "UPDATE public.salesman SET sname = " + "'" + newName + "'" + "WHERE sname=" + "'" + oldName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "更改成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "更改失败");
        }
    }

    /**
     * 更改售货员密码
     *
     * @param newPW
     * @param sName
     */
    public static void changeSalesmanPassword(String newPW, String sName) {
        String SQLUpdate = "UPDATE public.salesman SET spaswd = " + "'" + newPW + "'" + "WHERE sname=" + "'" + sName + "'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null, "更改成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "更改失败");
        }
    }

    /**
     * 通过姓名删除售货员
     * @param sName
     */
    public static void deleteSalesman(String sName){
        String SQLUpdate = "DELETE FROM public.salesman WHERE sname ="+"'"+sName+"'";
        try {
            statement.executeUpdate(SQLUpdate);
            JOptionPane.showMessageDialog(null,"删除成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null,"删除失败" );
        }
    }

    /**
     * 通过姓名模糊查找售货员
     * @param sName
     * @return
     */
    public static ArrayList<String> findSalesmanByVagueName(String sName){
        String SQLQuery = "SELECT sname,spaswd FROM public.salesman WHERE sname LIKE"+"'"+"%"+sName+"%"+"'";
        ArrayList<String> salesmen = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(SQLQuery);
            String singleSalesman;
            while(resultSet.next()){
                singleSalesman = resultSet.getString("sname")+"_"+resultSet.getString("spaswd");
                salesmen.add(singleSalesman);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null,"查询失败");
        }
        return salesmen;
    }
    public static ArrayList<String> showAllSalesman(){
        String SQLQuery = "SELECT sname,spaswd FROM public.salesman";
        ArrayList<String> allSalesman = new ArrayList<>();
        try {
            String singleSalesman;
            resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()){
                singleSalesman = resultSet.getString("sname")+"_"+resultSet.getString("spaswd");
                allSalesman.add(singleSalesman);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null,"查询失败");
        }
        return allSalesman;
    }
}
