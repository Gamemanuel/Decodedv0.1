package org.firstinspires.ftc.teamcode.libraryUtils;

public class buttonCombo {
    private final boolean button1;
    private final boolean button2;
    private boolean attempt = false;
    private final boolean toggle;

    public buttonCombo(boolean button1, boolean button2, boolean toggle) {
        this.button1 = button1;
        this.button2 = button2;
        this.toggle = toggle;

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
