import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel{
    private Connect4 c4;
    private JLabel status; 
    
    public static final int BOARD_WIDTH = 350;
    public static final int BOARD_HEIGHT = 300;
    
    private boolean gameOver = false;
    
    private int moves = 0;
    private String winner;
    private String LBDisplay = " "; 
    
    LeaderBoard lb = new LeaderBoard();
    
    public Board(JLabel curStat) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        setFocusable(true);
        
        c4 = new Connect4();
        status = curStat;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                c4.putPiece(p.x / 50);
                updateStat();
                repaint();
            }
        });
    }
    
    public int numMoves() {
        return moves;
    }
    
    public String getWinner() {
        return winner;
    }
    
    private void updateStat() {
        int cur = c4.getPlayer();
        if (cur == 1) {
            status.setText("Player 2's turn");  
            moves += 1; 
        } else {
            status.setText("Player 1's turn");
            moves += 1; 
        }
        
        if (c4.checkwin()) {
            status.setText("Player " + c4.getWinner() + " wins!");
            gameOver = true;
            moves = c4.getMoves();
            winner = JOptionPane.showInputDialog("What's the winner's name?");
            try {
                lb.reader();
                String input = winner + " " + moves;
                lb.writer(input);
            } catch (IOException e) {}
            LBDisplay = lb.getScores();
        }
    }
    
    public String getLBDisplay() {
        return LBDisplay;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public void reset() {
        c4.resetGame();
        status.setText("Player 1's turn");
        repaint();
    }
    
    public void undo() {
        c4.undo();
        
        int cur = c4.getPlayer();
        if (cur == 1) {
            status.setText("Player 2's turn");    
        } else {
            status.setText("Player 1's turn");
        }
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
            g.drawLine(50, 0, 50, 300);
            g.drawLine(100, 0, 100, 300);
            g.drawLine(150, 0, 150, 300);
            g.drawLine(200, 0, 200, 300);
            g.drawLine(250, 0, 250, 300);
            g.drawLine(300, 0, 300, 300);
            g.drawLine(0, 0, 300, 0);
            g.drawLine(0, 50, 300, 50);
            g.drawLine(0, 100, 300, 100);
            g.drawLine(0, 150, 300, 150);
            g.drawLine(0, 200, 300, 200);
            g.drawLine(0, 250, 300, 250);
            
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    int piece = c4.getPiece(j, i);
                    
                    if (piece == 1) {
                        g.setColor(Color.CYAN);
                        g.fillOval(15 + 50*j, 15 + 50*i, 20, 20);
                    } else if (piece == 2){
                        g.setColor(Color.pink);
                        g.fillOval(15 + 50*j, 15 + 50*i, 20, 20);
                    }
                }
            }
         
    
    }
    
}

