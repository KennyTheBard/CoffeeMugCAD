package shapes;

import utils.ColorUtils;
import interfaces.Shape;
import interfaces.Visitor;

import java.awt.Color;
import java.io.IOException;

import utils.FileReader;
import utils.Max;

public final class Triangle implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui triunghi.
     */

    private int x1, y1, x2, y2, x3, y3;
    private Color contur, fill;

    private static final int THREE = 3;

    public Triangle(final int x1, final int y1, final int x2, final int y2,
                    final int x3, final int y3, final FileReader fs) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
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

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
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

    public int getMaxWidth() {
        return Max.max(Max.max(x1, x2), x3);
    }

    public int getMaxHeight() {
        return Max.max(Max.max(y1, y2), y3);
    }

    public int getCenterX() {
        return new Integer((x1 + x2 + x3) / THREE);
    }

    public int getCenterY() {
        return new Integer((y1 + y2 + y3) / THREE);
    }
}
