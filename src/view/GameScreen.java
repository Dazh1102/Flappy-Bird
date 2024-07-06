package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameControllerInterface;
import model.Bird;
import model.Character;
import model.GameModelInterface;
import model.Hero;
import model.Pipe;
import model.Plane;
import model.StateObserver;

public class GameScreen extends JPanel implements StateObserver {
    private int boardWidth = 360;
    private int boardHeight = 640;

    private Image backgroundImg;
    private Image birdImg, heroImg, planeImg;  
    private Image topPipeImg, bottomPipeImg, topPauseImg, topPlayImg;
    private GameModelInterface gameModel;
    private GameControllerInterface gameController;
    private JFrame mainFrame;
    private JButton buttonPause;

    public GameScreen(JFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameController) {
        this.mainFrame = mainFrame;
        this.gameModel = gameModel;
        this.gameController = gameController;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener((KeyListener) gameController); // Add this line to listen to keyboard events

        loadImage();
        gameModel.addStateObserver(this);
        
        initPauseButton();
        requestFocusInWindow();
    }

    public Image getBottomPipeImg() {
        return bottomPipeImg;
    }

    public void setBottomPipeImg(Image bottomPipeImg) {
        this.bottomPipeImg = bottomPipeImg;
    }

    public Image getTopPipeImg() {
        return topPipeImg;
    }

    
    
    private void loadImage() {
    
        backgroundImg = loadImage("/resources/flappybirdbg.png");
        birdImg = loadImage("/resources/flappybird.png");
        heroImg = loadImage("/resources/heroC.png");  // Load the hero image
        planeImg = loadImage("/resources/planeC.png"); // Load the plane image
        topPipeImg = loadImage("/resources/toppipe.png");
        bottomPipeImg = loadImage("/resources/bottompipe.png");
        topPauseImg = loadImage("/resources/pause.png");
		topPlayImg = loadImage("/resources/play.png");
    }
    

    private Image loadImage(String path) {
        URL imageUrl = getClass().getResource(path);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Could not find image file: " + path);
            return null;
        }
    }
    

  


    private Image getCharacterImage(Character character) {
        if (character instanceof Bird) {
            return birdImg;
        } else if (character instanceof Hero) {
            return heroImg;
        } else if (character instanceof Plane) {
            return planeImg;
        }
        return null;  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        drawDecoratedCircle(g); // Draw a circle with the letter "S"
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);

        Character character = gameModel.getCharacter();
        if (character != null) {
            Image characterImg = getCharacterImage(character);  // Get the correct character image
            g.drawImage(characterImg, character.getX(), character.getY(), character.getWidth(), character.getHeight(), null);
        }

        ArrayList<Pipe> pipes = gameModel.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameModel.isGameOver()) {
            g.drawString("Game Over: " + (int) gameModel.getScore(), 10, 70);
        } else {
            g.drawString(String.valueOf((int) gameModel.getScore()), 10, 35);
        }
    }

    private void drawDecoratedCircle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int circleX = 80;
        int circleY = boardHeight - 615;
        int radius = 20;

        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fill(new Ellipse2D.Double(circleX - radius + 5, circleY - radius + 5, 2 * radius, 2 * radius));

        g2d.setColor(Color.YELLOW);
        g2d.fill(new Ellipse2D.Double(circleX - radius, circleY - radius, 2 * radius, 2 * radius));

        g2d.setColor(Color.ORANGE);
        g2d.draw(new Ellipse2D.Double(circleX - radius, circleY - radius, 2 * radius, 2 * radius));

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        Character character = gameModel.getCharacter();
        if (character.isSkillOnCooldown()) {
            int seconds = character.getRemainingCooldownTime() / 1000;
            String cooldownText = String.valueOf(seconds);
            int strWidth = g2d.getFontMetrics().stringWidth(cooldownText);
            g2d.drawString(cooldownText, circleX - strWidth / 2, circleY + radius / 2 - 2);
        } else {
            int strWidth = g2d.getFontMetrics().stringWidth("S");
            g2d.drawString("S", circleX - strWidth / 2, circleY + radius / 2 - 2);
        }
    }
    
 // làm nút button Pause

 	private void initPauseButton() {
 		topPauseImg = topPauseImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
 		topPlayImg = topPlayImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
 		buttonPause = new JButton(new ImageIcon(topPauseImg));
 		buttonPause.setSize(50, 50);
 		buttonPause.setLocation(290, 0);

 		buttonPause.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				gameController.togglePause();
 				GameScreen.this.requestFocusInWindow();
 			}
 		});
 		setLayout(null);
 		add(buttonPause);

 	}

 	// làm button play
 	public void togglePauseIcon() {
 		if (gameController.isPause()) {
 			buttonPause.setIcon(new ImageIcon(topPlayImg));
 		} else {
 			buttonPause.setIcon(new ImageIcon(topPauseImg));
 		}
 	}

    @Override
    public void updateState(String newState) {
        if ("RankingScreen".equalsIgnoreCase(newState)) {
            gameModel.setCurrentState(newState);
            RankingScreen rankingScreen = new RankingScreen(mainFrame, gameModel, gameController);
            rankingScreen.printCurrentScore();
            rankingScreen.printTop3Score();
            mainFrame.setContentPane(rankingScreen);
            mainFrame.revalidate();
            rankingScreen.requestFocusInWindow();
        }
    }
}
