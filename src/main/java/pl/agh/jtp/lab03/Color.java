package pl.agh.jtp.lab03;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public enum Color {
    RED(255, 0, 0),
    BLUE(0, 255, 0),
    GREEN(0, 0, 255);

    private final int r;
    private final int b;
    private final int g;

    private Color(int r, int b, int g) {
        if(r > 255 || b > 255 || g > 255)
            throw new IllegalArgumentException("Too high value");
        this.r = r;
        this.b = b;
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public int getR() {
        return r;
    }

    public int getB() {
        return b;
    }

    public String toString() {
        return "{#" + Integer.toHexString(256 * 256 * 256 + r * 256 * 256 + b * 256 + g).substring(1) + "}";
    }

    public boolean equals(Color color) {
        return toString().equals(color.toString());
    }
}
