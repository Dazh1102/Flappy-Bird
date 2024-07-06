package controller;

import model.Bird;
import model.Character;
import model.GameModelInterface;
import model.Hero;
import model.Plane;

public class CharacterFactory {
	
	private GameModelInterface model;

	public CharacterFactory(GameModelInterface model) {
		super();
		this.model = model;
	}

	public GameModelInterface getModel() {
		return model;
	}

	public void setModel(GameModelInterface model) {
		this.model = model;
	}
	
	public Character createCharacter(String type) {
        Character character = null;
        if(type.equalsIgnoreCase("bird")) {
            character = new Bird(45, 320, 34, 24, null, model);
        } else if(type.equalsIgnoreCase("plane")) {
            character = new Plane(45, 320, 64, 54, null, model);
        } else if(type.equalsIgnoreCase("hero")) {
            character = new Hero(45, 320, 64, 54, null);
        }
        return character;
    }

}
