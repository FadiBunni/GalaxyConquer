package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Backgrounds.Background;
import Main.GamePanel;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private String text;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
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
	}
	
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);
		
		//draw title
		text = "Galaxy Conquer";
		g.setColor(titleColor);
		g.setFont(titleFont);
		
		FontMetrics fm = g.getFontMetrics();
        int x = ((GamePanel.WIDTH - fm.stringWidth(text)) / 2);
        int y = GamePanel.HEIGHT - (GamePanel.HEIGHT / 2);
        
		g.drawString(text, x, y-80);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i< options.length; i++){
			if(i == currentChoice){
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.RED);
			}   
			x = ((GamePanel.WIDTH - fm.stringWidth(options[i])) / 2);
			y = (int) (GamePanel.HEIGHT - (GamePanel.HEIGHT / (double)2.2));
			g.drawString(options[i], x, i*35+y);
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
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length-1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k) {}

}
