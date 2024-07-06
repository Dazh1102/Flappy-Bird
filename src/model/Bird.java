package model;

import java.awt.Image;

public class Bird extends Character {
    
    public Bird(int x, int y, int width, int height, Image image, GameModelInterface gameModel) {
        super(x, y, width, height, image);
        setSkill(new BirdSkill(gameModel));  // Set the skill directly during initialization
    }
    
    @Override
    public void move() {
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
    }

    @Override
    public void useSkill(GameModelInterface gameModel) {
        getSkill().skill();
    }
}
