package view;

import javax.swing.JFrame;

import controller.CharacterFactory;
import controller.GameController;
import controller.GameControllerInterface;
import model.Bird;
import model.GameModel;
import model.GameModelInterface;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
//        GameModelInterface gameModel = new GameModel();
//        CharacterFactory factory = new CharacterFactory(gameModel);
//        
//        JFrame mainFrame1 = new JFrame("StartScreen 1");
//        mainFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame1.setSize(360, 640);
//        mainFrame1.setResizable(false);
//        mainFrame1.setLocationRelativeTo(null);
//        GameControllerInterface gameController1 = new GameController(gameModel, mainFrame1, factory);
//        StartScreen startScreen1 = new StartScreen(mainFrame1, gameModel, gameController1);
//        mainFrame1.setContentPane(startScreen1);
//        mainFrame1.setVisible(true);
//
//        JFrame mainFrame2 = new JFrame("StartScreen 2");
//        mainFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame2.setSize(360, 640);
//        mainFrame2.setResizable(false);
//        mainFrame2.setLocationRelativeTo(null);
//        GameControllerInterface gameController2 = new GameController(gameModel, mainFrame2, factory);
//        StartScreen startScreen2 = new StartScreen(mainFrame2, gameModel, gameController2);
//        mainFrame2.setContentPane(startScreen2);
//        mainFrame2.setVisible(true);
//
//        JFrame mainFrame3 = new JFrame("StartScreen 3");
//        mainFrame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame3.setSize(360, 640);
//        mainFrame3.setResizable(false);
//        mainFrame3.setLocationRelativeTo(null);
//        GameControllerInterface gameController3 = new GameController(gameModel, mainFrame3, factory);
//        StartScreen startScreen3 = new StartScreen(mainFrame3, gameModel, gameController3);
//        mainFrame3.setContentPane(startScreen3);
//        mainFrame3.setVisible(true);
		
		GameModelInterface gameModel = new GameModel();
        CharacterFactory factory = new CharacterFactory(gameModel);
        
        int numFrames = 3;
        JFrame[] frames = new JFrame[numFrames];
        GameControllerInterface[] gameControllers = new GameControllerInterface[numFrames];
        StartScreen[] startScreens = new StartScreen[numFrames];
        
        for (int i = 0; i < numFrames; i++) {
            frames[i] = new JFrame("StartScreen " + (i + 1));
            frames[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frames[i].setSize(360, 640);
            frames[i].setResizable(false);
            frames[i].setLocationRelativeTo(null);
            
            gameControllers[i] = new GameController(gameModel, frames[i], factory);
            startScreens[i] = new StartScreen(frames[i], gameModel, gameControllers[i]);
            
            frames[i].setContentPane(startScreens[i]);
            frames[i].setVisible(true);
        }
    

    }

}
