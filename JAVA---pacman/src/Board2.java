import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.*;
public class Board2 extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private final Font factfont = new Font("Arial", Font.BOLD, 18);

    private final Font qfont = new Font("Arial", Font.BOLD, 25);
    private boolean inGame = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;

    private int N_GHOSTS = 12;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost , cherry ;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private final short levelData[] = {
            19, 26, 26, 26, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22,
            21, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21,
            17, 18, 26, 18, 18, 18, 18, 26, 18, 16, 18, 18, 26, 18, 20,
            17, 20, 0, 17, 16, 16, 20, 0, 17, 16, 16, 20, 0, 17, 20,
            17, 20, 0, 17, 16, 16, 20, 0, 17, 16, 16, 20, 0, 17, 20,
            17, 20, 0, 17, 24, 24, 28, 0, 25, 24, 24, 20, 0, 17, 20,
            17, 16, 26, 20, 0, 0, 0, 0, 0, 0, 0, 17, 26, 16, 20,
            17, 20, 0, 17, 18, 18, 22, 0, 19, 18, 18, 20, 0, 17, 20,
            17, 20, 0, 17, 16, 16, 20, 0, 17, 16, 16, 20, 0, 17, 20,
            17, 20, 0, 17, 16, 16, 20, 0, 17, 16, 16, 20, 0, 17, 20,
            17, 24, 26, 24, 24, 24, 24, 18, 24, 24, 24, 24, 26, 24, 20,
            21, 0, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 0, 21,
            17, 18, 18, 26, 18, 18, 18, 24, 18, 18, 18, 26, 18, 18, 20,
            17, 16, 20, 0, 17, 16, 20, 0, 17, 16, 20, 0, 17, 16, 20,
            25, 24, 24, 26, 24, 24, 24, 26, 24, 24, 24, 26, 24, 24, 28
    };

    JButton p = new JButton("LeaderBoard");


    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    public Board2() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }


    private void loadImages() {
        down = new ImageIcon("images/down.gif").getImage();
        up = new ImageIcon("images/up.gif").getImage();
        left = new ImageIcon("images/left.gif").getImage();
        right = new ImageIcon("images/right.gif").getImage();
        ghost = new ImageIcon("images/qqq.png").getImage();
        heart = new ImageIcon("images/heart.png").getImage();
        cherry = new ImageIcon("images/cherry1.png").getImage();
    }
    private void initVariables() {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(760, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        timer.start();
    }

    private void playGame(Graphics2D g2d) throws SQLException, ClassNotFoundException {
        if (dying) {
            death();
        } else {
            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void drawFact(Graphics2D g) {
        g.setFont(factfont);
        g.setColor(new Color(5, 181, 79));
        String s = "HOW PACMAN CAN STAY HEALTHY";
        g.drawString(s, SCREEN_SIZE / 2 + 220, 30);

        String s1 = "if he eats healthy fruits";
        String s12 = "his life won't decrease";
        g.drawString(s1, SCREEN_SIZE / 2 + 270, 75);
        g.drawString(s12 , SCREEN_SIZE / 2 + 272 , 95);

        g.drawImage(cherry , SCREEN_SIZE / 2 + 270 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 290 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 310 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 330 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 350 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 370 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 390 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 410 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 430 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 450 , 115 , this);
        g.drawImage(cherry , SCREEN_SIZE / 2 + 470 , 115 , this);

        g.drawImage(cherry, SCREEN_SIZE / 2 + 270 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 290 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 310 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 330 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 350 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 370 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 390 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 410 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 430 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 450 , 140 , this);
        g.drawImage(cherry, SCREEN_SIZE / 2 + 470 , 140 , this);

        g.setColor(Color.red);
        String s2 = "If he eats junk food";
        String s3 = "his life decreases";
        g.drawString(s2, SCREEN_SIZE / 2 + 285, 200);
        g.drawString(s3 , SCREEN_SIZE / 2 + 290 , 220);
        g.drawImage(ghost , SCREEN_SIZE/2 + 265 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 295 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 325 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 355 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 385 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 415 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 445 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 475 , 240 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 265 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 295 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 325 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 355 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 385 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 415 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 445 , 270 , this);
        g.drawImage(ghost , SCREEN_SIZE/2 + 475 , 270 , this);

        String s5 = "(It is true in real life)";
        g.setColor(Color.green);
        g.setFont(qfont);
        g.drawString(s5 , SCREEN_SIZE/2 + 265 , 330);
        String s6 = "Press L for LeaderBoard";
        g.setColor(Color.yellow);
        g.setFont(factfont);
        g.drawString(s6 , SCREEN_SIZE/2 + 270 , 360);

        String s7 = "Press G for Main Menu";
        g.setColor(Color.yellow);
        g.setFont(factfont);
        g.drawString(s7 , SCREEN_SIZE/2 + 270 , 380);

    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;
        int cnt = 0 ;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }
            if(screenData[i] == 0){
                cnt++;
            }

            i++;
        }

        if(cnt == N_BLOCKS*N_BLOCKS -5){
            finished = true;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() throws SQLException, ClassNotFoundException {

        lives--;

        if (lives == 0) {
            System.out.println("1111111");
            jdbc q = new jdbc(score);
            inGame = false;
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        int pos;
        short ch;

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(Graphics2D g2d) {

        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;
        int cnt = 1;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));

                if ((levelData[i] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x-2, y-2, x-2, y-2 + BLOCK_SIZE  + 2);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x-2, y-2, x-2 + BLOCK_SIZE + 2, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x-2 + BLOCK_SIZE  + 2, y-2, x-2 + BLOCK_SIZE + 2,
                            y-2 + BLOCK_SIZE + 2);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x-2, y-2 + BLOCK_SIZE + 2, x-2 + BLOCK_SIZE +2,
                            y-2 + BLOCK_SIZE + 2);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.drawImage(cherry, x, y, this);
                    cnt++;
                }

                i++;
            }
        }
    }

    private void initGame() {

        lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {

        int dx = 1;
        int random;

        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 4 * BLOCK_SIZE; //start position
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        drawFact(g2d);

        if (inGame) {
            try {
                playGame(g2d);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }


    //controls
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                }
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    initGame();
                }
                if (key == KeyEvent.VK_L) {
                    try {
                        new Leaderboard();
                    } catch (Exception qq) {
                    }
                }
                if (key == KeyEvent.VK_G) {
                    try {
                        new ShowIntro();
                    } catch (Exception qq) {
                    }
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}