package Managment;

import biuoop.DrawSurface;

public class ScoreIndicator implements Sprite {
    private Counter score;
    public ScoreIndicator(Counter score)
    {
        this.score=score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(380,12,"Score: "+ score, 12);

    }

    public void addToGame(Game g){
        g.addSprite(this);
    }
    @Override
    public void timePassed() {

    }
}
