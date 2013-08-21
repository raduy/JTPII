package pl.agh.jtp.lab06_home;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public enum Session {
    INSTANCE;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean equals(Session o) {
        return isActive() == o.isActive();
    }

    @Override
    public String toString() {
        return "Session{" +
                "active=" + active +
                '}';
    }
}
