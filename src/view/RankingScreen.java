package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GameController;
import controller.GameControllerInterface;
import model.GameModel;
import model.GameModelInterface;
import model.StateObserver;

public class RankingScreen extends JPanel implements StateObserver {
	private JPanel topPanel = new JPanel();
	private JLabel score_Label_1 = new JLabel("Your Score: ");
	private JLabel score_Label_2 = new JLabel("0");
	//chỉnh cỡ chữ
	private JPanel centerPanel = new JPanel();
	private JLabel label_top = new JLabel("Top 3 Score: ");
	private JLabel label_top3 = new JLabel("");
	private JLabel label_top3_1 = new JLabel("");

	private JLabel label_name = new JLabel("Name ");
	private JLabel label_score = new JLabel("Score ");
	private JLabel label_rank = new JLabel("Rank ");

	private JLabel label_rank1 = new JLabel("1ST");
	private JLabel label_rank2 = new JLabel("2ND");
	private JLabel label_rank3 = new JLabel("3RD");

	private JLabel label_name1 = new JLabel("Player 1 ");
	private JLabel label_name2 = new JLabel("Player 2 ");
	private JLabel label_name3 = new JLabel("Player 3 ");
	private JLabel label_score1 = new JLabel("0");
	private JLabel label_score2 = new JLabel("0");
	private JLabel label_score3 = new JLabel("0");
	private JPanel bottomPanel = new JPanel();
	private JButton button_1 = new JButton("Menu");
	private JButton button_2 = new JButton("Exit");
	private JButton button_3 = new JButton("NewGame");

	private Image backgroundImg;
	private GameModelInterface gameModel;
	private GameControllerInterface gameControl;
	private JFrame mainFrame;

