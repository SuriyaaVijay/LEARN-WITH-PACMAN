
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class Intro {
    public static void main(String[] args) throws IOException, FontFormatException {
        ShowIntro w = new ShowIntro();
    }
}


class ShowIntro extends JFrame{

    Font Lot;
    static Font CrackMan;
    Font video;
    public ShowIntro() throws IOException, FontFormatException {

        //Pacman Font 1
        Lot = Font.createFont(Font.TRUETYPE_FONT , new File("Lot.otf"));
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("Lot.otf")));


        //Pacman Font2
        CrackMan = Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF"));
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF")));
        video = Font.createFont(Font.TRUETYPE_FONT , new File("videobeast80s.TTF"));
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("videobeast80s.TTF")));

        //Title
        JLabel q = new JLabel("Learn with PAC-MAN");
        q.setFont(new Font("CrackMan", Font.PLAIN, 35));
        q.setBackground(Color.black);
        q.setForeground(Color.yellow);
        q.setBounds(410 , 20 , 500 , 100);

        //LeaderBoard
        JButton leaderboard = new JButton("LeaderBoard");
        leaderboard.setFont(new Font("Lot", Font.PLAIN, 20));
        leaderboard.setBackground(Color.black);
        leaderboard.setForeground(Color.yellow);
        leaderboard.setBounds(470 , 360 , 300 , 90);

        //New Game
        JButton newgame = new JButton("New Game");
        leaderboard.setFont(new Font("Lot", Font.PLAIN, 20));
        newgame.setFont(new Font("Lot" , Font.PLAIN, 20));
        newgame.setBackground(Color.black);
        newgame.setForeground(Color.yellow);
        newgame.setBounds(470 , 250 , 300 , 90);

        //Exit
        JButton ext = new JButton("Exit");
        ext.setFont(new Font("Lot" , Font.PLAIN, 20));
        ext.setBackground(Color.black);
        ext.setForeground(Color.yellow);
        ext.setBounds(470 , 470 , 300 , 50);

        //About
        JButton about = new JButton("About");
        about.setFont(new Font("Lot" , Font.PLAIN, 15));
        about.setBackground(Color.black);
        about.setForeground(Color.yellow);
        about.setBounds(1000 , 500 , 200 , 50);



        BufferedImage myPicture = ImageIO.read(new File("images/maze.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(5, 220 , 300 , 300);

        BufferedImage burger = ImageIO.read(new File("images/burg.png"));
        JLabel junkfood = new JLabel(new ImageIcon(burger));
        junkfood.setBounds(895 , 250 , 300 , 300);

        BufferedImage fruits = ImageIO.read(new File("images/healthy.jpg"));
        JLabel healthy = new JLabel(new ImageIcon(fruits));
        healthy.setBounds(450, 20 , 300 , 300);

        BufferedImage chery = ImageIO.read(new File("images/cherry.png"));
        JLabel cherry = new JLabel(new ImageIcon(chery));
        cherry.setBounds(50 , 100 , 200 , 200);

        BufferedImage appl = ImageIO.read(new File("images/apple.png"));
        JLabel apple = new JLabel(new ImageIcon(appl));
        apple.setBounds(930 , 100 , 200 , 200);

        setVisible(true);
        setLayout(null);
        add(picLabel);
        add(junkfood);
        add(healthy);
        add(cherry);
        add(apple);
        add(q);
        add(leaderboard);
        add(newgame);
        add(ext);
        add(about);
        setSize(1280,600);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leaderboard.addActionListener(e -> {
            Leaderboard t = null;
            try {
                t = new Leaderboard();
            } catch (Exception r) {

            }
        });

        newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        new ShowLevels();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                dispose();
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        new about();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                dispose();
            }
        });

        ext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}