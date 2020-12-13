package maze;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Maze extends JPanel {
	//enum that sets all of the directions and their attributes.
	enum Dir {
		N(1, 0 ,-1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		final int bit; 
		final int dx;
		final int dy;
		Dir opposite;
		
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
		
		Dir (int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
	
	int cols;
	int rows;
	int cellSize = 20;
	int margin = 20;
	int [][] maze;
	LinkedList<Integer> solved;
	
	//the start of the whole program 
	public Maze (int size) {
		setPreferredSize(new Dimension(750, 750));
		cols = size;
		rows = size;
		maze = new int [rows][cols];
		solved = new LinkedList<>();
		makeMaze(0, 0);
		
		//mouse listener to start the solver animation
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Thread(() -> {
					long startTime = System.currentTimeMillis();
					solve(0);
					long endTime = System.currentTimeMillis();
					System.out.println("That took " + (endTime - startTime) + " milliseconds");
				}).start();
			}
		});
	}
	
	//Method to make the maze through recursion that keeps making for loops until it finishes that last one and then works in reverse finishing form the end up
	public void makeMaze(int r, int c) {
		Dir [] dirs = Dir.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (Dir dir : dirs) {
			int nc = c + dir.dx;
			int nr = r + dir.dy;
			if (inbounds(nr, nc) && maze[nr][nc] == 0) {
				maze[r][c] += dir.bit;
				maze[nr][nc] += dir.opposite.bit;
				makeMaze(nr,nc);
			}
		}
	}
	
	//Method to check if the new numbers used in the make maze method are still inbounds and if it should continue making the maze
	public boolean inbounds(int r, int c) {
		return c >= 0 && c < cols && r >= 0 && r < rows;
	}
	
	//Method to solve the maze and put it into an Linked List to have it drawn out in the end
	public boolean solve(int pos) {
		if (pos == cols * rows -1) {
			return true;
		}
		int c = pos % cols;
		int r = pos / cols;
		
		for (Dir dir : Dir.values()) {
			int nc = c + dir.dx;
			int nr = r + dir.dy;
			if (inbounds (nr, nc) && (maze[r][c] & dir.bit) != 0 && (maze[nr][nc] & 16) == 0) {
				int newPos = nr * cols + nc;
				solved.add(newPos);
				maze[nr][nc] |= 16;
				animate();
				
				if (solve(newPos)) {
					return true;
				}
				animate();
				
				solved.removeLast();
				maze[nr][nc] &= ~16;
			}
		}
		return false;		
	}
	
	//Method to make the jframe and draw the maze and solution to it.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setStroke(new BasicStroke(5));
		gg.setColor(Color.black);
		
		//make the maze
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int x = margin + c * cellSize;
				int y = margin + r * cellSize;
				
				//North
				if ((maze[r][c] & 1) == 0) {
					gg.drawLine(x, y, x + cellSize, y);
				}
				//South
				if ((maze[r][c] & 2) == 0) {
					gg.drawLine(x, y + cellSize, x + cellSize, y + cellSize);	
				}
				//East
				if ((maze[r][c] & 4) == 0) {
					gg.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
				}
				//West
				if ((maze[r][c] & 8) == 0) {
					gg.drawLine(x, y, x, y + cellSize);
				}
			}
		}
		
		//draw solver animation
		int offset = margin + cellSize / 2;
		Path2D path = new Path2D.Float();
		path.moveTo(offset, offset);
		
		for (int pos : solved) {
			int x = pos % cols * cellSize + offset;
			int y = pos / cols * cellSize + offset;
			path.lineTo(x, y);
		}
		
		//path
		gg.setColor(Color.red);
		gg.draw(path);
		//starting
		gg.setColor(Color.blue);
		gg.fillOval(offset - 5, offset - 5, 10, 10);
		//ending
		gg.setColor(Color.green);
		int x = offset + (cols - 1) * cellSize;
        int y = offset + (rows - 1) * cellSize;
		gg.fillOval(x - 5, y - 5, 10, 10);
	}
	
	//Method to animate the solver
	public void animate() {
		try {
			Thread.sleep(50L);
		} catch (InterruptedException ignored) {
		}
		repaint();
	}
	
	//main method that makes the jframe, starts the program, and lets you pick the size of the maze
	public static void main (String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the size of maze");
		int size = Integer.parseInt(input.nextLine());
		SwingUtilities.invokeLater(() -> {
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("Maze");
			f.setResizable(true);
			f.add(new Maze(size), BorderLayout.CENTER);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		});
	}	
}