package Litsener;

import Managment.Counter;
import Managment.Game;
import Objects.Ball;
import Objects.Block;

public class BallRemover implements HitListener {
        private Game game;
        private Counter remainingBalls;

        public BallRemover(Game game, Counter removedBalls) {
            this.game = game;
            this.remainingBalls = removedBalls;
        }

        // Blocks that are hit should be removed
        // from the game. Remember to remove this listener from the block
        // that is being removed from the game.
        public void hitEvent(Block beingHit, Ball hitter)
        {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);

        }

    }
