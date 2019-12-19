package eg.edu.alexu.csd.oop.game.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelperClass {
    /**
     * Merge 2 buffered images
     */
    public static BufferedImage mergeImages(BufferedImage image1, BufferedImage image2) {
        return HelperClass.merge(1, image1, image2);
    }

    /**
     * Merge 2 buffered images
     */
    public static BufferedImage overlayImages(BufferedImage image1, BufferedImage image2) {
        return HelperClass.merge(0, image1, image2);
    }

    private static BufferedImage merge(int id, BufferedImage image1, BufferedImage image2)
    {
        BufferedImage merged;

        int width = image1.getWidth() + image2.getWidth();
        int height = Math.max(image1.getHeight(), image2.getHeight());
        merged = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = merged.getGraphics();
        graphics.drawImage(image1, 0, 0, null);

        if (id == 0)
        {
            graphics.drawImage(image2, 0, 0, null);
        }
        else
        {
            graphics.drawImage(image2, image1.getWidth(), 0, null);
        }

        return merged;
    }
}
