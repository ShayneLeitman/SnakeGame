import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel {

	//Board Info:
	private final int screenHeight = 500;
	private final int screenWidth = 500;
	private final int panelSize = 10;
	private final int totalPanels = 2500;
	
	//Snake Info:
	private int snakeSize = 2; //Initial Snake Size (Maybe increase...or make customizable in a new panel)
	private final int snakeX[] = new int[totalPanels];
	private final int snakeY[] = new int[totalPanels];

	//Food Info:
	private int numFood = 1; //Number of food available. Note: Maybe add this later. Use for loop to create them! 
	private final int foodX[] = new int[numFood];
	private final int foodY[] = new int[numFood];
	
	//Movement Info:
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	
	//General Game Info:
	boolean gameInProgress = false; //Start with this value as off/false.
	private Timer gameTimer;
	
	public Screen() {
		
		initScreen();
	}

	private void initScreen() {
		
        addKeyListener(new MyKeyListener1());
        setBackground(Color.blue); //Maybe later let the user choose this
        setFocusable(true);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        initGame();
		
	}
	
	private class MyKeyListener1 extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			
			if (k == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				up = true;
				//down = false; Redundant
			} else if (k == KeyEvent.VK_DOWN && !up) {
				left = false;
				right = false;
				//up = false; Redundant
				down = true;
			} else if (k == KeyEvent.VK_RIGHT && !left) {
				//left = false; Redundant
				right = true;
				up = false;
				down = false;
			} else if (k == KeyEvent.VK_LEFT && !right) {
				left = true;
				//right = false; Redundant
				up = false;
				down = false;
			}	
		}
	}
	
	private class MyKeyListener2 implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			
			if (k == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				up = true;
				//down = false; Redundant
			} else if (k == KeyEvent.VK_DOWN && !up) {
				left = false;
				right = false;
				//up = false; Redundant
				down = true;
			} else if (k == KeyEvent.VK_RIGHT && !left) {
				//left = false; Redundant
				right = true;
				up = false;
				down = false;
			} else if (k == KeyEvent.VK_LEFT && !right) {
				left = true;
				//right = false; Redundant
				up = false;
				down = false;
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			//Do not need, leaving blank
		}


		@Override
		public void keyReleased(KeyEvent e) {
			//Do not need, leaving blank
		}
		
	}

}
