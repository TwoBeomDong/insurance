package model.user;

public enum Authority {
	Administrator_Employee(3), Employee(2), Viewer(1), None(0);

    private final int level;

    Authority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
