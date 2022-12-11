import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppLayout {
    private JFrame window;
    private JPanel testPanel;
    private JPanel optionPanel;
    private Tile[] testTiles;
    private Tile[] optionTiles;
    private JLabel statusLabel;

    public Tile[] getTestTiles() {
        return testTiles;
    }

    public Tile[] getOptionTiles() {
        return optionTiles;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public AppLayout() {
        createWindow();
        addStatusLabel();
        createTestPanel();
        attachTilesToTestPanel();
        createOptionPanel();
        attachButtonsToOptionPanel();
        packAndShowWindow();
    }

    private void addStatusLabel() {
        var label = new JLabel("Empty label");
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        label.setFont(font);
        var border = new EmptyBorder(10, 10, 0, 10);
        label.setBorder(border);
        statusLabel = label;
        window.add(label);
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
        testTiles = new Tile[9];
        for (var i = 0; i < 9; i++) {
            var tile = new Tile();
            testPanel.add(tile);
            testTiles[i] = tile;
        }
    }

    private void attachButtonsToOptionPanel() {
        optionTiles = new Tile[3];
        for (var i = 0; i < 3; i++) {
            var option = new Tile();
            option.setPreferredSize(new Dimension(60, 60));
            optionPanel.add(option);
            optionTiles[i] = option;
        }
    }

    private void packAndShowWindow() {
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }
}