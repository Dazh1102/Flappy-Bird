package model;

import java.awt.Image;

public class Pipe extends Character {

	private boolean passed;
	private static int pipeSpeed = -4; // Default speed
    private static int pipeWidth = 70; // Default width
    private static int pipeHeight = 550; // Default height

    public Pipe(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.passed = false;
    }

    @Override
    public void move() {
        x += velocityX;
        x += pipeSpeed;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public static void setPipeSpeed(int speed) {
        pipeSpeed = speed;
    }

    public static void setPipeWidth(int width) {
        pipeWidth = width;
    }

    public static void setPipeHeight(int height) {
        pipeHeight = height;
    }
    public static int getPipeWidth() {
        return pipeWidth;
    }

    public static int getPipeHeight() {
        return pipeHeight;
    }

}
