import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame{

	public Main() {
		
		initGUI();
	}
	
	private void initGUI() {
		
        add(new Menu());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
        EventQueue.invokeLater(() -> {
            JFrame game = new Main();
            game.setVisible(true);
        });
	}

}
