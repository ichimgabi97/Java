import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection con;

    public void DatabaseConnection(String user, String password) throws SQLException {
        this.con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user,password);
    }

    public Connection getCon(){
        return con;
    }
}
