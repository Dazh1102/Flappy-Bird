package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import model.Bird;
import model.Character;
import controller.CollisionManager;
import model.Pipe;
import model.Plane;
import view.GameScreen;
import view.RankingScreen;
import view.SelectionScreen;
import view.StartScreen;
import model.GameModelInterface;
import model.Hero;

public class GameController implements GameControllerInterface, ActionListener, KeyListener {

	private GameScreen gameScreen;
	private GameModelInterface gameModel;
	private Character character;
	private ArrayList<Pipe> pipes;
	private Random random = new Random();
	private Timer gameLoop, placePipeTimer;
	private JFrame mainFrame;
	private CharacterFactory factory;
	private String difficultyLevel;
	private boolean Pause = false; 

	public GameController(GameModelInterface gameModel, JFrame mainFrame, CharacterFactory factory) {
		this.gameModel = gameModel;
		this.character = gameModel.getCharacter();
		this.pipes = gameModel.getPipes();
		this.mainFrame = mainFrame;
		this.factory = factory;
	}

	public boolean isPause() {
		return Pause;
	}

	public void togglePause() {
		if (Pause) {
			gameLoop.start();
			placePipeTimer.start();
			Pause = false;
		} else {
			gameLoop.stop();
			placePipeTimer.stop();
			Pause = true;
		}
		gameScreen.togglePauseIcon();
	}

	public void startScreen() {
		//observer
		gameModel.notifyStateObserver("StartScreen");
//		StartScreen startScreen = new StartScreen(mainFrame, gameModel, this);
//		mainFrame.setContentPane(startScreen);
//		mainFrame.revalidate();
//		startScreen.requestFocusInWindow();
	}

	public void startSelection() {
//		SelectionScreen selectionScreen = new SelectionScreen(gameModel, this);
//		mainFrame.setContentPane(selectionScreen);
//		mainFrame.revalidate();
//		selectionScreen.requestFocusInWindow();
		
		//observer
		gameModel.notifyStateObserver("SelectionScreen");
	}

