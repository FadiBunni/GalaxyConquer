package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import Handler.Keys;
import Handler.MouseEvents;
import GameState.GameStateManager;
import GameState.InGameState;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	
		// dimensions
		public static final int WIDTH = 960;
		public static final int HEIGHT = 700;

		// game thread

		private Thread thread;
		private boolean running;
		private int FPS = 60;
		private long targetTime = 1000 / FPS;

		// image
		private BufferedImage image;
		private Graphics2D g;

		// game state manager
		private GameStateManager gsm;
		
		public GamePanel() {
			super();
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			setFocusable(true);
			requestFocus();
		}
		
		public void addNotify() {
			super.addNotify();
			if (thread == null) {
				thread = new Thread(this);
				addKeyListener(this);
				addMouseListener(this);
				addMouseMotionListener(this);
				thread.start();
			}
		}

		private void init() {
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
			
			/*g.setRenderingHint(
			RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON
			);*/
			running = true;
			
			gsm = new GameStateManager();

		}

		public void run() {
			init();

			long start;
			long elapsed;
			long wait;

			// game loop
			while (running) {
				start = System.nanoTime();

				update();
				draw();
				drawToScreen();

				elapsed = System.nanoTime() - start;

				wait = targetTime - elapsed / 1000000;
				if(wait < 0 )
					wait = 1;

				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private void update() {
			gsm.update();
			Keys.update();
		}
		private void draw() {
			gsm.draw(g);
		}

		private void drawToScreen() {
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
			g2.dispose();
		}

		public void keyTyped(KeyEvent key) {}
		
		@Override
		public void keyPressed(KeyEvent key) {
			Keys.keySet(key.getKeyCode(), true);
		}

		public void keyReleased(KeyEvent key) {
			Keys.keySet(key.getKeyCode(), false);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			MouseEvents.mouseX = e.getX();
			MouseEvents.mouseY = e.getY();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			MouseEvents.mouseX = e.getX();
			MouseEvents.mouseY = e.getY();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			MouseEvents.buttonSet(e.getButton(), true);
			
		}
		public void mouseReleased(MouseEvent e) {
			MouseEvents.buttonSet(e.getButton(), false);
			InGameState.setHasPressed(false);
		}
		
		// UNUSED
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
}
