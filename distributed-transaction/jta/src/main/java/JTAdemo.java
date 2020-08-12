import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author: zhegong
 * 可以用 atomikos 替代
 **/
public class JTAdemo {

    public void testJTA() {
        UserTransaction userTransaction = null;
        Connection conn1 = null;
        Connection conn2 = null;


        userTransaction = getUserTransaction();

        conn1 = getConnectionFromDB2();
        conn2 = getConnectionFromOracleDB();

        if (conn1 != null && conn2 != null && userTransaction != null) {
            System.out.println("----------个对象成功就位！");
        }

        try {
            userTransaction.begin();
        } catch (NotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Statement sta1 = conn1.createStatement();
            Statement sta2 = conn2.createStatement();

            String sql1 = "INSERT INTO user(ID,NAME,AGE,SEX,ADDRESS)VALUES(1,'XIAOQIANG','15','1','湖北武汉')";
            String sql2 = "INSERT INTO usertext(ID,NAME,AGE,SEX,ADDRESS)VALUES(1,'XIAOQIANG','15','1','湖北武汉')";

            sta1.executeQuery(sql1);
            sta2.executeQuery(sql2);


            userTransaction.commit();

            sta1.close();
            sta2.close();

            conn1.close();
            conn2.close();


        } catch (Exception e) {
            e.printStackTrace();
            try {
                userTransaction.rollback();
            } catch (IllegalStateException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SecurityException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SystemException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("调用异常！");
        }
    }

    /**
     * 获取全局式事务接口
     */
    public static UserTransaction getUserTransaction() {
        UserTransaction userTransaction = null;
        try {
            Context initContext = new InitialContext();
            userTransaction = (UserTransaction) initContext.lookup("java:comp/UserTransaction");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTransaction;
    }

    /**
     * 功能：获取db2数据库的数据源
     */
    static private DataSource getDataSouceFromDB2() {
        DataSource _source = null;
        if (_source == null) {
            try {
                Context context = new javax.naming.InitialContext(); //从JNDI取出java环境上下文对象
                _source = (DataSource) context.lookup("java:comp/env/jdbc/db2Demo");//取出数据源
            } catch (NamingException e) {
                System.out.println("NamingException->" + e.toString());
            }
        }
        if (_source == null) {
            System.out.println("_source为null");
        }
        return _source;
    }

    /**
     * 功能：获取db2数据库的连接对象
     */
    public static Connection getConnectionFromDB2() {
        try {
            Connection c = getDataSouceFromDB2().getConnection(); //从连接池获取连接对象
            //c.setReadOnly(false);
            if (c != null)
                System.out.println("已经获得DB2数据库连接对象conn。");
            return c;
        } catch (Exception e) {
            System.err.println("JndiRes.getConnection() error.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能：获取oracle数据库数据源：
     */
    static private DataSource getDataSouceFromOracleDB() {
        DataSource _source = null;
        if (_source == null) {
            try {
                Context env = new javax.naming.InitialContext();
                _source = (DataSource) env.lookup("java:comp/env/jdbc/oracleDemo");
            } catch (NamingException e) {
                System.out.println("NamingException->" + e.toString());
            }
        }
        return _source;
    }

    /**
     * 功能：获得Oracle数据库连接对象。
     */
    public static Connection getConnectionFromOracleDB() {
        try {
            Connection c = getDataSouceFromOracleDB().getConnection();
            if (c != null)
                System.out.println("已经获得oracle数据库连接对象conn。");
            return c;
        } catch (Exception e) {
            System.err.println("JndiRes.getConnection() error.");
            e.printStackTrace();
            return null;
        }
    }
}