	public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	@Override
	public void placePipes() {
		int screenWidth = gameScreen.getWidth();
        int screenHeight = gameScreen.getHeight();
        int minPipeWidth = 50;
        int maxPipeWidth = 100;
        int minPipeHeight = 300;
        int maxPipeHeight = 600;

        if ("Easy".equalsIgnoreCase(difficultyLevel)) {
            minPipeWidth = 40;
            maxPipeWidth = 70;
            minPipeHeight = 300;
            maxPipeHeight = 500;
        } else if ("Medium".equalsIgnoreCase(difficultyLevel)) {
            minPipeWidth = 50;
            maxPipeWidth = 90;
            minPipeHeight = 350;
            maxPipeHeight = 550;
        } else if ("Hard".equalsIgnoreCase(difficultyLevel)) {
            minPipeWidth = 40;
            maxPipeWidth = 100;
            minPipeHeight = 300;
            maxPipeHeight = 500;
        }

        int pipeWidth = minPipeWidth + (int) (Math.random() * (maxPipeWidth - minPipeWidth));
        int pipeHeight = minPipeHeight + (int) (Math.random() * (maxPipeHeight - minPipeHeight));

        int randomPipeY = (int) (screenHeight / 2 - Math.random() * (screenHeight / 4));
        int openingSpace = screenHeight / 4;

        Pipe topPipe = new Pipe(screenWidth, randomPipeY - screenHeight / 2, pipeWidth, pipeHeight,
                gameScreen.getTopPipeImg()); // Set pipe speed and size
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(screenWidth, topPipe.getY() + topPipe.getHeight() + openingSpace, pipeWidth, pipeHeight,
                gameScreen.getBottomPipeImg()); // Set pipe speed and size
        pipes.add(bottomPipe);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameModel.isGameOver()) {
			move();
			gameScreen.repaint();
		}
	}

	@Override
	public void move() {
		character.move();
		for (Pipe pipe : pipes) {
			pipe.move();
			if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
				gameModel.setScore(gameModel.getScore() + 0.5);
				pipe.setPassed(true);
			}
			if (CollisionManager.checkCollision(character, pipe)) {
				endGame();
			}
		}
		if (character.getY() > gameScreen.getHeight()) {
			endGame();
		}
	}

	@Override
	public void startGame() {
		this.character = gameModel.getCharacter();
		if (character == null) {
			System.out.println("Character is null in startGame");
			return;
		}

		//observer
		gameModel.notifyStateObserver("GameScreen");

		if (gameScreen == null) {
			gameScreen = new GameScreen(mainFrame, gameModel, this);
		}
		mainFrame.setContentPane(gameScreen);
		mainFrame.revalidate();
		gameScreen.requestFocusInWindow();

		placePipeTimer = new Timer(1500, e -> placePipes());
		placePipeTimer.start();

		int gameLoopDelay; // in milliseconds
	    int pipeSpeed; // in pixels per frame
	    int pipeWidth;
	    int pipeHeight;

	    // Determine delay, character speed, and pipe properties based on difficulty level
	    if ("Medium".equalsIgnoreCase(difficultyLevel)) {
	        gameLoopDelay = 1000 / 40; // Medium speed (40 FPS)
	        character.setSpeed(8); // Speed for medium difficulty
	        pipeSpeed = -6; // Medium pipe speed
	        pipeWidth = 60; // Medium pipe width
	        pipeHeight = 500; // Medium pipe height
	    } else if ("Hard".equalsIgnoreCase(difficultyLevel)) {
	        gameLoopDelay = 1000 / 60; // Hard speed (60 FPS)
	        character.setSpeed(12); // Speed for hard difficulty
	        pipeSpeed = -8; // Hard pipe speed
	        pipeWidth = 50; // Hard pipe width
	        pipeHeight = 450; // Hard pipe height
	    } else {
	        // Default to Easy level
	        gameLoopDelay = 1000 / 30; // Easy speed (25 FPS)
	        character.setSpeed(5); // Speed for easy difficulty
	        pipeSpeed = -2; // Easy pipe speed
	        pipeWidth = 50; // Easy pipe width
	        pipeHeight = 500; // Easy pipe height
	    }

	    // Update the pipe properties based on difficulty level
	    Pipe.setPipeSpeed(pipeSpeed);
	    Pipe.setPipeWidth(pipeWidth);
	    Pipe.setPipeHeight(pipeHeight);

	    // Initialize the game loop timer
	    gameLoop = new Timer(gameLoopDelay, this);
	    gameLoop.start();
	}

	@Override
	public void endGame() {
		gameModel.setGameOver(true);
		placePipeTimer.stop();
		gameLoop.stop();
		
		// Lưu điểm số của người chơi, mới thêm
		String playerName = gameModel.getPlayerName();
        double playerScore = gameModel.getScore();
        gameModel.getScoreManagement().addScore(playerName, playerScore);
        gameModel.getScoreManagement().printSortedScores();
        
        //observer
        gameModel.notifyStateObserver("RankingScreen");
		// Create and display the RankingScreen
//		RankingScreen rankingScreen = new RankingScreen(mainFrame, gameModel, this);
//		rankingScreen.printCurrentScore();
//		rankingScreen.printTop3Score();
//		mainFrame.setContentPane(rankingScreen);
//		mainFrame.revalidate();
//		rankingScreen.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (gameModel.isGameOver()) {
				resetGame();
			} else {
				character.setVelocityY(-9);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_S) { // Key 'S' to activate skill
			if (!character.isSkillOnCooldown()) {
				character.useSkill(gameModel); // Use the correct skill method
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_P) {
			togglePause();
		}
	}

	@Override
	public void resetGame() {
		character.reset(gameScreen.getWidth() / 8, gameScreen.getHeight() / 2);
		character.resetSkill(); // Reset skill khi trò chơi được reset
		pipes.clear();
		gameModel.setGameOver(false);
		gameModel.setScore(0);
		gameLoop.restart();
		placePipeTimer.restart();
	}

	// khởi tạo nhân vật để chơi game
	public Character initializeCharacter(String type) {
		Character character;
		character = factory.createCharacter(type);
		return character;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
