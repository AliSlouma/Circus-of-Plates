package eg.edu.alexu.csd.oop.game.utility;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class HelperClass {
    /**
     * Check if three shapes are similar
     */
    public static boolean areSimilarShapes(GameObject shape1, GameObject shape2, GameObject shape3) {
        // Get center pixels colors
        BufferedImage image1 = shape1.getSpriteImages()[0];
        int centerColor1 = image1.getRGB(image1.getWidth() / 2, image1.getHeight() / 2);

        BufferedImage image2 = shape2.getSpriteImages()[0];
        int centerColor2 = image2.getRGB(image2.getWidth() / 2, image2.getHeight() / 2);

        BufferedImage image3 = shape3.getSpriteImages()[0];
        int centerColor3 = image3.getRGB(image3.getWidth() / 2, image3.getHeight() / 2);

        // If colors are similar
        return (centerColor1 == centerColor2) && (centerColor2 == centerColor3);
    }

    /**
     * Check if three shapes are similar
     */
    public static boolean areSimilarShapes(List<Shapes> shapeList) {
        if (shapeList.size() != 3)
        {
            return false;
        }

        return HelperClass.areSimilarShapes(shapeList.get(0).getShape(), shapeList.get(1).getShape(), shapeList.get(2).getShape());
    }

    /**
     * @return if two images match
     */
    public static boolean matchingImages(BufferedImage image1, BufferedImage image2) {
        return image1.getWidth() == image2.getWidth() && image1.getHeight() == image2.getHeight();
    }

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
