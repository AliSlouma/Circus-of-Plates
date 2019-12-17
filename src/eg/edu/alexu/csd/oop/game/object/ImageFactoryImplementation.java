package eg.edu.alexu.csd.oop.game.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ImageFactoryImplementation implements ImageFactory {
    private static ImageFactory imageFactory = new ImageFactoryImplementation();
    private Map<String, BufferedImage> imageMap = new HashMap<>();

    // Available shapes
    private List<String> availableShapes;

    // Private constructor
    private ImageFactoryImplementation() {
        File file = new File("res" + System.getProperty("file.separator") + "pieces");

        availableShapes = new ArrayList<>();
        if (file.list() != null)
        {
            for (String innerFile : file.list())
            {
                // Check if file is .png
                if (Pattern.compile("(.*\\.png$)", Pattern.CASE_INSENSITIVE).matcher(innerFile).matches())
                {
                    availableShapes.add(innerFile);
                }
            }
        }
    }

    /**
     * @return ImageFactory object (singleton).
     */
    public static ImageFactory getInstance() {
        return ImageFactoryImplementation.imageFactory;
    }

    /**
     * @return a random image with random color.
     */
    public BufferedImage createImage() {
        return this.getImage(System.getProperty("file.separator") + "pieces" + System.getProperty("file.separator") + availableShapes.get((int) (Math.random() * availableShapes.size())));
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
                loadedImage = ImageIO.read(this.getClass().getResourceAsStream(name));
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
