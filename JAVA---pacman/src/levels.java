import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class levels {
    public static void main(String[] args) throws IOException, FontFormatException {
        ShowLevels w = new ShowLevels();
    }
}


class ShowLevels extends JFrame{

    Font Lot;
    static Font CrackMan;
    Font video;
    public ShowLevels() throws IOException, FontFormatException {
        Lot = Font.createFont(Font.TRUETYPE_FONT , new File("Lot.otf"));
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("Lot.otf")));
        CrackMan = Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF"));
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF")));
        video = Font.createFont(Font.TRUETYPE_FONT , new File("videobeast80s.TTF"));
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("videobeast80s.TTF")));
        JLabel q = new JLabel("Select Levels");
        JButton level1 = new JButton("Level 1");
        JButton level2 = new JButton("Level 2");
        JButton level3 = new JButton("Level 3");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.black);
        level1.setFont(new Font("Lot", Font.PLAIN, 20));
        level2.setFont(new Font("Lot" , Font.PLAIN, 20));
        level1.setBackground(Color.black);
        level1.setForeground(Color.yellow);
        level2.setBackground(Color.black);
        level2.setForeground(Color.yellow);
        level3.setBackground(Color.black);
        level3.setForeground(Color.yellow);
        level3.setFont(new Font("Lot" , Font.PLAIN , 20));

        //Select Levels
        q.setFont(new Font("CrackMan", Font.PLAIN, 35));
        q.setBackground(Color.black);
        q.setForeground(Color.yellow);
        q.setBounds(410 , 20 , 500 , 100);

        level1.setBounds(470 , 250 , 300 , 50);

        level2.setBounds(470 , 350 , 300 , 50);

        level3.setBounds(470 , 450 , 300 , 50);

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


        add(picLabel);
        add(junkfood);
        add(healthy);
        add(cherry);
        add(apple);
        add(q);
        add(level1);
        add(level3);
        add(level2);
        setSize(1280,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pacman ex = new Pacman(1);
                ex.setVisible(true);
                dispose();
            }
        });

        level2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pacman ex = new Pacman(2);
                ex.setVisible(true);
                dispose();
            }
        });

        level3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pacman ex = new Pacman(3);
                ex.setVisible(true);
                dispose();
            }
        });
    }

}