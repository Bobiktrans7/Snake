import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FieldKeyListener extends KeyAdapter {
    private GameField game;
    public FieldKeyListener(GameField n){
        this.game  = n;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (game.endgame) {
                game.setup();
            } else {
                game.inGame = !game.inGame;
            }
        } else if (key == KeyEvent.VK_LEFT && !game.right) {
            game.left = true;
            game. up = false;
            game.down = false;
        } else if (key == KeyEvent.VK_RIGHT && !game.left) {
            game.right = true;
            game.up = false;
            game.down = false;
        } else if (key == KeyEvent.VK_UP && !game.down) {
            game.right = false;
            game.up = true;
            game.left = false;
        } else if (key == KeyEvent.VK_DOWN && !game.up) {
            game.right = false;
            game.down = true;
            game.left = false;
        }
    }
}
