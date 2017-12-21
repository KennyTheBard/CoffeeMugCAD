package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;
import utils.FileReader;

import java.awt.Color;
import java.io.IOException;

public final class Rectangle implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui dreptungho.
     */

    private int x, y, height, width;
    private Color contur, fill;

    public Rectangle(final int x, final int y, final int height,
                     final int width, final FileReader fs) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height - 1;
    }

    public int getWidth() {
        return width - 1;
    }

    public Color getContur() {
        return contur;
    }

    public Color getFill() {
        return fill;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }
}
