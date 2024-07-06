package model;

import java.awt.Image;

public class Hero extends Character {
    private HeroSkill heroSkill;

    public Hero(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.heroSkill = new HeroSkill(this);
    }

    @Override
    public void move() {
        heroSkill.skill(); // Apply skill effect on every move
        if (!heroSkill.isActive()) { // Apply gravity only if skill is not active
            velocityY += gravity;
        }
        y += velocityY;
        y = Math.max(y, 0);
    }

    @Override
    public void useSkill(GameModelInterface gameModel) {
        heroSkill.activate();
    }
}
