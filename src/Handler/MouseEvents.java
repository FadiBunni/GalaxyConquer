package Handler;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class MouseEvents {
	
	public static final int NUM_BUTTONS = 3;
	
	public static boolean buttons[] = new boolean[NUM_BUTTONS];
	public static boolean prevButtons[] = new boolean[NUM_BUTTONS];
	public static int mouseX;
	public static int mouseY;
	
	public static int LEFTCLICK = 0;
	public static int MIDDLECLICK = 1;
	public static int RIGHTCLICK = 2;
	
	
	public static void buttonSet(int i, boolean b) {
		if (i == MouseEvent.BUTTON1)
			buttons[LEFTCLICK] = b;
		else if (i == MouseEvent.BUTTON2)
			buttons[LEFTCLICK] = b;
		else if (i == MouseEvent.BUTTON3)
			buttons[RIGHTCLICK] = b;
	}
	
	public static void update() {
		for (int i = 0; i < NUM_BUTTONS; i++) {
			prevButtons[i] = buttons[i];
		}
	}
	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public static boolean isPressed(int i) {
		return buttons[i];
	}
	
	public static boolean mouseHovered(Ellipse2D.Double e) {
		return e.contains(mouseX,mouseY);
	}
}
