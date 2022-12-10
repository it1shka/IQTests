import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private Shape.ShapeType shapeType;
    private int amount;
    private Color color;

    public void setParameters(Shape.ShapeType shapeType, int amount, Color color) {
        this.shapeType = shapeType;
        this.amount = amount;
        this.color = color;
    }

    @Override public void paint(Graphics graphics) {
        graphics.setColor(new Color(222, 212, 202));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        Drawer.drawTile(graphics, shapeType, amount, color);
        repaint();
    }
}
