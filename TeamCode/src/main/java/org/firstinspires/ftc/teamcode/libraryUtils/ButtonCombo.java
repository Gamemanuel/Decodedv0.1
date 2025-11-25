package org.firstinspires.ftc.teamcode.libraryUtils;

/**
 * Handles toggling using 2 button inputs.
 */
public class ButtonCombo {
    private final boolean button1;
    private final boolean button2;
    private boolean attempt = false;
    private boolean toggle = false;

    public ButtonCombo(boolean button1, boolean button2) {
        this.button1 = button1;
        this.button2 = button2;
    }
    public void check() {
        if (button1 && button2 && !attempt) {
            toggle = !toggle;
            attempt = true;
        } else if (!(button1 && button2)) {
            attempt = false;
        }
    }
    public boolean get() {
        return toggle;
    }

}
