package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;

import java.awt.Color;

public final class Line implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unei linii.
     */

    private int xStart, yStart, xEnd, yEnd;
    private Color color;

    public Line(final int xStart, final int yStart, final int xEnd,
                final int yEnd, final Color color) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    public Line(final int xStart, final int yStart, final int xEnd,
                final int yEnd, final String color, final int alpha) {
        this(xStart, yStart, xEnd, yEnd, ColorUtils.parseColorARGB(color, alpha));
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }
}
