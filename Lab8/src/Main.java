import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.DatabaseConnection("dba", "sql");

        ArtistController artist = new ArtistController();
        artist.setCon(db.getCon());
        artist.create("Name", "Romania");
        System.out.println(artist.findByName("Name"));
        System.out.println(artist.findByName("Name1"));

        AlbumController album = new AlbumController();
        album.setCon(db.getCon());
        album.create("Name", 1, 1500);
        System.out.println(album.findByArtist(1));
        System.out.println(album.findByArtist(1000));
    }



}
