package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;
import utils.FileReader;

import java.awt.Color;
import java.io.IOException;

public final class Diamond implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui romb.
     */

    private int x, y, height, width;
    private Color contur, fill;

    public Diamond(final int x, final int y, final int width, final int height,
                   final FileReader fs) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        try {
            String colorContur = fs.nextWord();
            int alphaContur = fs.nextInt();
            String color = fs.nextWord();
            int alpha = fs.nextInt();
            this.contur = ColorUtils.parseColorARGB(colorContur, alphaContur);
            this.fill = ColorUtils.parseColorARGB(color, alpha);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getCenterX() {
        return x;
    }

    public int getCenterY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getContur() {
        return contur;
    }

    public Color getFill() {
        return fill;
    }

    public int getUpX() {
        return x;
    }

    public int getUpY() {
        return y - height / 2;
    }

    public int getRightX() {
        return x + width / 2;
    }

    public int getRightY() {
        return y;
    }

    public int getDownX() {
        return x;
    }

    public int getDownY() {
        return y + height / 2;
    }

    public int getLeftX() {
        return x - width / 2;
    }

    public int getLeftY() {
        return y;
    }


    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }
}
