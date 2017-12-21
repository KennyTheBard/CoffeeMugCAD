package control;

import utils.FileReader;
import interfaces.Shape;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.io.IOException;

public final class Facton {
    /**
     *  Fac(tory) + (Single)ton serveste ca mijloc de initializare a formelor
     *  sau detaliilor grafice cerute in fisierul de input.
     */

    private static Facton monopol = new Facton();

    private Facton() { }

    public static Facton getFacton() {
        return monopol;
    }

    public Shape getShape(final String type, final FileReader fs) {
        try {
            switch (type) {
                case "SQUARE":
                    return new Square(fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs.nextWord(), fs.nextInt(),
                            fs.nextWord(), fs.nextInt());
                case "RECTANGLE":
                    return new Rectangle(fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs.nextInt(), fs);
                case "CIRCLE":
                    return new Circle(fs.nextInt(), fs.nextInt(), fs.nextInt(),
                            fs.nextWord(), fs.nextInt(),
                            fs.nextWord(), fs.nextInt());
                case "TRIANGLE":
                    return new Triangle(fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs);
                case "DIAMOND":
                    return new Diamond(fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs.nextInt(), fs);
                case "POLYGON":
                    return new Polygon(fs.nextInt(), fs);
                default: /** Cazul LINE */
                    return new Line(fs.nextInt(), fs.nextInt(), fs.nextInt(),
                            fs.nextInt(), fs.nextWord(), fs.nextInt());
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }
}
