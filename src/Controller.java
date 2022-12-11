import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private static final Shape.ShapeType[] shapes = new Shape.ShapeType[] {
        Shape.ShapeType.SQUARE,
        Shape.ShapeType.CIRCLE,
        Shape.ShapeType.TRIANGLE
    };

    private static final Integer[] amounts = new Integer[] {
            1, 2, 3, 4, 5
    };

    private static final Color[] colors = new Color[] {
        new Color(168, 50, 123),
        new Color(50, 168, 82),
        new Color(50, 91, 168)
    };

    private static final Random random = new Random();
    private final AppLayout layout;
    private int currentRightTile;
    private int correct;
    private int wrong;

    public Controller(AppLayout layout) {
        this.layout = layout;
        addClickListenersToOptions();
        correct = 0;
        wrong = 0;
    }

    private void addClickListenersToOptions() {
        var options = layout.getOptionTiles();
        for (var i = 0; i < options.length; i++) {
            final var nth = i;
            options[i].addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    optionClicked(nth);
                }
            });
        }
    }

    public void startTheTest() {
        var shapeSequence = createSequence(shapes);
        var amountSequence = createSequence(amounts);
        var colorSequence = createSequence(colors);

        var tiles = layout.getTestTiles();
        for (var i = 0; i < tiles.length - 1; i++) {
            var shape = shapeSequence.get(i);
            var amount = amountSequence.get(i);
            var color = colorSequence.get(i);
            tiles[i].setParameters(shape, amount, color);
        }

        var rightTile = random.nextInt(3);
        currentRightTile = rightTile;
        var last = tiles.length - 1;
        var options = layout.getOptionTiles();
        options[rightTile].setParameters(
                shapeSequence.get(last),
                amountSequence.get(last),
                colorSequence.get(last)
        );
        for (var i = 0; i < 3; i++) {
            if (i == rightTile) continue;
            options[i].setParameters(
                    getRandom(shapes),
                    getRandom(amounts),
                    getRandom(colors)
            );
        }

        updateStatusLabel();
    }

    private static final String[] statuses = new String[] {
            "very dumb",
            "dumb",
            "little bit stupid",
            "not that bad",
            "ok",
            "fine",
            "great",
            "awesome",
            "very smart",
            "brilliant"
    };

    private static String getStatus(double percentage) {
        var step = 100.0d / statuses.length;
        for (var i = 0; i < statuses.length; i++) {
            var left = i * step;
            var right = (i + 1) * step;
            if (percentage >= left && percentage <= right) {
                return statuses[i];
            }
        }
        return statuses[statuses.length - 1];
    }

    private void updateStatusLabel() {
        var label = layout.getStatusLabel();
        if (wrong == 0 && correct == 0) {
            label.setText("0/0, let's go");
            return;
        }
        var percentage = (double)correct / (double)(correct + wrong);
        var status = getStatus(percentage * 100);
        var labelText = String.format("%d/%d (%.2f%%) %s", correct, wrong, percentage * 100, status);
        label.setText(labelText);
    }

    private void optionClicked(int nth) {
        if (nth == currentRightTile) correct++;
        else wrong++;
        startTheTest();
    }

    private static <T> ArrayList<T> createSequence(T[] parameter) {
        var len = parameter.length;
        var output = new ArrayList<T>(len * len);
        var initialOffset = random.nextInt(len);
        var step = random.nextInt(len) + 1;
        for (var i = 0; i < step * 3; i += step) {
            for (var j = 0; j < 3; j++) {
                var index = (initialOffset + i + j) % len;
                output.add(parameter[index]);
            }
        }
        return output;
    }

    private static <T> T getRandom(T[] sequence) {
        var index = random.nextInt(sequence.length);
        return sequence[index];
    }

}
