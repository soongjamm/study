package com.soongjamm.startboot.godOfJava.nested_class;

public class AnonymousSample {
    public static void main(String[] args) {
        AnonymousSample sample = new AnonymousSample();
        sample.setButtonListener();
    }

    public void setButtonListener() {
        MagicButton button = new MagicButton();
        EventListener listener = new EventListener() {
            @Override
            public void onClick() {
                System.out.println("magic");
            }
        };
        button.setListener(listener);
        button.onClickProcess();
    }
}
