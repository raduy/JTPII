package pl.agh.jtp.lab10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Configuration {
    private List<Figure> figures = new ArrayList<>();

    public Configuration() {
        super();
    }

    public Configuration(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Configuration)) {
            return false;
        }

        Configuration that = (Configuration) o;

        if(figures != null ? !figures.equals(that.figures) : that.figures != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return figures != null ? figures.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "figures=" + figures +
                '}';
    }
}
