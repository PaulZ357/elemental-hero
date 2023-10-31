package Screens;

import Engine.*;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the Void level lose screen
public class VoidLoseScreen extends Screen {
    protected SpriteFont loseMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayVoidLevelScreen playVoidLevelScreen;
    protected ScreenCoordinator level = new ScreenCoordinator();

    public VoidLoseScreen(PlayVoidLevelScreen playVoidLevelScreen) {
        this.playVoidLevelScreen = playVoidLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        loseMessage = new SpriteFont("You lose!", 400, 239, "Comic Sans", 30, Color.white);
        instructions = new SpriteFont("Press Space to try again or Escape to go back to the main menu", 120, 279,"Comic Sans", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        GamePanel.setLostOrCleared(true);
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playVoidLevelScreen.resetLevel();
            GamePanel.setLostOrCleared(false);
        }else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playVoidLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        loseMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
