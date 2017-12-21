package control;

import interfaces.Visitor;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;
import utils.ColorUtils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public final class Painter implements Visitor {
    /**
     *  Implementare a Visitor Patternului, cu rol in:
     *      -> actionarea metodelor grafice de trasare si umplere
     *      -> determinarea modului in care acestea vor fi aplicate.
     */

    private BufferedImage image;

    private static final int
            THREE = 3,
            FOUR = 4,
            SIX = 6,
            TEN = 10,
            MAX_ALPHA = 255;

    public Painter(final BufferedImage image) {
        this.image = image;
    }

    @Override
    public void visit(final Line shape) {
        bresenhamAlgorithm(image, shape.getxStart(), shape.getyStart(),
                shape.getxEnd(), shape.getyEnd(), shape.getColor());
    }

    @Override
    public void visit(final Square shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getX() + shape.getDimension() + 1),
                max(image.getHeight(), shape.getY() + shape.getDimension() + 1),
                background);
        /** Latura de sus */
        bresenhamAlgorithm(temp, shape.getX(), shape.getY(),
                shape.getX() + shape.getDimension(),
                shape.getY(), shape.getContur());
        /** Latura de la stanga */
        bresenhamAlgorithm(temp, shape.getX(), shape.getY(), shape.getX(),
                shape.getY() + shape.getDimension(), shape.getContur());
        /** Latura de jos */
        bresenhamAlgorithm(temp, shape.getX(),
                shape.getY() + shape.getDimension(),
                shape.getX() + shape.getDimension(),
                shape.getY() + shape.getDimension(), shape.getContur());
        /** Latura de la dreapta */
        bresenhamAlgorithm(temp, shape.getX() + shape.getDimension(),
                shape.getY(), shape.getX() + shape.getDimension(),
                shape.getY() + shape.getDimension(), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getX() + shape.getDimension() / 2,
                shape.getY() + shape.getDimension() / 2,
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }

    @Override
    public void visit(final Rectangle shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getX() + shape.getWidth() + 1),
                max(image.getHeight(), shape.getY() + shape.getHeight() + 1),
                background);
        /** Latura de sus */
        bresenhamAlgorithm(temp, shape.getX(), shape.getY(),
                shape.getX() + shape.getWidth(),
                shape.getY(), shape.getContur());
        /** Latura de la stanga */
        bresenhamAlgorithm(temp, shape.getX(), shape.getY(), shape.getX(),
                shape.getY() + shape.getHeight(), shape.getContur());
        /** Latura de jos */
        bresenhamAlgorithm(temp, shape.getX(),
                shape.getY() + shape.getHeight(),
                shape.getX() + shape.getWidth(),
                shape.getY() + shape.getHeight(), shape.getContur());
        /** Latura de la dreapta */
        bresenhamAlgorithm(temp, shape.getX() + shape.getWidth(),
                shape.getY(), shape.getX() + shape.getWidth(),
                shape.getY() + shape.getHeight(), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getX() + shape.getWidth() / 2,
                shape.getY() + shape.getHeight() / 2,
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }

    @Override
    public void visit(final Circle shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getRadius() + 1),
                max(image.getHeight(), shape.getRadius() + 1),
                background);
        /** Desenarea cercului */
        bresenhamCircle(temp, shape.getCenterX(), shape.getCenterY(),
                shape.getRadius(), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getCenterX(), shape.getCenterY(),
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }

    @Override
    public void visit(final Triangle shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getMaxWidth() + 1),
                max(image.getHeight(), shape.getMaxHeight() + 1),
                background);
        /** Desenarea laturilor */
        bresenhamAlgorithm(temp, shape.getX1(), shape.getY1(),
                shape.getX2(), shape.getY2(), shape.getContur());
        bresenhamAlgorithm(temp, shape.getX2(), shape.getY2(),
                shape.getX3(), shape.getY3(), shape.getContur());
        bresenhamAlgorithm(temp, shape.getX3(), shape.getY3(),
                shape.getX1(), shape.getY1(), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getCenterX(), shape.getCenterY(),
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }

    @Override
    public void visit(final Diamond shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getWidth() / 2 + 1),
                max(image.getHeight(), shape.getHeight() / 2 + 1),
                background);
        /** Desenarea laturilor */
        bresenhamAlgorithm(temp, shape.getUpX(), shape.getUpY(),
                shape.getRightX(), shape.getRightY(), shape.getContur());
        bresenhamAlgorithm(temp, shape.getRightX(), shape.getRightY(),
                shape.getDownX(), shape.getDownY(), shape.getContur());
        bresenhamAlgorithm(temp, shape.getDownX(), shape.getDownY(),
                shape.getLeftX(), shape.getLeftY(), shape.getContur());
        bresenhamAlgorithm(temp, shape.getUpX(), shape.getUpY(),
                shape.getLeftX(), shape.getLeftY(), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getCenterX(), shape.getCenterY(),
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }

    @Override
    public void visit(final Polygon shape) {
        Color background = new Color(0, 0, 0, MAX_ALPHA);
        BufferedImage temp = getAuxBuffer(
                max(image.getWidth(), shape.getMaxWidth() + 1),
                max(image.getHeight(), shape.getMaxHeight() + 1),
                background);
        /** Desenarea laturilor */
        for (int i = 0; i < shape.getNumPoints() - 1; i++) {
            bresenhamAlgorithm(temp, shape.getXs(i), shape.getYs(i),
                    shape.getXs(i + 1), shape.getYs(i + 1), shape.getContur());
        }
        bresenhamAlgorithm(temp, shape.getXs(shape.getNumPoints() - 1),
                shape.getYs(shape.getNumPoints() - 1),
                shape.getXs(0), shape.getYs(0), shape.getContur());
        /** Colorarea interiorului */
        floodFill(temp, shape.getCenterX(), shape.getCenterY(),
                shape.getFill(), background);
        /** Copiem pixelii modificati in imaginea originala */
        copyOver(temp, image, background);
    }


    private void bresenhamAlgorithm(final BufferedImage tempImage,
                final int x1, final int y1, final int x2, final int y2, final Color color) {

        // initialize variables
        int aux;
        int x = x1;
        int y = y1;
        int deltaX = abs(x2 - x1);
        int deltaY = abs(y2 - y1);
        int s1 = sign(x2 - x1);
        int s2 = sign(y2 - y1);

        // interchange delta_x and delta_y, depending on the slope of the line
        boolean interchanged;
        if (deltaY > deltaX) {
            aux = deltaX;
            deltaX = deltaY;
            deltaY = aux;
            interchanged = true;
        } else {
            interchanged = false;
        }

        // initialize the error term to compensate for a nonzero intercept
        int error = 2 * deltaY - deltaX;

        for (int i = 0; i <= deltaX; i++) {
            ColorUtils.paintPixel(tempImage, x, y, color);

            while (error > 0) {
                if (interchanged) {
                    x += s1;
                } else {
                    y += s2;
                }
                error -= 2 * deltaX;
            }

            if (interchanged) {
                y += s2;
            } else {
                x += s1;
            }

            error += 2 * deltaY;
        }
    }

    private int sign(final int n) {
        if (n < 0) {
            return -1;
        } else if (n > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private int abs(final int n) {
        if (n < 0) {
            return -n;
        } else {
            return n;
        }
    }

    private void floodFill(final BufferedImage tempImage, final int x,
           final int y, final Color fillingColor, final Color targetColor) {
        floodFillHalf(tempImage, x, y, fillingColor, targetColor, -1);
        floodFillHalf(tempImage, x + 1, y, fillingColor, targetColor, 1);
    }

    private void floodFillHalf(final BufferedImage tempImage, final int x,
           final int y, final Color fillingColor, final Color targetColor,
               final int toAdd) {
        /** Verificam limitele imaginii */
        if (!(tempImage.getWidth() > x && tempImage.getHeight() > y
                && x >= 0 && y >= 0)) {
            return;
        }
        /** Verificam daca pozitia actuala are culoare dif de cea initiala */
        if (tempImage.getRGB(x, y) != targetColor.getRGB()) {
            return;
        }
        /** Umplerea pixelului curent */
        ColorUtils.paintPixel(tempImage, x, y, fillingColor);
        /** Apelarea recursiva pe pixelii vecini (in 4 directii) */
        floodFillHalf(tempImage, x + toAdd, y, fillingColor, targetColor, toAdd);
        floodFillHalf(tempImage, x, y - 1, fillingColor, targetColor, toAdd);
        floodFillHalf(tempImage, x, y + 1, fillingColor, targetColor, toAdd);
    }

    private void copyOver(final BufferedImage source, final BufferedImage dest,
                          final Color background) {
        for (int x = 0; x < dest.getWidth(); x++) {
            for (int y = 0; y < dest.getHeight(); y++) {
                if (source.getRGB(x, y) != background.getRGB()) {
                    ColorUtils.paintPixel(dest, x, y, new Color(source.getRGB(x, y), true));
                }
            }
        }
    }

    private BufferedImage getAuxBuffer(final int width, final int height, final Color background) {
        BufferedImage aux = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ColorUtils.paintPixel(aux, x, y, background);
            }
        }
        return aux;
    }

    public void bresenhamCircle(final BufferedImage temp, final int centerX, final int centerY,
                                final int radius, final Color contur) {
        int xc = centerX;
        int yc = centerY;
        int r = radius;
        int x = 0, y = r;
        int d = THREE - 2 * r;
        while (y >= x) {
            // for each pixel we will
            // draw all eight pixels
            drawCircle(temp, xc, yc, x, y, contur);
            x++;

            // check for decision parameter
            // and correspondingly
            // update d, x, y
            if (d > 0) {
                y--;
                d = d + FOUR * (x - y) + TEN;
            } else {
                d = d + FOUR * x + SIX;
            }
            drawCircle(temp, xc, yc, x, y, contur);
        }
    }

    private void drawCircle(final BufferedImage temp, final int xc,
                final int yc, final int x, final int y, final Color c) {
        ColorUtils.paintPixel(temp, xc + x, yc + y, c);
        ColorUtils.paintPixel(temp, xc - x, yc + y, c);
        ColorUtils.paintPixel(temp, xc + x, yc - y, c);
        ColorUtils.paintPixel(temp, xc - x, yc - y, c);
        ColorUtils.paintPixel(temp, xc + y, yc + x, c);
        ColorUtils.paintPixel(temp, xc - y, yc + x, c);
        ColorUtils.paintPixel(temp, xc + y, yc - x, c);
        ColorUtils.paintPixel(temp, xc - y, yc - x, c);
    }

}
