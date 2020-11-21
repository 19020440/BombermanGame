
package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Oneal;
import uet.oop.bomberman.entities.PlameItem;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.SpeedItem;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static int countLoop = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static  boolean goUp,goDown,goRight,goLeft,running;
	
    
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
       
        
       //move
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goUp = true; break;
                    case DOWN:  goDown = true; break;
                    case LEFT:  goLeft  = true; break;
                    case RIGHT: goRight  = true; break;
                    case SHIFT: running = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goUp = false; break;
                    case DOWN:  goDown = false; break;
                    case LEFT:  goLeft  = false; break;
                    case RIGHT: goRight  = false; break;
                    case SHIFT: running = false; break;
                }
            }
        });
        //khác
        stage.setScene(scene);
        stage.show();
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        
        
    }

    public static void createMap() throws FileNotFoundException {
//      for (int i = 0; i < WIDTH; i++) {
//          for (int j = 0; j < HEIGHT; j++) {
//              Entity object;
//              if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                  object = new Wall(i, j, Sprite.wall.getFxImage());
//              }
//              else {
//                  object = new Grass(i, j, Sprite.grass.getFxImage());
//              }
//              stillObjects.add(object);
//          }
//      }
  	List<String> rs = new ArrayList<String>(); 
  	
  	String url = "C:\\Users\\DELL\\Documents\\Projects\\bomberman-starter-starter-2\\res\\levels/Level1.txt";
  	FileInputStream fis = new FileInputStream(url);
  	Scanner sc = new Scanner(fis);
  	int[] a = new int[3];
  	try {
  		for(int i=0;i<3;++i) {
  			a[i] = sc.nextInt();
  		}
  		sc.nextLine();
  		int dem=-1;
  		while(dem!=a[1]) {
  			String line = sc.nextLine();
  			rs.add(line);
  			dem++;
  		}
  		System.out.println(rs.get(0));
  	} finally {
  		try {
  			sc.close();
  			fis.close();
  		}catch(IOException ex) {
  			System.out.println(ex);
  	}
  }
 
  	
//  	for(int i=0;i<rs.size();++i) {
//  		Entity object;
//  		for(int j=0;j<rs.get(i).length();++j) {
//  			
//  			if(rs.get(i).charAt(j) == '#') object = new Wall(j, i, Sprite.wall.getFxImage());
//  			else if(rs.get(i).charAt(j) == '*') object = new Brick(j, i, Sprite.brick.getFxImage());
//  			else if(rs.get(i).charAt(j) == 'x') object = new Portal(j, i, Sprite.portal.getFxImage());
//  			else if(rs.get(i).charAt(j) == '1') object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
//  			else if(rs.get(i).charAt(j) == '2') object = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
//  			else if(rs.get(i).charAt(j) == 'f') object = new PlameItem(j, i, Sprite.powerup_flamepass.getFxImage());
//  			else if(rs.get(i).charAt(j) == 's') object = new SpeedItem(j, i, Sprite.portal.getFxImage());
//
//  			else object = new Grass(j, i, Sprite.grass.getFxImage());
//  			stillObjects.add(object);
//  		}
  		
  	for (int i = 0; i < rs.size(); i++) {
        for (int j = 0; j < rs.get(i).length(); j++) {
            Entity object;
            Entity object1 = null;
            if (rs.get(i).charAt(j) == '#') {
            	object = new Wall(j, i, Sprite.wall.getFxImage());
            } else {
            	object = new Grass(j, i, Sprite.grass.getFxImage());
                if (rs.get(i).charAt(j) == '*') {
                	object1 = new Brick(j, i, Sprite.brick.getFxImage());
                } else if (rs.get(i).charAt(j) == '1') {
                	object1 = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                    
                } else if (rs.get(i).charAt(j) == '2') {
                	object1 = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                } else if (rs.get(i).charAt(j) == 'p') {
                    object1 = new Bomber(j, i , Sprite.player_down.getFxImage());
                    
                } else if (rs.get(i).charAt(j) == 's') {
                	object1 = new SpeedItem(j, i, Sprite.portal.getFxImage());;
                }

                if (object1 != null)
                    entities.add(object1);
            }
            stillObjects.add(object);
        }

    }
			
  	}
  

    public void update() {
        entities.forEach(t -> {
			t.update();
		});
        
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
    

//    public static  Entity getEntity() {
//    	return bomberman;
//    }
}