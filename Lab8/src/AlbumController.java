import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlbumController {

    Statement stmt = null;
    Connection con;

    public void setCon(Connection con){
        this.con = con;
    }

    public void create(String name, int artistId, int realeaseYear) throws SQLException {
        try {
            String query = "INSERT INTO ALBUMS (name, artist_id, release_year) VALUES ('" + name + "'," + artistId + ", " + realeaseYear +")";
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

    public boolean findByArtist(int artistId) throws SQLException {
        try {
            String query = "SELECT id FROM ALBUMS WHERE artist_id ="+ artistId;
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
