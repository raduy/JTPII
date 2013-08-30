package pl.agh.jtp.lab06_home.application;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public enum Session {
    INSTANCE;

    private boolean active;
    private Context currentContext;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(Context currentContext) {
        this.currentContext = currentContext;
    }

    @Override
    public String toString() {
        return "Session{" +
                "active=" + active +
                '}';
    }
}
