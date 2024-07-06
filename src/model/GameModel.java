package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameModelInterface {
	
	private boolean gameOver;
	private double score;
	private String playerName; 
	private Character character;
	private ArrayList<Pipe> pipes;
	private ArrayList<StateObserver> stateOb;
	private ScoreManagement scoreManagement; 
	private String currentState; // Track current state
	
	public GameModel() {
		super();
		setCharacter(character);		//skill sẽ được character set sau;
		pipes = new ArrayList<Pipe>();
		stateOb = new ArrayList<>();
		this.gameOver = false;
		this.score = 0;
		this.scoreManagement = new ScoreManagement();
		this.currentState = "StartScreen";
	}


	@Override
	public void addStateObserver(StateObserver o) {
		stateOb.add(o);
		
	}

	@Override
	public void removeObserver(StateObserver o) {
		int i = stateOb.indexOf(o);
		if (i >= 0) { stateOb.remove(i); }
		
	}


	@Override
	public void notifyStateObserver(String newState) {
        List<StateObserver> observersCopy = new ArrayList<>(stateOb); // Create a copy to avoid concurrent modification
        for (StateObserver observer : observersCopy) {
            observer.updateState(newState);
        }
    }
	
	@Override
	public void notifySelectionObserver() {
		// TODO Auto-generated method stub
		
	}
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public ScoreManagement getScoreManagement() {  
        return scoreManagement;
    }
	
	public void setPlayerName(String playerName) { 
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }


	public String getCurrentState() {
		return currentState;
	}


	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
    
    

}
