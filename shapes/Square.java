package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;

import java.awt.Color;

public final class Square implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui patrat.
     */

    private int x, y, dimension;
    private Color contur, fill;


    public Square(final int x, final int y, final int dimension, final String colorContur,
                  final int alphaContur, final String color, final int alpha) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
        this.contur = ColorUtils.parseColorARGB(colorContur, alphaContur);
        this.fill = ColorUtils.parseColorARGB(color, alpha);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDimension() {
        return dimension - 1;
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
