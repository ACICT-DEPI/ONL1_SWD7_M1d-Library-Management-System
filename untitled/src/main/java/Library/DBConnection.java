package Library;

import java.sql.*;

public class DBConnection {
    private static Connection conn;
    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-2072fc58-tasneemabdeltawab205-075f.j.aivencloud.com:28698/defaultdb?ssl-mode=REQUIRED";
            String user = "avnadmin";
            String pass = "AVNS_UMoxhSatBfdaI4exVWu";
            DBConnection.conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return conn; // Return the established connection
    }

    public ResultSet runQuery(String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    public int runUpdate(String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeUpdate(query);
    }

    public static void Close() throws SQLException {
        DBConnection.conn.close();
    }
}

/*
running style
DBConnection newCon = new DBConnection();
resultSet = newCon.runQuery("select * from Library");
newCon.Close();
 */
