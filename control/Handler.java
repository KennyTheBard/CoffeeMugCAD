package control;

import utils.ColorUtils;
import utils.FileReader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Handler {
    /** Clasa principala de control a programului.
     *  Aceasta este responsabila pentru:
     *      -> initializarea CANVASului
     *      -> actualizarea culorii pixelilor pe acesta
     *      -> generarea imaginii .PNG la incheierea
     *          setului de instructiuni
     */

    private FileReader fs;
    private BufferedImage image;
    private int numShapes;

    /**
     *  Constructor care obtine detalii despre datele ce urmeaza introduse.
     * @param fs ~ fisierul din care se va citeste;
     */
    public Handler(final FileReader fs) {
        this.fs = fs;
        try {
            numShapes = fs.nextInt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        createCanvas();
        paintCanvas();
    }

    /**
     *  Actionarea Visitor Patternului asupra formelor generate de clasa
     *  ce implementeaza Factory Pattern si Sigleton Pattern.
     */
    private void paintCanvas() {
        Painter davinci = new Painter(image);
        while (numShapes > 0) {
            try {
                Facton.getFacton().getShape(fs.nextWord(), fs).accept(davinci);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            numShapes--;
        }
    }

    /**
     *  Initializarea CANVASului conform primelor date de intrare.
     */
    private void createCanvas() {
        try {
            fs.nextWord();
            int height = fs.nextInt();
            int width = fs.nextInt();
            image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            Color color = ColorUtils.parseColorARGB(fs.nextWord(), fs.nextInt());
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    image.setRGB(i, j, color.getRGB());
                }
            }
            numShapes--;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Construirea imaginii .PNG la incheierea executiei datelor de intrare.
     */
    public void buildPNG() {
        try {
            ImageIO.write(image, "PNG", new File("./drawing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
