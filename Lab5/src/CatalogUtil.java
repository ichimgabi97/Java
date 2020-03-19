import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException, IOException, ClassNotFoundException {
        Catalog result;
        try(var oss = new ObjectInputStream(new FileInputStream(path))){
            result = (Catalog) oss.readObject();
        }
        return result;
    }
    public static void view(Document doc) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        if(doc.getLocation().contains("https:")){
            URI result = new URI(doc.getLocation());
            desktop.browse(result);
        }
        else {
            File result = new File(doc.getLocation());
            desktop.open(result);
        }
    }

}
