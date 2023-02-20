import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameField extends JPanel implements ActionListener{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    Image dot;
    Image apple;
    int appleX;
    int appleY;
    int[] x = new int[ALL_DOTS];
    int[] y = new int[ALL_DOTS];
    int dots;
    Timer timer;
    int foodEaten;
    boolean left = false;
    boolean right = true;
    boolean up = false;
    boolean down = false;
    boolean inGame = true;
    boolean endgame = false;
    public GameField(){
        setBackground(Color.black);
        setFocusable(true);
        Tim();
        setup();
    }

    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
    }

    public  void Tim (){
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }
    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }
            g.setColor(Color.GREEN);
            g.drawString("Score: " + foodEaten, 135, SIZE/12);
        }else {
            String str = "Game Over";

           // Font f = new Font("Arial", 14,Font.BOLD);
            g.setColor(Color.GREEN);
           // g.setFont(f);
            g.drawString(str,125,SIZE/2);
            g.drawString("Score: " + foodEaten, 135, SIZE/12);

        }
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            foodEaten++;
            createApple();
        }
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
                endgame = true;
            }
        }

        if(x[0]>SIZE){
            inGame = false;
            endgame = true;
        }
        if(x[0]<0){
            inGame = false;
            endgame = true;
        }
        if(y[0]>SIZE){
            inGame = false;
            endgame = true;
        }
        if(y[0]<0){
            inGame = false;
            endgame = true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    public void setup(){
        dots = 3;
        foodEaten = 0;
        inGame = true;
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener(this));
    }
}
