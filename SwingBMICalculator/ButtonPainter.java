import javax.swing.*;
import java.awt.*;

/**
 * A custom painter class that paints a button with a gradient fill and rounded corners.
 */
@SuppressWarnings("rawtypes") // Consider using generics for type safety
public class ButtonPainter implements Painter {

    private Color light, dark;
    private GradientPaint gradPaint;
    private int arcWidth, arcHeight;

    /**
     * Constructor that sets the light and dark colors for the gradient fill,
     * and uses default values (7) for the arc width and height.
     * 
     * @param light the lighter color for the gradient
     * @param dark the darker color for the gradient
     */
    public ButtonPainter(Color light, Color dark) {
        this(light, dark, 7, 7);
    }

    /**
     * Constructor that allows customization of all properties: light color,
     * dark color, arc width, and arc height.
     * 
     * @param light the lighter color for the gradient
     * @param dark the darker color for the gradient
     * @param arcWidth the width of the rounded corners
     * @param arcHeight the height of the rounded corners
     */
    public ButtonPainter(Color light, Color dark, int arcWidth, int arcHeight) {
        this.light = light;
        this.dark = dark;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    /**
     * Paints a button with a gradient fill and rounded corners.
     * 
     * @param g the Graphics2D object used for painting
     * @param c the component being painted (usually a JButton)
     * @param w the width of the component
     * @param h the height of the component
     */
    @Override
    public void paint(Graphics2D g, Object c, int w, int h) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gradPaint = new GradientPaint((w / 8.0f), 0, light, (w / 3.0f), (h / 1.0f), dark, true);
        g.setPaint(gradPaint);

        // Draw rounded rectangle with gradient fill
        g.setStroke(new BasicStroke(3)); // Adjust stroke width as needed
        g.fillRoundRect(2, 2, w - 5, h - 5, arcWidth, arcHeight);
    }
}