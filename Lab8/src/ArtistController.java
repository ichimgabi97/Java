import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistController {

    Statement stmt = null;
    Connection con;

    public void setCon(Connection con){
        this.con = con;
    }

    public void create(String name, String country) throws SQLException {
        try {
            String query = "INSERT INTO ARTISTS (name, country) VALUES ('" + name + "', '" + country + "')";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException e){
            System.err.println(e);
        }finally {
            if (stmt != null){
                stmt.close();
            }
        }
    }

    public boolean findByName(String name) throws SQLException {
        try {
            String query = "SELECT id FROM ARTISTS WHERE name = '" + name +"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = 0;
                id = rs.getInt("id");
                if (id != 0){
                    return true;
                }
            }
        } catch (SQLException e){
            System.err.println(e);
        }finally {
            if (stmt != null){
                stmt.close();
            }
        }
        return false;
    }
}
