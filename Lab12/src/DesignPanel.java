import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DesignPanel extends JPanel {

    final MainFrame frame;
    final static int W = 1900, H = 870;
    BufferedImage image;
    Graphics2D graphics;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }
    private void init() {
        this.setLayout(null);
        this.setSize(800, 800);
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    drawShape(e.getX(), e.getY());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                }
                revalidate();
                repaint();
            }
        });
    }

    public void setPreferredSize(Dimension dimension) {
        this.setSize(dimension);
    }

    private void drawShape(int x, int y) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class aClass = Class.forName((String) this.frame.controlPanel.colorCombo.getItemAt(this.frame.controlPanel.colorCombo.getSelectedIndex()));
        Component component = (Component)  aClass.newInstance();
        add(component);
        component.setLocation(x,y);
        revalidate();
        repaint();
    }
    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}