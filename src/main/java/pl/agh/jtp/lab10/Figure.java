package pl.agh.jtp.lab10;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a Figure object.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Figure {
    private String id;
    private String name;
    private String clazz;
    private String icon;
    private String hint;

    public Figure() {
    }

    /**
     * Create a new figure object with not-null parameters, if you try put one of those argument as a null it
     * throw NullPointerException.
     * @param name  Figure name
     * @param clazz  Figure class name
     * @param icon  Path to figure icon
     * @param hint  help
     * @param id  Figure ID
     */
    public Figure(String id, String name, String clazz, String icon, String hint) {
        this.id = checkNotNull(id, "Figure ID can't be a null!");
        this.name = checkNotNull(name, "Figure name can't be a null!");
        this.clazz = checkNotNull(clazz, "Figure class name can't be a null!");
        this.hint = checkNotNull(hint, "Figure hint can't be a null!");
        this.icon = checkNotNull(icon, "Figure icon can't be a null!");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Figure)) {
            return false;
        }

        Figure figure = (Figure) o;

        if(!id.equals(figure.id)) {
            return false;
        }
        if (!clazz.equals(figure.clazz)) {
            return false;
        }
        if (!hint.equals(figure.hint)) {
            return false;
        }
        if (!name.equals(figure.name)) {
            return false;
        }
        if (!icon.equals(figure.icon)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + clazz.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + hint.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "clazz='" + clazz + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }
}
