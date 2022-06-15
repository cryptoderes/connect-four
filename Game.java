import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
    public void run() {
        JFrame frame = new JFrame("Connect 4");
        frame.setLocation(300, 350);
        
        //status panel for displaying turns
        JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        JLabel status = new JLabel("Welcome! Player 1's turn.");
        status_panel.add(status);
        
        //game board
        Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);
        
        //control panel for the other buttons
        JPanel controls = new JPanel();
        frame.add(controls, BorderLayout.NORTH);
        
        //reset button
        JButton reset = new JButton("Reset");
        reset.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                board.reset();
            }
        });
        controls.add(reset);
        
        //undo button
        JButton undo = new JButton("Undo");
        undo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                board.undo();
            }
        });
        controls.add(undo);
        
        JButton LB = new JButton("Show Leader Board");
        LB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(LB, board.getLBDisplay());
            }
        });
        status_panel.add(LB);
        
        //instructions button: opens a pop up window
        JButton instructions = new JButton("Click for instructions");
        controls.add(instructions);
        instructions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(instructions, "The goal of Connect 4 is to place "
                        + "four of your pieces in row diagonally, horizontally, or vertically. Simply click "
                        + "on the board to choose a row to place your piece. This is a "
                        + "turn-based game. If you would like to undo the most recent move, you can "
                        + "click the 'undo' button.");
            }
        });
       
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        board.reset();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
