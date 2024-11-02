package Litsener;

import Managment.Counter;
import Objects.Ball;
import Objects.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter)
    {
       this.currentScore.increase(5);
       beingHit.removeHitListener(this);
    }
}