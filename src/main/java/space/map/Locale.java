package space.map;

public class Locale {

    private int coordinateX;
    private int coordinateY;

    public Locale(int coordinateX, int coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public void setCoordinateX(int x) {
        coordinateX = x;
    }
    public void setCoordinateY(int y) {
        coordinateY = y;
    }
    public int getCoordinateX() {
        return coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }
}
