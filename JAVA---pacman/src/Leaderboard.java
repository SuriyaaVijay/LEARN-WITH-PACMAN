

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Leaderboard {
    JFrame frame = new JFrame();

    Leaderboard() throws ClassNotFoundException, SQLException, IOException, FontFormatException {
        Font smallFont = new Font("Arial", 1, 15);
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "system";
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
        Statement statement = con.createStatement();
        System.out.println("retrieve");
        String query = "SELECT * FROM leaderboard ORDER BY Score DESC LIMIT 10";
        System.out.println("retrieve");
        int i = 0;
        String data[][] = new String[10][3];
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String score = Integer.toString(resultSet.getInt("SCORE"));
            String date = resultSet.getString("DATA");
            data[i] = new String[]{i+1+"" , score, date};
            i++;
        }

        Font CrackMan = Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF"));
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT , new File("CrackMan.TTF")));
        String[] columnnames = {"S.NO" , "SCORE" , "WHEN"};
        JLabel t = new JLabel("Leaderboard");
        t.setFont(new Font("CrackMan" , Font.BOLD , 50));
        t.setForeground(Color.yellow);
        t.setBounds(230,780,500,100);
        t.setBackground(Color.black);
        JTable j = new JTable(data , columnnames);
        j.setBounds(200,200,0,0);
        j.getColumnModel().getColumn(0).setPreferredWidth(0);
        j.getColumnModel().getColumn(1).setPreferredWidth(100);
        j.getColumnModel().getColumn(2).setPreferredWidth(100);
        for(int ss = 0 ; ss < 10 ; ss++){
            j.setRowHeight(ss,30);
        }
        j.setForeground(Color.yellow);
        j.setFont(smallFont);
        j.setGridColor(Color.blue);
        j.setBackground(Color.black);
        j.getTableHeader().setBackground(Color.black);
        j.getTableHeader().setFont(smallFont);
        j.getTableHeader().setForeground(Color.yellow);

        JScrollPane sp = new JScrollPane(j);
        frame.setSize(500,370);
        frame.add(t);
        frame.add(sp);
        frame.setVisible(true);
        frame.setBackground(Color.black);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setTitle("Leader Board");
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
    }
}

