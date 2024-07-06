package controller;

import java.awt.Rectangle;

import model.Character;
import model.Pipe;

public class CollisionManager {
	
	public static boolean checkCollision(Character character, Pipe pipe) {
        Rectangle characterBounds = character.getBound();
        Rectangle pipeBounds = pipe.getBound();
        return characterBounds.intersects(pipeBounds);
    }

}
