package Litsener;

import Objects.Ball;
import Objects.Block;

public interface HitListener {
    void hitEvent(Block beingHit, Ball hitter);
}
