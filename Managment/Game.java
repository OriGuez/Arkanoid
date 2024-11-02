 
package Managment;
import Geo.Point;
import Litsener.BallRemover;
import Litsener.BlockRemover;
import Litsener.PrintingHitListener;
import Litsener.ScoreTrackingListener;
import Objects.Ball;
import Objects.Paddle;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import Objects.Block;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCount;
    private Counter ballCount;
    private GUI gui;
    private Sleeper sleeper;
    private biuoop.KeyboardSensor keyboardSensor;
    private Counter score;
    public Game()
    {
        this.environment= new GameEnvironment();
        this.sprites= new SpriteCollection();
        this.gui= new GUI("Ark", 800, 600);
        this. sleeper= new Sleeper();
        this.keyboardSensor= gui.getKeyboardSensor();
        this.blockCount=new Counter(57);
        this.ballCount=new Counter(3);
        this.score=new Counter(0);
    }

    public void addCollidable(Collidable c)
    {
        this.environment.addCollidable(c);
    }
    public void addSprite(Sprite s)
    {
        this.sprites.addSprite(s);
    }
    public void removeCollidable(Collidable c)
    {
        this.environment.removeCollidable(c);
    }
    public void removeSprite(Sprite s)
    {
        this.sprites.removeSprite(s);
    }

    // Initialize a new game: create the Blocks and Objects.Ball (and Objects.Paddle)
    // and add them to the game.
    public void initialize()
    {
        PrintingHitListener phl= new PrintingHitListener();
        ScoreTrackingListener stl= new ScoreTrackingListener(this.score);
        ScoreIndicator si= new ScoreIndicator(this.score);
        si.addToGame(this);
        BlockRemover br= new BlockRemover(this, this.blockCount);
        BallRemover blr=new BallRemover(this, this.ballCount);
        Block block;
        Paddle paddle= new Paddle(new Point(300,580), 100, 20,
                java.awt.Color.YELLOW, this.keyboardSensor);
        paddle.addToGame(this);
        java.awt.Color[] colors = new java.awt.Color[]
                {java.awt.Color.GRAY, java.awt.Color.RED, java.awt.Color.YELLOW,
                        java.awt.Color.BLUE, java.awt.Color.PINK, java.awt.Color.GREEN};
        for (int j = 0; j < 6; j++) {
            for (int i = 1; i < 13 - j; i++) {
                block = new Block(new Point(790 - 50 * i, 100 + j * 30), 50, 30, colors[j]);
                block.addHitListener(phl);
                block.addHitListener(br);
                block.addToGame(this);
                block.addHitListener(stl);
            }
        }
        block = new Block(new Point(0, 10), 10, 600, java.awt.Color.BLACK);
        block.addToGame(this);
        block = new Block(new Point(0, 15), 800, 10, java.awt.Color.BLACK);
        block.addToGame(this);
        block = new Block(new Point(10, 610), 780, 10, java.awt.Color.BLACK);
        block.addHitListener(blr);
        block.addToGame(this);
        block = new Block(new Point(790, 10), 10, 600, java.awt.Color.BLACK);
        block.addToGame(this);
        Ball ball = new Ball(new Point(100, 300), 5, java.awt.Color.RED, this.environment);
        ball.setVelocity(2, 5);
        ball.addToGame(this);
        ball = new Ball(new Point(400, 500), 5, java.awt.Color.RED, this.environment);
        ball.setVelocity(-2, 6);
        ball.addToGame(this);
        ball = new Ball(new Point(450, 520), 5, java.awt.Color.RED, this.environment);
        ball.setVelocity(-5, 5);
        ball.addToGame(this);

    }

    // Run the game -- start the animation loop.
    public void run()
    {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.blockCount.getValue()<=0){
                System.out.println("out of blocks");
                this.score.increase(100);
                this.gui.close();
                break;
            }
            if(this.ballCount.getValue()<=0)
            {
                System.out.println("out of balls");
                this.gui.close();
                break;
            }
        }

    }
}