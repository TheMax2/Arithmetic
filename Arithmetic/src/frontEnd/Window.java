
package frontEnd;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import backEnd.Calculator;

public class Window extends JFrame{

	private ImageIcon backgroundImage;
	private ImageIcon[] digits;
	private ImageIcon plus;
	private ImageIcon minus;
	private ImageIcon equals;
	
	private JLabel mySign;
	private JLabel myNumb;
	
	private Timer myTimer;
	
	private Calculator myCalculator;
	private JButton startBtn;
	private Container background;	
	private int myDifficulty;
	private int numbOfTurns;
	
	public Window() {
		super("Arithmetic Game");

		loadImages();
		myDifficulty = 5;
		numbOfTurns = 10;
		myCalculator = new Calculator(myDifficulty, numbOfTurns);
		startBtn = new JButton();
		myTimer = new Timer(1000, timerAction);
		myTimer.setInitialDelay(100);
		mySign = new JLabel();
		myNumb = new JLabel();
		backgroundImage = new ImageIcon();
		
	}

	private void loadImages() {
		digits = new ImageIcon[10];
		for (int i = 0; i < 10; i++) {
			digits[i] = new ImageIcon(loadImage("/imgs/"+i+".png"));
		}
		plus = new ImageIcon(loadImage("/imgs/plus.png"));
		minus = new ImageIcon(loadImage("/imgs/minus.png"));
		equals = new ImageIcon(loadImage("/imgs/eqauls.png"));
		
	}
	
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setIconImage(new ImageIcon(loadImage("/imgs/ArithmeticLogo2.png")).getImage());
		startBtn.setIcon(new ImageIcon(loadImage("/imgs/start.png")));
		startBtn.setPreferredSize(new Dimension(427,200));
		startBtn.setBorderPainted(false); 
		startBtn.setContentAreaFilled(false); 
		//startBtn.setFocusPainted(false); 
		//startBtn.setOpaque(false);
		startBtn.addActionListener(new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	myCalculator = new Calculator(myDifficulty, numbOfTurns);
		    	System.out.println("Button Pushed!");
		        myCalculator.startRound();
		        myTimer.start();
		    }
		});
		
		//ImageIcon backgroundImg = new ImageIcon(loadImage("/imgs/arithmetic-background.jpg"));
		backgroundImage = new ImageIcon(loadImage("/imgs/arithmetic-background.jpg"));
		background = new JLabel("",backgroundImage, JLabel.CENTER);
		background.setBounds(0,0,1200,700);
		background.setLayout(new GridBagLayout());
		background.add(startBtn);
		startBtn.setVisible(false);
		background.add(mySign);
		background.add(myNumb);
		add(background);
		pack();
		setJMenuBar(new Menu(this));
		setLocationRelativeTo(null);
		displayStartScreen();
		setVisible(true);
	}
	

	private void displayStartScreen() {
		startBtn.setVisible(true);
	}
	
	private void displayGameScreen() {
		backgroundImage.setImage(new ImageIcon(loadImage("/imgs/BackgroundNormal.png")).getImage());
		setContentPane(background);
		startBtn.setVisible(false);
		if (myCalculator.getSignToString() == "+") {
			mySign.setIcon(plus);
		}else {
			mySign.setIcon(minus);
		}
		if (myCalculator.getTurns() == 1) mySign.setIcon(null);
		myNumb.setIcon(digits[myCalculator.getNumb()]);


	}
	
	private void displayPromptScreen() {
		mySign.setIcon(equals);
		myNumb.setIcon(null);
		JTextField input = new JTextField(2);
		Font bigFont = new Font(Font.SANS_SERIF,  Font.BOLD, 150);
		input.setFont(bigFont);
		input.addActionListener(new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("You wrote: "+ input.getText() +". The answer was: "+ myCalculator.getAnswer()+"");
		        if(input.getText().equals(myCalculator.getAnswer()+"")) {
		        	input.setVisible(false);
		        	mySign.setIcon(null);
		        	displayCorrectScreen();
		        }else {
		        	mySign.setIcon(null);
		        	input.setVisible(false);
		        	displayIncorrectScreen();
		        }
		    }
		});
		background.add(input);
	}
	
	private void displayCorrectScreen() {
		System.out.println("Correct!");
		backgroundImage.setImage(new ImageIcon(loadImage("/imgs/BackgroundGood.png")).getImage());
		setContentPane(background);
		startBtn.setIcon(new ImageIcon(loadImage("/imgs/again.png")));
		startBtn.setVisible(true);
	}
	
	private void displayIncorrectScreen() {
		System.out.println("Incorrect!");
		backgroundImage.setImage(new ImageIcon(loadImage("/imgs/BackgroundBad.png")).getImage());
		setContentPane(background);
		startBtn.setIcon(new ImageIcon(loadImage("/imgs/again.png")));
		startBtn.setVisible(true);
	}
	
	ActionListener timerAction = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			//System.out.println("Difficulty = "+ myCalculator.getDifficulty());
			if (myCalculator.getTurns() > myCalculator.getMaxTurns()) {
				myTimer.stop();
				displayPromptScreen();
			} else {
				if (myDifficulty > 0) {
					myCalculator.makeMove();
				} else {
					myCalculator.makeEasyMove();
				}		
				displayGameScreen();
			}
		}
	};
	
	protected void setDifficulty(int theDifficulty) {
		myDifficulty = theDifficulty;
		if (theDifficulty >= 10) myDifficulty = 9;
	}
	
	protected void setSpeed(int theSpeed) {
		double delay = (5/(double)theSpeed) * 1000;
		if (theSpeed == 0) delay = 7500;
		System.out.println("delay = " + delay);
		myTimer.setDelay((int)delay);
		numbOfTurns = theSpeed + 5;
	}
	
	private BufferedImage loadImage(String url) {
		BufferedImage img;
		try {
			img = ImageIO.read(Window.class.getResource(url));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
