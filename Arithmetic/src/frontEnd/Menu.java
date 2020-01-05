package frontEnd;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JMenuBar{
	
	private Window myFrame;
	
	public Menu(Window theFrame) {
		super();
		createMenu();
		myFrame = theFrame;
	}
	
	private void createMenu(){
		JMenu fileMenu  = new JMenu("File");
		JMenuItem quitBtn = new JMenuItem("Quit");
		quitBtn.addActionListener(new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {	        
		        myFrame.dispose();
		    }
		});
		fileMenu.add(quitBtn);
		add(fileMenu);
		
		JMenu difficultyMenu = new JMenu("Difficulty");
		JSlider difficultySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		difficultySlider.setMajorTickSpacing(5);
		difficultySlider.setMinorTickSpacing(1);
		difficultySlider.setPaintTicks(true);
		difficultySlider.setPaintLabels(true);
		difficultySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	//System.out.println(difficultySlider.getValue());
                myFrame.setDifficulty(difficultySlider.getValue());
            }
        });
		difficultyMenu.add(difficultySlider);
		add(difficultyMenu);
		
		JMenu speedMenu = new JMenu("Speed");
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	myFrame.setSpeed(speedSlider.getValue());
            }
        });
		speedMenu.add(speedSlider);
		add(speedMenu);
		
		JMenu modeMenu = new JMenu("Game Mode");
		JLabel label = new JLabel("Coming Soon");
		modeMenu.add(label);
		add(modeMenu);
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem infoBtn = new JMenuItem("Info");
		infoBtn.addActionListener(new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        
		    	JFrame infoFrame = new JFrame();
		    	infoFrame.setLayout(new GridBagLayout());
		    	JTextArea infoLabel = new JTextArea(""
		    			+ "Welcome to the Arithmetic Game. \n"
		    			+ "Are you ready to practice and \n"
		    			+ "exercise your brain? Add all the \n "
		    			+ "numbers together but try to keep up. \n"
		    			+ "You can change the speed and difficulty. \n"
		    			+ "Made by Max Malyshev all rights reserved");
		    	infoFrame.add(infoLabel);
		    	infoFrame.pack();
		    	infoFrame.setResizable(false);
		    	infoFrame.setLocationRelativeTo(null);
				infoFrame.setVisible(true);
		    }
		});
		helpMenu.add(infoBtn);
		add(helpMenu);
		
	}
	
}
