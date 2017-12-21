package interfaces;

import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

public interface Visitor {
    /**
     *  Interfata pentru clasa ce va reprezenta Visitorul din
     *  Visitor Pattern.
     * @param shape ~ interfata ce va reprezenta Visitable;
     */

    void visit(Line shape);

    void visit(Square shape);

    void visit(Rectangle shape);

    void visit(Circle shape);

    void visit(Triangle shape);

    void visit(Diamond shape);

    void visit(Polygon shape);

}
