import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppLayout {
    private JFrame window;
    private JPanel testPanel;
    private JPanel optionPanel;

    public AppLayout() {
        createWindow();
        createTestPanel();
        attachTilesToTestPanel();
        createOptionPanel();
        attachButtonsToOptionPanel();
        packAndShowWindow();
    }

    private void createWindow() {
        var window = new JFrame("IQ Test");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var windowLayout = new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS);
        window.setLayout(windowLayout);
        this.window = window;
    }

    private void createTestPanel() {
        var testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(300, 300));
        var gridLayout = new GridLayout(3, 3);
        testPanel.setLayout(gridLayout);
        final var tileGap = 5;
        gridLayout.setHgap(tileGap);
        gridLayout.setVgap(tileGap);
        var border = new EmptyBorder(10, 20, 10, 20);
        testPanel.setBorder(border);
        this.testPanel = testPanel;
        window.add(testPanel);
    }

    private void createOptionPanel() {
        var optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(280, 80));
        var flowLayout = new FlowLayout(FlowLayout.CENTER);
        optionsPanel.setLayout(flowLayout);
        this.optionPanel = optionsPanel;
        window.add(optionPanel);
    }

    private void attachTilesToTestPanel() {
        for (var i = 0; i < 9; i++) {
            var tile = new JPanel() {
                @Override public void paint(Graphics graphics) {
                    // for now, it's just painting it red
                    graphics.setColor(new Color(222, 212, 202));
                    graphics.fillRect(0, 0, getWidth(), getHeight());
                    //
                    Drawer.drawRandom(graphics);
                }
            };
            testPanel.add(tile);
        }
    }

    private void attachButtonsToOptionPanel() {
        for (var i = 0; i < 3; i++) {
            var option = new JButton() {
                @Override public void paint(Graphics graphics) {
                    // for now just painting it black
                    graphics.setColor(new Color(222, 212, 202));
                    graphics.fillRect(0, 0, getWidth(), getHeight());
                    // var square = new Shape(Shape.ShapeType.CIRCLE, new int[] {getWidth() / 2, getHeight() / 2}, 1.2, Color.BLUE);
                    // Drawer.drawShape(graphics, square);
                    Drawer.drawRandom(graphics);
                }
            };
            option.setPreferredSize(new Dimension(60, 60));
            optionPanel.add(option);
        }
    }

    private void packAndShowWindow() {
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }
}