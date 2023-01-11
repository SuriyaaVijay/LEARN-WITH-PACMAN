
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;


class about extends JFrame{

    Font Lot;
    static Font CrackMan;
    Font video;
    public about() throws IOException, FontFormatException {

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


        JButton ext = new JButton("Back");
        ext.setFont(new Font("Lot" , Font.PLAIN, 20));
        ext.setBackground(Color.black);
        ext.setForeground(Color.yellow);
        ext.setBounds(470 , 470 , 300 , 50);

        JLabel names = new JLabel("This project was created by : ");
        names.setFont(new Font("Lot", Font.PLAIN, 25));
        names.setBackground(Color.black);
        names.setForeground(Color.yellow);
        names.setBounds(410 , 220 , 600 , 100);

        JLabel name1 = new JLabel("Kartikeyan TR");
        name1.setFont(new Font("CrackMan", Font.PLAIN, 25));
        name1.setBackground(Color.black);
        name1.setForeground(Color.yellow);
        name1.setBounds(520 , 270 , 500 , 100);

        JLabel name2 = new JLabel("Suriyaa V");
        name2.setFont(new Font("CrackMan", Font.PLAIN, 25));
        name2.setBackground(Color.black);
        name2.setForeground(Color.yellow);
        name2.setBounds(560 , 320 , 500 , 100);

        JLabel name3 = new JLabel("Guru Raman C");
        name3.setFont(new Font("CrackMan", Font.PLAIN, 25));
        name3.setBackground(Color.black);
        name3.setForeground(Color.yellow);
        name3.setBounds(530 , 370 , 500 , 100);

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
        add(ext);
        add(names);
        add(name1);
        add(name2);
        add(name3);
        setSize(1280,600);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ShowIntro();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
    }

}