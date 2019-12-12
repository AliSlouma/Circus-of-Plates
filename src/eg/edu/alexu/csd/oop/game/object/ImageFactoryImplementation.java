package eg.edu.alexu.csd.oop.game.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageFactoryImplementation implements ImageFactory {
    private ImageFactory imageFactory = new ImageFactoryImplementation();
    private Map<String, BufferedImage> imageMap = new HashMap<>();

    // Available shapes and colors
    private List<String> availableShapes;
    private List<String> availableColors;

    // Private constructor
    private ImageFactoryImplementation() {
        availableShapes = new ArrayList<>();
        availableColors = new ArrayList<>();

        // ...
    }

    /**
     * @return ImageFactory object (singleton).
     */
    public ImageFactory getInstance() {
        return this.imageFactory;
    }

    /**
     * @return a random image with random color.
     */
    public BufferedImage createImage() {
        String name = availableShapes.get((int) (Math.random() * availableShapes.size()));
        String color = availableColors.get((int) (Math.random() * availableColors.size()));

        return this.getImage(name + "-" + color + ".png");
    }

    /**
     * @return image based on string
     */
    private BufferedImage getImage(String name) {
        if (imageMap.get(name) == null)
        {
            // Load image
            BufferedImage loadedImage = null;
            try {
                loadedImage = ImageIO.read(this.getClass().getResourceAsStream("/" + name));
                imageMap.put(name, loadedImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return loadedImage;
        }
        else
        {
            return imageMap.get(name);
        }
    }
}
