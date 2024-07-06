package model;

import java.awt.Image;

public class Plane extends Character {

    private final int defaultGravity;

    public Plane(int x, int y, int width, int height, Image image, GameModelInterface gameModel) {
        super(x, y, width, height, image);
        this.defaultGravity = gravity;
        this.skill = new PlaneSkill(this); // Pass reference to PlaneSkill
    }

    @Override
    public void move() {
        skill.skill(); // Apply skill effect on every move
        if (!((PlaneSkill) skill).isActive()) {
            velocityY += gravity;
        }
        y += velocityY;
        y = Math.max(y, 0);
    }

    @Override
    public void useSkill(GameModelInterface gameModel) {
        ((PlaneSkill) skill).activate();
    }

    public int getDefaultGravity() {
        return defaultGravity;
    }
}