	public RankingScreen(JFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameControl) {
		this.gameModel = gameModel;
		this.gameControl = gameControl;
		this.mainFrame = mainFrame;

		gameModel.addStateObserver(this);

		loadBackgroundImage();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setupUIComponents();
		addComponentsToBackgroundPanel();

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void setupUIComponents() {
		// chỉnh cõ chữ
		score_Label_2.setFont(new Font("Arial", Font.BOLD, 18));

		label_name1.setFont(new Font("Arial", Font.BOLD, 18));
		label_name2.setFont(new Font("Arial", Font.BOLD, 18));
		label_name3.setFont(new Font("Arial", Font.BOLD, 18));
		label_score1.setFont(new Font("Arial", Font.BOLD, 18));
		label_score2.setFont(new Font("Arial", Font.BOLD, 18));
		label_score3.setFont(new Font("Arial", Font.BOLD, 18));
		
		// Cấu hình topPanel
		topPanel.setLayout(new GridLayout(1, 2));
		score_Label_1.setHorizontalAlignment(SwingConstants.CENTER);
		score_Label_2.setHorizontalAlignment(SwingConstants.CENTER);
		String imagePath_yscore = "/resources/YourScore1.png";
		ImageIcon originalIcon_yscore = new ImageIcon(getClass().getResource(imagePath_yscore));
		// Kích thước mới mong muốn
		int scaledWidth_yscore = 100;
		int scaledHeight_yscore = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_yscore = originalIcon_yscore.getImage().getScaledInstance(scaledWidth_yscore,
				scaledHeight_yscore, Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_yscore = new ImageIcon(scaledImage_yscore);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel score_Label_1 = new JLabel(scaledIcon_yscore);
		topPanel.add(score_Label_1);
		topPanel.add(score_Label_2);
		topPanel.setPreferredSize(new Dimension(640, 200));
		topPanel.setOpaque(false); // Đặt panel trong suốt để hình nền hiển thị

		// Cấu hình centerPanel
		centerPanel.setLayout(new GridLayout(5, 2));
		
		score_Label_2.setHorizontalAlignment(SwingConstants.LEFT);  // Căn ngang sang trái
		score_Label_2.setVerticalAlignment(SwingConstants.CENTER);     // Căn dọc lên trên cùng (nếu cần)

		
		label_top.setHorizontalAlignment(SwingConstants.CENTER);
		label_top.setVerticalAlignment(SwingConstants.CENTER);
		label_top3.setHorizontalAlignment(SwingConstants.CENTER);
		label_top3.setHorizontalAlignment(SwingConstants.CENTER);
		label_top3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_top3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_name1.setHorizontalAlignment(SwingConstants.CENTER);
		label_name1.setVerticalAlignment(SwingConstants.CENTER);
		label_name2.setHorizontalAlignment(SwingConstants.CENTER);
		label_name2.setVerticalAlignment(SwingConstants.CENTER);
		label_name3.setHorizontalAlignment(SwingConstants.CENTER);
		label_name3.setVerticalAlignment(SwingConstants.CENTER);
		label_score1.setHorizontalAlignment(SwingConstants.CENTER);
		label_score1.setVerticalAlignment(SwingConstants.CENTER);
		label_score2.setHorizontalAlignment(SwingConstants.CENTER);
		label_score2.setVerticalAlignment(SwingConstants.CENTER);
		label_score3.setHorizontalAlignment(SwingConstants.CENTER);
		label_score3.setVerticalAlignment(SwingConstants.CENTER);
		label_rank.setHorizontalAlignment(SwingConstants.CENTER);
		label_rank.setVerticalAlignment(SwingConstants.CENTER);
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setVerticalAlignment(SwingConstants.CENTER);
		label_score.setHorizontalAlignment(SwingConstants.CENTER);
		label_score.setVerticalAlignment(SwingConstants.CENTER);
		label_rank1.setHorizontalAlignment(SwingConstants.CENTER);
		label_rank1.setVerticalAlignment(SwingConstants.CENTER);
		label_rank2.setHorizontalAlignment(SwingConstants.CENTER);
		label_rank2.setVerticalAlignment(SwingConstants.CENTER);
		label_rank3.setHorizontalAlignment(SwingConstants.CENTER);
		label_rank3.setVerticalAlignment(SwingConstants.CENTER);

		String imagePath_top_Higher = "/resources/tophigher.png";
		ImageIcon originalIcon_top_Higher = new ImageIcon(getClass().getResource(imagePath_top_Higher));
		// Kích thước mới mong muốn
		int scaledWidth_top_Higher = 120;
		int scaledHeight_top_Higher = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_top_Higher = originalIcon_top_Higher.getImage().getScaledInstance(scaledWidth_top_Higher,
				scaledHeight_top_Higher, Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_top_Higher = new ImageIcon(scaledImage_top_Higher);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_top = new JLabel(scaledIcon_top_Higher);
		centerPanel.add(label_top);
		centerPanel.add(label_top3);
		centerPanel.add(label_top3_1);

		String imagePath = "/resources/rank1.png";
		ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
		// Kích thước mới mong muốn
		int scaledWidth = 50;
		int scaledHeight = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_rank = new JLabel(scaledIcon);

		String imagePath_name = "/resources/name1.png";
		ImageIcon originalIcon_name = new ImageIcon(getClass().getResource(imagePath_name));
		// Kích thước mới mong muốn
		int scaledWidth_name = 50;
		int scaledHeight_name = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_name = originalIcon_name.getImage().getScaledInstance(scaledWidth_name, scaledHeight_name,
				Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_name = new ImageIcon(scaledImage_name);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_name = new JLabel(scaledIcon_name);

		// Đường dẫn tới hình ảnh score
		String imagePath_score = "/resources/score1.png";
		// Tạo ImageIcon từ hình ảnh
		ImageIcon originalIcon_score = new ImageIcon(getClass().getResource(imagePath_score));
		// Kích thước mới mong muốn
		int scaledWidth_score = 50;
		int scaledHeight_score = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_score = originalIcon_score.getImage().getScaledInstance(scaledWidth_score, scaledHeight_score,
				Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_score = new ImageIcon(scaledImage_score);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_score = new JLabel(scaledIcon_score);

		// Đường dẫn tới hình ảnh rank
		String imagePath_rank1 = "/resources/1st.png";
		// Tạo ImageIcon từ hình ảnh
		ImageIcon originalIcon_rank1 = new ImageIcon(getClass().getResource(imagePath_rank1));
		// Kích thước mới mong muốn
		int scaledWidth_rank1 = 50;
		int scaledHeight_rank1 = 15;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_rank1 = originalIcon_rank1.getImage().getScaledInstance(scaledWidth_rank1, scaledHeight_rank1,
				Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_rank1 = new ImageIcon(scaledImage_rank1);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_rank1 = new JLabel(scaledIcon_rank1);

		// Đường dẫn tới hình ảnh rank2
		String imagePath_rank2 = "/resources/2nd.png";
		// Tạo ImageIcon từ hình ảnh
		ImageIcon originalIcon_rank2 = new ImageIcon(getClass().getResource(imagePath_rank2));
		// Kích thước mới mong muốn
		int scaledWidth_rank2 = 50;
		int scaledHeight_rank2 = 15;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_rank2 = originalIcon_rank2.getImage().getScaledInstance(scaledWidth_rank2, scaledHeight_rank2,
				Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_rank2 = new ImageIcon(scaledImage_rank2);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_rank2 = new JLabel(scaledIcon_rank2);

		// Đường dẫn tới hình ảnh rank3
		String imagePath_rank3 = "/resources/3rd.png";
		// Tạo ImageIcon từ hình ảnh
		ImageIcon originalIcon_rank3 = new ImageIcon(getClass().getResource(imagePath_rank3));
		// Kích thước mới mong muốn
		int scaledWidth_rank3 = 50;
		int scaledHeight_rank3 = 20;
		// Scale hình ảnh với kích thước mới
		Image scaledImage_rank3 = originalIcon_rank3.getImage().getScaledInstance(scaledWidth_rank3, scaledHeight_rank3,
				Image.SCALE_SMOOTH);
		// Tạo ImageIcon từ hình ảnh đã scale
		ImageIcon scaledIcon_rank3 = new ImageIcon(scaledImage_rank3);
		// Tạo JLabel và thiết lập biểu tượng là hình ảnh đã scale
		JLabel label_rank3 = new JLabel(scaledIcon_rank3);

		centerPanel.add(label_rank);
		centerPanel.add(label_name);
		centerPanel.add(label_score);

		centerPanel.add(label_rank1);
		centerPanel.add(label_name1);
		centerPanel.add(label_score1);

		centerPanel.add(label_rank2);
		centerPanel.add(label_name2);
		centerPanel.add(label_score2);

		centerPanel.add(label_rank3);
		centerPanel.add(label_name3);
		centerPanel.add(label_score3);
		centerPanel.setOpaque(false); // Đặt panel trong suốt để hình nền hiển thị

		// Cấu hình bottomPanel
		bottomPanel.setLayout(new FlowLayout());
		button_1 = createButton("/resources/menu.png", 80, 30);
		button_2 = createButton("/resources/exit2.png", 80, 30);
		button_3 = createButton("/resources/newGame.png", 100, 30);
		bottomPanel.add(button_1);
		bottomPanel.add(button_3);
		bottomPanel.add(button_2);
		bottomPanel.setPreferredSize(new Dimension(640, 200));
		bottomPanel.setOpaque(false); // Đặt panel trong suốt để hình nền hiển thị

		// hiện thị chức năng
		// 1. thoát
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 2. trở về màn hình bắt đầu
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				gameControl.startScreen();

			}
		});

		// 3. New Game == nút Start
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				gameControl.startSelection();

			}
		});

	}

// vị  trí layout
	private void addComponentsToBackgroundPanel() {
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

// hình nền
	private void loadBackgroundImage() {
		URL imageUrlBackground = getClass().getResource("/resources/endbg.png");
		if (imageUrlBackground != null) {
			backgroundImg = new ImageIcon(imageUrlBackground).getImage();
		} else {
			System.err.println("Could not find image file: /resources/startbg.png");
		}
	}
	// hình ảnh nút bấm

	private JButton createButton(String imagePath, int width, int height) {
		JButton button = new JButton();
		try {
			// Lấy URL của hình ảnh từ resource của ứng dụng
			URL imageUrl = getClass().getResource(imagePath);
			if (imageUrl != null) {
				// Tạo ImageIcon từ URL và thay đổi kích thước ảnh
				ImageIcon icon = new ImageIcon(imageUrl);
				Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(scaledImage)); // Thiết lập ảnh vào nút
			} else {
				System.err.println("Không tìm thấy file hình ảnh: " + imagePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Thiết lập kích thước cho nút
		button.setPreferredSize(new Dimension(width, height));
		button.setMaximumSize(new Dimension(width, height));
		button.setMinimumSize(new Dimension(width, height));
		return button;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImg != null) {
			g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// hiện điểm của người chơi
	public void printCurrentScore() {
		score_Label_2.setText("" + gameModel.getScore());
	}

	// hiện điểm của top 3
	public void printTop3Score() {
		int size = gameModel.getScoreManagement().getSortedScores().size();
		JLabel[] labelsName = { label_name1, label_name2, label_name3 };
		JLabel[] labelsScore = { label_score1, label_score2, label_score3 };

		for (int i = 0; i < size && i < 3; i++) {
			labelsName[i].setText(gameModel.getScoreManagement().getKeyByIndex(i));
			labelsScore[i].setText("" + gameModel.getScoreManagement().getValueByIndex(i));
		}
	}

	@Override
	public void updateState(String newState) {
	    if ("StartScreen".equalsIgnoreCase(newState)) {
	        gameModel.setCurrentState(newState);
	        StartScreen startScreen = new StartScreen(mainFrame, gameModel, gameControl);
	        mainFrame.setContentPane(startScreen);
	        mainFrame.revalidate();
	        startScreen.requestFocusInWindow();
	    }
	}

}
