package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import controller.GameControllerInterface;
import model.Bird;
import model.Character;
import model.GameModel;
import model.GameModelInterface;
import model.StateObserver;

public class StartScreen extends JPanel implements StateObserver {

	 private JButton startButton;
	    private JButton exitButton;
	    private Image backgroundImg;
	    private GameModelInterface gameModel;
	    private GameControllerInterface gameControl;
	    private JFrame mainFrame;

	    public StartScreen(JFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameControl) {
	        this.mainFrame = mainFrame;
	        this.gameModel = gameModel;
	        this.gameControl = gameControl;

	        // Add this StartScreen as an observer to the model
	        gameModel.addStateObserver(this);

	        setPreferredSize(new Dimension(360, 640));
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        loadBackgroundImage();

	        startButton = createButton("/resources/start.png", 120, 50);
	        startButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mainFrame.getContentPane().removeAll();
	                gameControl.startSelection();
	               
	            }
	        });

	        exitButton = createButton("/resources/exit.png", 120, 50);
	        exitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });

	        add(Box.createRigidArea(new Dimension(0, 200)));
	        add(startButton);
	        add(Box.createRigidArea(new Dimension(0, 20)));
	        add(exitButton);
	    }

	private void loadBackgroundImage() {
		URL imageUrlBackground = getClass().getResource("/resources/startbg.png");
		if (imageUrlBackground != null) {
			backgroundImg = new ImageIcon(imageUrlBackground).getImage();
		} else {
			System.err.println("Could not find image file: /resources/startbg.png");
		}
	}

	private JButton createButton(String imageUrl, int width, int height) {
		JButton button = new JButton();
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		URL buttonIconUrl = getClass().getResource(imageUrl);
		if (buttonIconUrl != null) {
			ImageIcon icon = new ImageIcon(buttonIconUrl);
			Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(scaledImage));
		} else {
			System.err.println("Could not find image file: " + imageUrl);
		}

		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);

		return button;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImg != null) {
			g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public void updateState(String newState) {
		if ("SelectionScreen".equalsIgnoreCase(newState)) {
			gameModel.setCurrentState(newState);
			SelectionScreen selectionScreen = new SelectionScreen(mainFrame, gameModel, gameControl);
			mainFrame.setContentPane(selectionScreen);
			mainFrame.revalidate();
			selectionScreen.requestFocusInWindow();
		}
	}


}
