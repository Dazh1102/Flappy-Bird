package controller;

import model.Character;

public interface GameControllerInterface  {
	
	public void startScreen();
	public void startGame();
	public void endGame();
	public void resetGame();
	public void placePipes();
	public void move();
	public void startSelection();
	//public Character createCharacter(String type);
	public Character initializeCharacter(String type);
	public void togglePause();
	public boolean isPause();
	public void setDifficultyLevel(String string);
}
