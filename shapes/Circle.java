package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;

import java.awt.Color;

public final class Circle implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui cerc.
     */

    private int x, y, radius;
    private Color contur, fill;

    public Circle(final int x, final int y, final int radius, final String colorContur,
                  final int alphaContur, final String color, final int alpha) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.contur = ColorUtils.parseColorARGB(colorContur, alphaContur);
        this.fill = ColorUtils.parseColorARGB(color, alpha);
    }

    public int getCenterX() {
        return x;
    }

    public int getCenterY() {
        return y;
    }

    public int getRadius() {
        return radius;
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
