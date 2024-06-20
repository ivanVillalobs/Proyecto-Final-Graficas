package metodos;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 *
 * @author ivano
 */
public class floodFillIterative {

    private BufferedImage buffer;

    public floodFillIterative(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void floodFillIteratives(int x, int y, int borderColor, int fillColor) {
        int width = buffer.getWidth();
        int height = buffer.getHeight();

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        if (buffer.getRGB(x, y) == fillColor) {
            return;
        }

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            x = p.x;
            y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) {
                continue;
            }

            try {
                if (buffer.getRGB(x, y) != borderColor && buffer.getRGB(x, y) != fillColor) {
                    buffer.setRGB(x, y, fillColor);

                    stack.push(new Point(x + 1, y));
                    stack.push(new Point(x - 1, y));
                    stack.push(new Point(x, y + 1));
                    stack.push(new Point(x, y - 1));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }
}
