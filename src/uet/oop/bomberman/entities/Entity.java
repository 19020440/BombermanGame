package uet.oop.bomberman.entities;

import java.awt.Rectangle;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import java.awt.*;
public abstract class Entity {
//	 protected static double SIZE_DRAW_DEFAULT = 0.95;
//    //Tọa độ X tính từ góc trái trên trong Canvas
//    protected double x;
//
//    //Tọa độ Y tính từ góc trái trên trong Canvas
//    protected double y;
//
//    protected Image img;
//    
//    protected double velocityX,velocityY;
//    
//    protected double speed,width,height;
//    
//    protected Sprite sprite = null;
//    
//    protected Rectangle rectangle;
//    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
//    public Entity( double xUnit, double yUnit, Image img) {
//        this.x = xUnit * Sprite.SCALED_SIZE;
//        this.y = yUnit * Sprite.SCALED_SIZE;
//        this.img = img;
////        this.rectangle = new Rectangle((int) x,(int) y,(int) img.getWidth(), (int) img.getHeight());
//    }
//
//    public void render(GraphicsContext gc) {
//        gc.drawImage(img, x, y);
//    }
//    public abstract void update();
//
//    //
//    public void setVelocity(double X, double Y) {
//        this.velocityX = X;
//        this.velocityY = Y;
//    }
//
//    public void addVelocity(double X, double Y) {
//
//        this.velocityX += X;
//        this.velocityY += Y;
//    }
//    //
//    public void setPosition() {
//        x += velocityX*speed;
//        y += velocityY*speed;
//    }
//    //
//    
//    
//    
//    
//	public void move() {
//		// TODO Auto-generated method stub
//		 
//	}
//	

	
	protected static double SIZE_DRAW_DEFAULT = 0.95;

    protected double x;
    protected double y;
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected double width;
    protected double height;
    protected double widthDraw;
    protected double heightDraw;
    protected double speed;
    protected Image img;
    protected Sprite sprite = null;
    protected int heart;
    protected final double IMPACT = 0.97;
    protected int index_left = 1;
    protected int index_right = 1;
    protected int index_up = 1;
    protected int index_down = 1;
    protected int index_dead = 1;
    protected int index_XX = 1;


    public Entity( double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    public Entity( double x, double y, Image img, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.sprite = sprite;
        int maxSize = Math.max(sprite.get_realWidth(), sprite.get_realHeight());
        this.widthDraw = sprite.get_realWidth() * 1.0 / maxSize;
        this.heightDraw = sprite.get_realHeight() * 1.0 / maxSize;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void updateSize() {
        this.width = img.getWidth();
        this.height = img.getHeight();
        int maxSize = Math.max(sprite.get_realWidth(), sprite.get_realHeight());
        this.widthDraw = sprite.get_realWidth() * 1.0 / maxSize;
        this.heightDraw = sprite.get_realHeight() * 1.0 / maxSize;
    }


    public void setVelocity(double X, double Y) {
        this.velocityX = X;
        this.velocityY = Y;
    }

    public void addVelocity(double X, double Y) {

        this.velocityX += X;
        this.velocityY += Y;
    }

    public void setPosition() {
        x += velocityX * speed;
        y += velocityY * speed;
    }

    public Rectangle2D getBoundary() {
        Rectangle2D rectangle2D = null;
        //System.out.println(this + " x = " + x + " y = " + y);
        try {
            int maxSize = Math.max(sprite.get_realWidth(), sprite.get_realHeight());

            rectangle2D = new Rectangle2D(x + velocityX * speed, y + velocityY * speed
                    , sprite.get_realWidth() * 1.0 / maxSize, sprite.get_realHeight() * 1.0 / maxSize);
            //System.out.println(" w = " + sprite.get_realWidth() * IMPACT / maxSize + " h = " + sprite.get_realHeight() * IMPACT / maxSize);
        } catch (NullPointerException e) {
            //speed = 0;
            rectangle2D = new Rectangle2D(x + velocityX * speed, y + velocityY * speed, SIZE_DRAW_DEFAULT, SIZE_DRAW_DEFAULT);
            //System.out.println("hi");
        }
        return rectangle2D;
    }

    public boolean intersects(Entity entity) {

        return entity.getBoundary().intersects(this.getBoundary());

    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();
    public abstract void move();



}