import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        this.canvas = new DesignPanel(this);
        this.controlPanel = new ControlPanel(this);


        this.add(controlPanel, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        this.setSize(new Dimension(DesignPanel.W, DesignPanel.H));

        //invoke the layout manager
        pack();
    }

    public static void main(String[] args) {
        Frame frame = new MainFrame();
        frame.pack();
        frame.setVisible(true);
    }
}