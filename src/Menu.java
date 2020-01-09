import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Menu extends JPanel {

	private final int Width = 500;
	private final int Height = 500;
	
	public Menu() {
		initMenu();
		
	}
	
	private void initMenu() {
        setBackground(Color.BLACK); //Maybe later let the user choose this
        setFocusable(true);
        setPreferredSize(new Dimension(Width, Height));
	}
	
	
	
}
