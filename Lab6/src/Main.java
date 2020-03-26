import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

    String size;
    String color;
    String shape;
    boolean delete = false;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab6");

        //Configuration panel

        Label labelSize = new Label();
        labelSize.setText("Size: ");
        TextField textFieldSize = new TextField();
        HBox hbSize = new HBox();
        hbSize.getChildren().addAll(labelSize, textFieldSize);
        hbSize.setSpacing(10);


        Label labelColor = new Label();
        labelColor.setText("Color: ");
        ChoiceBox<String> cbColor = new ChoiceBox<>();
        cbColor.setItems(FXCollections.observableArrayList("Red", "Green", "Black"));
        HBox hbColor = new HBox();
        hbColor.getChildren().addAll(labelColor, cbColor);
        hbColor.setSpacing(10);
        cbColor.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            switch (t1) {
                case "Red":
                    color = "Red";
                    break;
                case "Green":
                    color = "Green";
                    break;
                case "Black":
                    color = "Black";
                    break;
            }
        });

        Label labelShape = new Label();
        labelShape.setText("Shape: ");
        ChoiceBox<String> cbShape = new ChoiceBox<>();
        cbShape.setItems(FXCollections.observableArrayList("Square", "Circle","Triangle"));
        HBox hbShape = new HBox();
        hbColor.getChildren().addAll(labelShape, cbShape);
        hbColor.setSpacing(10);
        cbShape.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            switch (t1) {
                case "Square":
                    shape = "Square";
                    break;
                case "Circle":
                    shape = "Circle";
                    break;
                case "Triangle":
                    shape = "Triangle";
                    break;
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(hbSize, hbColor, hbShape);
        hb.setSpacing(30);

        //-------------------------------------------------------------------


        //Drawing panel

        final Canvas layer = new Canvas(900,400);
        GraphicsContext gc = layer.getGraphicsContext2D();

        layer.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getClickCount() > 0) {
                if (!delete) {
                    switch (color) {
                        case "Red":
                            gc.setFill(Color.RED);
                            break;
                        case "Green":
                            gc.setFill(Color.GREEN);
                            break;
                        case "Black":
                            gc.setFill(Color.BLACK);
                            break;
                    }
                    size = textFieldSize.getText();
                    //Drawing on canvas
                    switch (shape) {
                        case "Square":
                            gc.fillRoundRect(e.getX() - Float.parseFloat(size) / 2, e.getY() - Float.parseFloat(size) / 2, Integer.parseInt(size), Integer.parseInt(size), 10, 10);
                            break;
                        case "Circle":
                            gc.fillOval(e.getX() - Float.parseFloat(size) / 2, e.getY() - Float.parseFloat(size) / 2, Integer.parseInt(size), Integer.parseInt(size));
                            break;
                        case "Triangle":
                            //Mai e de lucrat :)))
                            gc.fillPolygon(new double[]{e.getX(), e.getX() - Integer.parseInt(size), e.getX() + Integer.parseInt(size)},
                                    new double[]{e.getY() - Float.parseFloat(size) / 2, e.getY() + Float.parseFloat(size) / 2, e.getY() + Float.parseFloat(size) / 2}, 3);
                    }
                } else {
                    //Erasing from canvas
                    size = textFieldSize.getText();
                    gc.clearRect(e.getX() - Float.parseFloat(size) / 2, e.getY() - Float.parseFloat(size) / 2, Integer.parseInt(size), Integer.parseInt(size));
                }

            }
        });

        Pane pane = new Pane();
        pane.getChildren().add(layer);

        //-------------------------------------------------------------------



        //Control panel

        Button buttonLoad = new Button();
        buttonLoad.setText("Load");
        Button buttonSave = new Button();
        buttonSave.setText("Save");
        Button buttonReset = new Button();
        buttonReset.setText("Reset");
        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        Button buttonDelete = new Button();
        buttonDelete.setText("Delete");

        buttonReset.setOnAction(actionEvent -> gc.clearRect(0, 0, layer.getWidth(), layer.getHeight()));

        buttonExit.setOnAction(actionEvent -> Platform.exit());

        buttonSave.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            File file = fileChooser.showSaveDialog(primaryStage);
            WritableImage writableImage = new WritableImage((int) layer.getWidth(), (int) layer.getHeight());
            WritableImage snapshot = layer.snapshot(new SnapshotParameters(), writableImage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonLoad.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Image");
            File file = fileChooser.showOpenDialog(primaryStage);
            Image img = new Image(file.toURI().toString(), layer.getWidth(), layer.getHeight(), false, false);
            gc.clearRect(0, 0, layer.getWidth(), layer.getHeight());
            gc.drawImage(img, 0, 0);
        });

        buttonDelete.setOnAction(actionEvent -> delete = !delete);

        HBox hbButton = new HBox();
        hbButton.getChildren().addAll(buttonReset, buttonSave, buttonLoad, buttonExit, buttonDelete);
        hbButton.setSpacing(20);

        //-------------------------------------------------------------------


        
        VBox vb = new VBox();
        vb.getChildren().addAll(hb, pane, hbButton);
        StackPane layout = new StackPane();
        layout.getChildren().add(vb);
        Scene scene = new Scene(layout, 900,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }





}
