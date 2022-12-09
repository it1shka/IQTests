import java.awt.*;

public record Shape (
    Shape.ShapeType shapeType,
    int[] position,
    double size,
    Color color
) {
    public enum ShapeType {
        CIRCLE,
        SQUARE,
        TRIANGLE
    }
}