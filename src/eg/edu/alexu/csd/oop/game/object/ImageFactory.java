package eg.edu.alexu.csd.oop.game.object;

import java.awt.image.BufferedImage;

public interface ImageFactory {
    /**
     * @return a random image with random color.
     */
    public BufferedImage createImage();
}
