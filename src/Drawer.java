import java.awt.*;
import java.util.Random;

public class Drawer {
    private static final double defaultSize = 15.0;

    public static void drawShape(Graphics graphics, Shape shape) {
        graphics.setColor(shape.color());
        var radius = (int)(defaultSize * shape.size());
        var x = shape.position()[0];
        var y = shape.position()[1];

        switch (shape.shapeType()) {
            case CIRCLE -> graphics.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            case SQUARE -> graphics.fillRect(x - radius, y - radius, radius * 2, radius * 2);
            case TRIANGLE -> {
                var xs = new int[3];
                var ys = new int[3];
                var angle = -Math.PI / 2;
                var angleStep = 2 * Math.PI / 3;
                for (var i = 0; i < 3; i++) {
                    xs[i] = (int)(1.35 * radius * Math.cos(angle)) + x;
                    ys[i] = (int)(1.35 * radius * Math.sin(angle)) + y;
                    angle += angleStep;
                }
                graphics.fillPolygon(xs, ys, 3);
            }
        };
    }

    private static double getSize(int amount) {
        return switch (amount) {
            case 1 -> 1.0;
            case 2 -> 0.9;
            case 3 -> 0.85;
            case 4 -> 0.75;
            case 5 -> 0.7;
            case 6 -> 0.65;
            default -> 0.55;
        };
    }
    public static void drawTile(Graphics graphics, Shape.ShapeType shapeType, int amount, Color color) {
        var rect = graphics.getClipBounds();
        var center = new int[] { (int)rect.getCenterX(), (int)rect.getCenterX() };

        var offsetH = (int)(rect.getWidth() / 6) + 2;
        var offsetY = (int)(rect.getHeight() / 6) + 2;

        var positions = switch (amount) {
            case 1 -> new int[][] { center };
            case 2 -> new int[][] {
                    { center[0] - offsetH, center[1] },
                    { center[0] + offsetH, center[1] }
            };
            case 3 -> new int[][] {
                    { center[0], center[1] + offsetY },
                    { center[0] - offsetH, center[1] - offsetY },
                    { center[0] + offsetH, center[1] - offsetY }
            };
            case 4 -> new int[][] {
                    { center[0] - offsetH, center[1] + offsetY },
                    { center[0] + offsetH, center[1] + offsetY },
                    { center[0] - offsetH, center[1] - offsetY },
                    { center[0] + offsetH, center[1] - offsetY }
            };
            case 5 -> new int[][] {
                    { center[0] - offsetH - 2, center[1] + offsetY + 2 },
                    { center[0] + offsetH + 2, center[1] + offsetY + 2 },
                    { center[0] - offsetH - 2, center[1] - offsetY - 2 },
                    { center[0] + offsetH + 2, center[1] - offsetY - 2 },
                    center
            };
            default -> new int[0][];
        };
        var size = getSize(amount) * rect.getWidth() / 100.0;
        for (var position: positions) {
            var shape = new Shape(shapeType, position, size, color);
            drawShape(graphics, shape);
        }
    }

    // function for testing
    public static void drawRandom(Graphics graphics) {
        var random = new Random();
        var colors = new Color[] { new Color(168, 50, 123), new Color(50, 168, 82), new Color(50, 91, 168) };
        var color = colors[random.nextInt(colors.length)];
        var shapes = new Shape.ShapeType[] {Shape.ShapeType.TRIANGLE, Shape.ShapeType.CIRCLE, Shape.ShapeType.SQUARE};
        var shape = shapes[random.nextInt(shapes.length)];
        var amount = 1 + random.nextInt(5);
        drawTile(graphics, shape, amount, color);
    }

}
