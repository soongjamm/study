package com.soongjamm.startboot.godOfJava.nested_class;

public class MagicButton {
    public MagicButton() {
    }

    private EventListener listener;

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void onClickProcess() {
        if (listener != null) {
            listener.onClick();
        }
    }
}
