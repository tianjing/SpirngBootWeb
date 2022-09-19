package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestPhoenix {
    private static String driver = "org.apache.phoenix.jdbc.PhoenixDriver";

    public static void main(String[] args) throws Exception {

        Connection vConnection = createConnection();
        System.out.println(vConnection);
    }

    private static Connection createConnection() {
        try {
            //org.apache.hadoop.security.authentication.util.KerberosUtil.hasKerberosKeyTab();
            // load driver
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");

            // get connection
            // jdbc 的 url 类似为 jdbc:phoenix [ :<zookeeper quorum> [ :<port number> ] [ :<root node> ] ]，
            // 需要引用三个参数：hbase.zookeeper.quorum、hbase.zookeeper.property.clientPort、and zookeeper.znode.parent，
            // 这些参数可以缺省不填而在 hbase-site.xml 中定义。
            return DriverManager.getConnection("jdbc:phoenix:192.168.88.35:2182");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
