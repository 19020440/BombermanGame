package uet.oop.bomberman.entities;

import java.awt.Rectangle;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
public class Bomber extends Entity {
	
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }


	
    public Bomber(double x, double y, Image img) {
        super( x, y, img);
        super.speed = 0.25;
    }

    public Bomber(double x, double y, Image img, Sprite sprite) {
        super( x, y, img);
        super.speed = 0.25;
        this.sprite = sprite;
    }

    @Override
    public void update() {
        move();
        
//        for (Entity entity : BombermanGame.stillObjects) {
//            if (entity instanceof Wall && this.intersects(entity)) {
//                speed = 0;
////                moveImpact(entity);
//                //System.out.println("xBom =" + x + " yBom = " + y + "\t|" + "xEnti =" + entity.x + " yEnti = " + entity.y);
//                System.out.println("va cham voi tuong");
//            }
//        }

		this.setPosition();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		this.speed = 0.5;
		this.setVelocity(0, 0);
		if(BombermanGame.goDown) {
			this.img = Sprite.player_down_1.getFxImage();
//			this.sprite = Sprite.player_down_1;
			this.addVelocity(0, 1.5);
		}
		if(BombermanGame.goUp) {
			this.img = Sprite.player_up_1.getFxImage();
//			this.sprite = Sprite.player_up_1;
			this.addVelocity(0, -1.5);
		}
		if(BombermanGame.goRight) {
			this.img = Sprite.player_right_1.getFxImage();
//			this.sprite = Sprite.player_right_1;
			this.addVelocity(1.5, 0);
		}
		if(BombermanGame.goLeft) {
			this.img = Sprite.player_left_1.getFxImage();
			this.sprite = Sprite.player_left_1;
			this.addVelocity(-1.5, 0);
		}
		
	}
}
	
	
	
