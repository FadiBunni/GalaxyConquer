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
			titleFont = new Font ("Centrury Gothic", Font.PLAIN, 40);
			font = new Font("Arial", Font.PLAIN, 40);
			text = "Galaxy Conquer";
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		bg.update();
		
		// check keys
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		FontMetrics fm = g.getFontMetrics();
		g.drawString(text, (GamePanel.WIDTH - fm.stringWidth(text)) / 2, 100);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i< options.length; i++){
			if(i == currentChoice){
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.RED);
			}   
			g.drawString(options[i], (GamePanel.WIDTH - fm.stringWidth(options[i])) / 2, 400+i*50);
		}
	}
	
	
	// TODO - Make a mouseHovered and a intersect square.
	
	private void select() {
		//start
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.INGAMESTATE);			
		}
		if(currentChoice == 1) {
			// quit
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
