import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

	//Board Info:
	private final int screenHeight = 500;
	private final int screenWidth = 500;
	private final int panelSize = 10;
	private final int totalPanels = 2500;
	
	//Snake Info:
	private int snakeSize = 7; //Initial Snake Size (Maybe increase...or make customizable in a new panel)
	private final int snakeX[] = new int[totalPanels];
	private final int snakeY[] = new int[totalPanels];

	//Food Info:
	private int numFood = 2; //Number of food available. Note: Maybe add this later. Use for loop to create them! 
	private final int foodX[] = new int[numFood];
	private final int foodY[] = new int[numFood];
	private Random rand;
	
	//Movement Info:
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	
	//General Game Info:
	boolean gameInProgress = false; //Start with this value as off/false.
	private Timer gameTimer;
	private boolean edgeKills = false; //This is the option to have bounds/edges kill the snake
	
	public Screen() {
		
		initScreen();
	}

	//Initialize the game screen
	private void initScreen() {
		
        addKeyListener(new MyKeyListener1());
        setBackground(Color.blue); //Maybe later let the user choose this
        setFocusable(true);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        initGame();
		
	}
	
	//Initialize the actual game
	private void initGame() {
		
		//Build snake's starting body
		for (int i = 0; i < snakeSize; i++) {
			snakeX[i] = i * 10 + 250; //Note: setting the 250 to 500 pushes snake off
			snakeY[i] = 250; //Note: Setting this to 500 pushes snake off
		}
		
		//Add food to screen
		//When initializing game, we need to add food randomly based on numFood.
		for (int i = 0; i < numFood; i++) {
			changeFood(i);
		}
		//Start the game with the snake going up (change later maybe)
		up = true;
		gameInProgress = true;
		
		//Begin the timer so that movement and game is set in motion!
		gameTimer = new Timer(100, this);
		gameTimer.start();
		
	}
	
	private boolean checkNewFoodPanel(int i, int x, int y) {
		//First, check if the new food coordinates interfere with the snake!
		for (int j = 0; j < snakeSize; j++) {
			if (x == snakeX[j] && y == snakeY[j]) {
				return false;
			}
		}
		//Second, check if the new food coordinates interfere with the other food!
		for (int q = 0; q < numFood; q++) {
			if (q != i && x == foodX[q] && y == foodY[q]) {
				return false;
			}
		}
		
		return true;
	}
	
	private void changeFood(int j) {
		//Use tmp variables instead of actually changing real coordinates for food piece
		int tmp1 = (int)(Math.random() * 50) * 10;
		int tmp2 = (int)(Math.random() * 50) * 10;
		while(!checkNewFoodPanel(j, tmp1, tmp2)) {
			tmp1 = (int)(Math.random() * 50) * 10;
			tmp2 = (int)(Math.random() * 50) * 10;
		}
		//Once these tmp variables have been verified, then switch the food coordinates!
		foodX[j] = tmp1;
		foodY[j] = tmp2;
	}
	
	private void checkFoodHit() {
		for (int i = 0; i < numFood; i++) {
			if (snakeX[0] == foodX[i] && snakeY[0] == foodY[i]) {
				changeFood(i);
				snakeSize++;
				i = numFood; //This stops the loop! It is fine since food cannot be on the same panel!
			}
		}
	}
	
	private void checkSnakeHit() {
		for (int j = 1; j < snakeSize; j++) {
			if (snakeX[0] == snakeX[j] && snakeY[0] == snakeY[j]) {
				gameInProgress = false;
			}
		}
		//The following code ends the game if the snake hits the screen edges/bounds: Only used when edgeKills is set to true!
		if (edgeKills) {
			if (snakeX[0] >= 500) {
				gameInProgress = false;
			} else if (snakeX[0] < 0) {
				gameInProgress = false;
			} else if (snakeY[0] >= 500) {
				gameInProgress = false;
			} else if (snakeY[0] < 0) {
				gameInProgress = false;
			} 
		}
	}
	
	private void move() {
		
		for (int i = snakeSize; i > 0; i--) {
			snakeX[i] = snakeX[i - 1];
			snakeY[i] = snakeY[i - 1];
		}
		
		if (up) {
			snakeY[0] -= 10;
		} else if (down) {
			snakeY[0] += 10;
		} else if (left) {
			snakeX[0] -= 10;
		} else if (right) {
			snakeX[0] += 10;
		}
		
		//The following code lets the snake go through the bounds of one side and come out the other, if edgeKills is set to false!
		if (!edgeKills) {
			if (snakeY[0] >= 500) {
				snakeY[0] = 0;
			} else if (snakeY[0] < 0) {
				snakeY[0] = 490;
			} else if (snakeX[0] >= 500) {
				snakeX[0] = 0;
			} else if (snakeX[0] < 0) {
				snakeX[0] = 490;
			}
		}
		
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (gameInProgress) {

        	//draw the snake
        	
        	//Set the colour for the snake. (Maybe let the user change this!)
			g.setColor(Color.RED);
        	for (int i = 0; i < snakeSize; i++) {
    			g.drawRect(snakeX[i], snakeY[i], panelSize, panelSize);
    			g.fillRect(snakeX[i], snakeY[i], panelSize, panelSize);
    		}
        	
        	//draw the food
        	
        	//Set food colour (Maybe let user change this as well!)
        	g.setColor(Color.GREEN);
        	for (int j = 0; j < numFood; j++) {
    			g.drawRect(foodX[j], foodY[j], panelSize, panelSize);
    			g.fillRect(foodX[j], foodY[j], panelSize, panelSize);
        	}
        	
            Toolkit.getDefaultToolkit().sync();

        } else {
        	//Game Over scenario!
        	
        }        
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameInProgress) {

            checkFoodHit();
            checkSnakeHit();
            move();
        }

        repaint();
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
