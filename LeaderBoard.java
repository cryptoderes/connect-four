import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class LeaderBoard {
    private FileWriter w;
    private String[] text = new String[4];
    private String scores = "Leader Board \n" +
                    "1. " + text[0] + 
                    "\n 2. " + text[1] +
                    "\n 3. " + text[2];
    private TreeMap<Integer, String> scoreHold = new TreeMap<Integer, String>(); 
    
    public LeaderBoard() {
        try {
            reader();
        } catch (IOException e) {}
    }
    
    public String getScores() {
        return scores;
    }
    
    public void reader() throws FileNotFoundException {
        File leaderboard = new File("files/leaderBoard.txt");
        
        Scanner scan = new Scanner(leaderboard);
        
        while (scan.hasNext()) {
            String cur = scan.nextLine();
            scoreHold.put(getMoves(cur), getName(cur));
        }
        
        scan.close();
    }
    
    public String readLine() throws FileNotFoundException {
        File file = new File("files/leaderBoard.txt");
        Scanner scan = new Scanner(file);
        String output = scan.nextLine();
        scan.close();
        return output;
        
    }
    
    public String[] getText() {
        return text;
    }

    public int getMoves(String entry) {
        String[] set = entry.split(" ");
        int moves = Integer.parseInt(set[1]);
        return moves;
    }
    
    public String getName(String entry) {
        String[] set = entry.split(" ");
        return set[0];
    }
    
    public void writer(String s) throws IOException {
        
        
        w = new FileWriter("files/leaderBoard.txt", false);
        String curName = getName(s);
        int curMoves = getMoves(s);
        
        scoreHold.put(curMoves, curName);
        
        for (int i = 0; i < 4; i++) {
            String entry = String.valueOf(scoreHold.pollFirstEntry());
            String[] entrys = entry.split("=");
            w.write(entrys[1] + " " + entrys[0] + "\n");
            text[i] = entrys[1] + " " + entrys[0];
        }
        
        w.close();
    }
  
}
