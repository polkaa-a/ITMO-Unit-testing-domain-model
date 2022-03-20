package space.map;

public class Locale {

    private int coordinateX;
    private int coordinateY;

    public Locale(int x, int y){
        coordinateX = x;
        coordinateY = y;
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
