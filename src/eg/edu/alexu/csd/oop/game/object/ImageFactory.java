package eg.edu.alexu.csd.oop.game.object;

import java.awt.image.BufferedImage;

public interface ImageFactory {
    /**
     * @return ImageFactory object (singleton).
     */
    public ImageFactory getInstance();

    /**
     * @return a random image with random color.
     */
    public BufferedImage createImage();
}
