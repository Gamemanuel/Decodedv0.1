package org.firstinspires.ftc.teamcode.libraryUtils;

public class Toggle {
    private boolean bool;

    public Toggle(boolean bool) {
        this.bool = bool;
    }
    public void toggle() {
        bool = !bool;
    }
    public boolean get() {
        return bool;
    }
}
