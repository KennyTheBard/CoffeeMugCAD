package shapes;

import utils.ColorUtils;
import utils.FileReader;
import interfaces.Shape;
import interfaces.Visitor;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import utils.Max;

public final class Polygon implements Shape {
    /**
     *  Clasa Visitable generata pentru a gazdui parametrii unui poligon.
     */

    private ArrayList<Integer> xs = new ArrayList<>(), ys = new ArrayList<>();
    private Color contur, fill;

    public Polygon(final int numPoints, final FileReader fs) {
        for (int i = 0; i < numPoints; i++) {
            try {
                xs.add(fs.nextInt());
                ys.add(fs.nextInt());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

    public int getXs(final int pos) {
        return xs.get(pos);
    }

    public int getYs(final int pos) {
        return ys.get(pos);
    }

    public int getNumPoints() {
        return xs.size();
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
        int ret = xs.get(0);
        for (int x : xs) {
            ret = Max.max(ret, x);
        }
        return ret;
    }

    public int getMaxHeight() {
        int ret = ys.get(0);
        for (int y : ys) {
            ret = Max.max(ret, y);
        }
        return ret;
    }

    public int getCenterX() {
        int ret = 0;
        for (int x : xs) {
            ret += x;
        }
        return new Integer(ret / xs.size());
    }

    public int getCenterY() {
        int ret = 0;
        for (int y : ys) {
            ret += y;
        }
        return new Integer(ret / ys.size());
    }
}
