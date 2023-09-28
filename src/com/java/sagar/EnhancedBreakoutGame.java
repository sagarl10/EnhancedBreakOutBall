package com.java.sagar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EnhancedBreakoutGame extends JPanel implements ActionListener, KeyListener {

    private enum GameState {
        MENU, PLAYING, GAME_OVER
    }

    private GameState gameState;
    private Timer timer;
    private Paddle paddle;
    private List<Brick> bricks;
    private Ball ball;
    private int score;
    private int lives;

    public EnhancedBreakoutGame() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        gameState = GameState.MENU;

        timer = new Timer(10, this);

        initializeGame();
    }

    private void initializeGame() {
        paddle = new Paddle();
        bricks = new ArrayList<>();
        ball = new Ball();

        score = 0;
        lives = 3;

        createBricks();

        timer.start();
    }

    private void createBricks() {
        int brickRowCount = 5;
        int brickColumnCount = 10;

        for (int i = 0; i < brickRowCount; i++) {
            for (int j = 0; j < brickColumnCount; j++) {
                int brickX = j * 75 + 50;
                int brickY = i * 30 + 50;
                bricks.add(new Brick(brickX, brickY));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState == GameState.MENU) {
            drawMenu(g);
        } else if (gameState == GameState.PLAYING) {
            drawGame(g);
        } else if (gameState == GameState.GAME_OVER) {
            drawGameOver(g);
        }
    }

    private void drawMenu(Graphics g) {
        // Draw a title
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Breakout Game", 300, 100);

        // Draw menu options
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ENTER to Start", 300, 200);
    }

    private void drawGame(Graphics g) {
        // Draw the paddle
        g.setColor(Color.WHITE);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

        // Draw the ball
        g.setColor(Color.WHITE);
        g.fillOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), 2 * ball.getRadius(), 2 * ball.getRadius());

        // Draw bricks (you can customize brick graphics)
        for (Brick brick : bricks) {
            g.setColor(Color.BLUE);
            g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }

        // Draw score and lives
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 700, 30);
    }

    private void drawGameOver(Graphics g) {
        // Draw game over message
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", 330, 200);

        // Display the final score
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Score: " + score, 360, 300);

        // Instructions to restart
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Press ENTER to Restart", 320, 400);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.PLAYING) {
            updateGame();
        }

        repaint();
    }

    private void updateGame() {
        paddle.move();
        ball.move();

        checkCollisions();

        if (bricks.isEmpty()) {
            // All bricks are destroyed, the player wins
            gameState = GameState.GAME_OVER;
        }

        if (ball.getY() > getHeight()) {
            // Ball goes out of bounds, lose a life
            lives--;

            if (lives <= 0) {
                gameState = GameState.GAME_OVER;
            } else {
                // Reset paddle and ball positions
                paddle.resetPosition();
                ball.resetPosition();
            }
        }
    }

    private void checkCollisions() {
        Rectangle ballRect = ball.getBounds();

        // Paddle collision
        if (ballRect.intersects(paddle.getBounds())) {
            ball.reverseYDirection();
        }

        // Brick collisions
        for (Brick brick : bricks) {
            if (ballRect.intersects(brick.getBounds())) {
                ball.reverseYDirection();
                bricks.remove(brick);
                score += 10;
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (gameState == GameState.MENU) {
            if (key == KeyEvent.VK_ENTER) {
                gameState = GameState.PLAYING;
            }
        } else if (gameState == GameState.PLAYING) {
            if (key == KeyEvent.VK_LEFT) {
                paddle.setLeft(true);
            }
            if (key == KeyEvent.VK_RIGHT) {
                paddle.setRight(true);
            }
        } else if (gameState == GameState.GAME_OVER) {
            if (key == KeyEvent.VK_ENTER) {
                initializeGame();
                gameState = GameState.PLAYING;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (gameState == GameState.PLAYING) {
            if (key == KeyEvent.VK_LEFT) {
                paddle.setLeft(false);
            }
            if (key == KeyEvent.VK_RIGHT) {
                paddle.setRight(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Enhanced Breakout Game");
        EnhancedBreakoutGame game = new EnhancedBreakoutGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.gameState = GameState.MENU; // Start with the menu
    }
}
