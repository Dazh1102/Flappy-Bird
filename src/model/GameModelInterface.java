package model;

import java.util.ArrayList;

public interface GameModelInterface {
	
	public void addStateObserver(StateObserver o);
	public void removeObserver(StateObserver o);
	public void notifyStateObserver(String newState);
	public void notifySelectionObserver();
	public Character getCharacter();
    public void setCharacter(Character character);
    public ArrayList<Pipe> getPipes();  
    public void setPipes(ArrayList<Pipe> pipes);
    public boolean isGameOver();
    public double getScore();
    public void setGameOver(boolean gameOver);
    public void setScore(double score);
    public String getPlayerName();
    public void setPlayerName(String playerName);
    public ScoreManagement getScoreManagement();
    public void setCurrentState(String currentState);
    

}
