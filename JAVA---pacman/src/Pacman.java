import java.awt.*;
import javax.swing.JFrame;
public class Pacman extends JFrame {
    public Pacman(int level) {
        initUI(level);
    }
    private void initUI(int level) {
        if(level == 1) {
            add(new Board());
        }
        if(level == 2){
            add(new Board2());
        }
        if(level == 3){
            add(new Board3());
        }
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize( 760, 435);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}