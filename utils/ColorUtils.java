package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class ColorUtils {
    /**
     *  Clasa utilitara pentru metode grafice.
     */

    private ColorUtils() { }

    private static final int MAGIC_NUMBER = 16;

    /**
     *  Clasa utilitara pentru citirea si parsarea culorilor.
     * @param hex ~ componenta RGB a culorii in format String;
     * @param alpha ~ componenta Alpha a culorii;
     * @return Instanta a clasei Color cu parametrii respectivi.
     */
    public static Color parseColorARGB(final String hex, final int alpha) {
        return new Color(Integer.parseInt(Integer.toHexString(alpha)
                        + hex.substring(1), MAGIC_NUMBER), true);
    }

    /**
     *  Clasa utilitara pentru actualizarea culorilor de pe un BufferedImage
     *  respectand dimensiunile acesteia.
     * @param image ~ instanta a BufferedImageului;
     * @param x ~ punctul pe axa OX;
     * @param y ~ punctul pe axa OY;
     * @param color ~ noua culoare ce o va avea pixelul;
     */
    public static void paintPixel(final BufferedImage image,
                    final int x, final int y, final Color color) {
        if (0 <= x && x < image.getWidth() && 0 <= y
                && y < image.getHeight()) {
            image.setRGB(x, y, color.getRGB());
        }
    }

}
