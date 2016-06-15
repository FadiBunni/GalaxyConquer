package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Handler.Keys;

import Backgrounds.Background;
import Main.GamePanel;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	
	private String[] options = {
			"Start",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private String text;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}
		
	public void init() {
		try{
			bg = new Background("/Backgrounds/hubble1.jpg", 1);
			
			titleColor = new Color(255,255,255);
			titleFont = new Font (
					"Centrury Gothic",
					Font.PLAIN,
					40					
					);
			font = new Font("Arial", Font.PLAIN, 40);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		bg.update();
		
		// check keys
		handleInput();
	}
	
	public void draw(Graphics2D g2d) {
		// draw bg
		bg.draw(g2d);
		
		//draw title
		text = "Galaxy Conquer";
		g2d.setColor(titleColor);
		g2d.setFont(titleFont);
		
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((GamePanel.WIDTH - fm.stringWidth(text)) / 2);
        int y = GamePanel.HEIGHT - (GamePanel.HEIGHT / 2);
        
		g2d.drawString(text, x, y-80);
		
		//draw menu options
		g2d.setFont(font);
		for(int i = 0; i< options.length; i++){
			if(i == currentChoice){
				g2d.setColor(Color.BLACK);
			}else {
				g2d.setColor(Color.RED);
			}   
			x = ((GamePanel.WIDTH - fm.stringWidth(options[i])) / 2);
			y = (int) (GamePanel.HEIGHT - (GamePanel.HEIGHT / (double)2.2));
			g2d.drawString(options[i], x, i*35+y);
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.INGAMESTATE);
			
			//start
		}
		if(currentChoice == 1) {
			// help
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void handleInput() {
		if(Handler.Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				currentChoice++;
			}
		}
	}

}
