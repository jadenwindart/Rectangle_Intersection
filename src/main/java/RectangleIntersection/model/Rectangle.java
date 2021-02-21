package RectangleIntersection.model;


import java.util.List;

public class Rectangle {
    private Integer xCoord;
    private Integer yCoord;
    private Integer width;
    private Integer height;
    private List<Integer> rectangleConstructors;

    public Rectangle(Integer xCoord, Integer yCoord, Integer width, Integer height) throws Exception{
        if (xCoord == null || yCoord == null || width == null || height == null) {
            throw  new Exception("Rectangle data is null");
        }

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.height = height;
    }

    public boolean checkRectangleIntersection(Integer x, Integer y, Integer w, Integer h) {
        if (x == null || y == null || w == null || h == null) {
            return false;
        }

        if ((this.yCoord + this.height) < y || (y + h) < this.yCoord) {
            return false;
        }

        if ((this.xCoord + this.width) < x || (x + w) < this.xCoord) {
            return false;
        }

        return true;
    }

    public Intersection computeIntersection(List<Integer> rectangleIndex, Integer x, Integer y, Integer w, Integer h) {
        Integer topLeftX = Math.max(x , this.xCoord);
        Integer topLeftY = Math.max(y, this.yCoord);
        Integer width = Math.min((this.xCoord + this.width), (x + w)) - topLeftX;
        Integer height = Math.min((this.yCoord + this.height), (y + h)) - topLeftY;

        return new Intersection(topLeftX, topLeftY, width, height, rectangleIndex);
    }

    public Integer getXCoord() {
        return xCoord;
    }

    public Integer getYCoord() {
        return yCoord;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setRectangleConstructors(List<Integer> rectangleConstructors) {
        this.rectangleConstructors = rectangleConstructors;
    }

    public List<Integer> getRectangleConstructors() {
        return rectangleConstructors;
    }
}
