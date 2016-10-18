import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 





















public class spaceInvaders extends PApplet {

private ArrayList<Sprite> sprites;
private ArrayList<Enemy> enemies;
private ArrayList<Ship> players;
private ArrayList<Projectile> bullets;
private ArrayList<Ship> enemyTargets;
private ArrayList<Ship> friendlyTargets;

public static double Dist(float x1, float y1, float x2, float y2) {
  return Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2))  ;
}

public void settings() {  size(1000, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "spaceInvaders" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }

public void setup() {
  
  imageMode(CENTER);


  //Create Lists for other objects:
  bullets = new ArrayList<Projectile>();
  enemies = new ArrayList<Enemy>();
  enemyTargets = new ArrayList<Ship>();
  friendlyTargets = new ArrayList<Ship>();
  players=new ArrayList<Ship>();

  //Load Graphics
  sprites = new ArrayList<Sprite>();
  sprites.add(new Sprite("PlayerMouse.png", 60, 120));
  sprites.add(new Sprite("CatShip_A.png", 100, 70));
  sprites.add(new Sprite("CatShip_B.png", 100, 70));
  sprites.add(new Sprite("CheeseMissile.png", 30, 35));
  sprites.add(new Sprite("BossMissile.png", 40, 75));


  players.add(new Ship(width/2.0f, height-70, sprites.get(0), sprites.get(3)));
  //player.toggleDebug();
  friendlyTargets.add(players.get(0));
  System.out.println(friendlyTargets);


  //add enemies to the game
  for (int i = 0; i < 8; i++) {
    enemies.add(new Enemy(100.0f+150*i, 150.0f, sprites.get(1), sprites.get(4)));
    enemyTargets.add(enemies.get(enemies.size()-1));
  }
  for (int i = 0; i < 8; i++) {
    enemies.add(new Enemy(100.0f+150*i, 250.0f, sprites.get(2), sprites.get(4)));
    enemyTargets.add(enemies.get(enemies.size()-1));
  }
  //enemies.get(enemies.size()/2).toggleDebug();
}

/**There is no game menu right now, just start to play.
 */
public void draw() {
  background(255);
  doBulletStuff();
  doPlayerStuff();
  doEnemyStuff();
}


public void doBulletStuff() {
  //update all projectiles
  for (int i = bullets.size()-1; i>= 0; i--) {
    Projectile each = bullets.get(i);
    if (each.isToBeDeleted()) {
      bullets.remove(each);
    } else
    {
      each.update();
      each.display();
    }
  }
}

public  void doEnemyStuff() {
  //update all enemies
  for (int i = enemies.size()-1; i>= 0; i--) {
    Enemy each = enemies.get(i);
    if (each.isToBeDeleted()) {
      enemies.remove(each);
    } else
    {
      
      //Main idea: update, then display.
      each.update();
      each.display();
      if (each.canShoot()) {
        bullets.add( each.shoot( friendlyTargets ) );
      }
    }
  }
}

public void doPlayerStuff() {
  for (int i = players.size()-1; i>= 0; i--) {
    //handle the players
    Ship each = players.get(i);
    if (each.isToBeDeleted()) {
      players.remove(each);
    } else
    {

      //Main idea: update, then display.
      each.update();
      each.display();

      //slow the player down if they stop moving
      if (each.getSpeed() > 0.000001f) {
        each.setSpeed(each.getSpeed()*.97f);
      }
    }
  }
}

public void keyPressed() {
  if (players.size() > 0 ) {
    Ship player =  players.get(0);
    //System.out.println(keyCode);
    if (keyCode == 37) {
      player.moveLeft();
    }
    if (keyCode == 39) {
      player.moveRight();
    }
    if (keyCode == 32) {
      if (player.canShoot()) {
        bullets.add(players.get(0).shoot(enemyTargets));
      }
    }
  }
}





























class Agent{
  private Sprite sprite;
  private PVector position, velocity, acceleration;
  private float size;
  private float topSpeed;  
  private boolean rotates, debug, deleteMe;



  /**Change the deleteMe boolean to true.
   *somewhere in the code the program will remove all the agents that want to be deleted
   */
  public void deleteMe() {
    deleteMe = true;
  }

  /**
   */
  public boolean isToBeDeleted() {
    return deleteMe;
  }

  /**
   */
  public  float getAngle() {
    return atan2(velocity.y, velocity.x);
  }

  /**
   */
  public void setAngle(float angle) {
    float mag = velocity.mag();
    float x = cos(angle);
    float y = sin(angle);
    velocity = new PVector(x, y);
    velocity.mult(mag);
  }



  /**Accelerate the object to the left, this will make it start to move in that direction
   */
  public void moveLeft() {
    setAngle(PI);
    setSpeed(4);
  }

  /**Accelerate the object to the right, this will make it start to move in that direction
   */
  public void moveRight() {
    setAngle(0);
    setSpeed(4);
  }

  /**Accelerate the object to the bottom of the screen, this will make it start to move in that direction
   */
  public void moveDown() {
    setAngle(PI/2);
    setSpeed(4);
  }

  /**Accelerate the object to top of the screen, this will make it start to move in that direction
   */
  public void moveUp() {
    setAngle(3*PI/2);
    setSpeed(4);
  }

  /**Change the x,y position of the object
  *@param x the specified value to change position.x to
  *@param y the specified value to change position.y to
   */
  public void setXY(float x, float y) {
    position.x = x;
    position.y = y;
  }

  /**@return the x-position
   */
  public float getX() {
    return position.x;
  }

  /**Change the x position of the object to the specified value
  *@param x the specified value to change to
   */
  public void setX(float x) {
    position.x = x;
  }

  /**@return the y-position
   */
  public float getY() {
    return position.y;
  }

  /**Change the x position of the object 
  *@param y the specified value to change to
   */
  public void setY(float y) {
    position.y = y;
  }

  /**@return the x-velocity
   */
  public float getDX() {
    return velocity.x;
  }

  /**@return the y-velocity
   */
  public float getDY() {
    return velocity.y;
  }

  /**@return the x-size
   */
  public float getWidth() {
    if (sprite == null) {
      return size;
    } else {
      return sprite.width();
    }
  }


  /**@return the y-size
   */
  public float getHeight() {
    if (sprite == null) {
      return size;
    } else {
      return sprite.height();
    }
  }

  /**
   */
  public void toggleRotate() {
    rotates = !rotates;
  }

  /**
   */
  public void toggleDebug() {
    debug = !debug;
  }

  /**
   */
  public float getSpeed() {
    return velocity.mag();
  }

  /**
   */
  public void setSpeed(float speed) {
    velocity.normalize();
    velocity.mult(speed);
  }



  /**
   */
  public void turn(float angle) {
    setAngle(getAngle()+angle);
  }

  /**
   */
  public void update() {
    updatePhysics();
    keepInBounds();
  }

  //////////////////////////////////////////////////////////////////////////

  /**This method is called in the update, it is meant to handle how agents behave when they go off the screen
   *Unfortunately you don't know abstract methods yet, but this would be abstract.  
   */
  private void keepInBounds() {
  }


  /**
   */
  Agent(PVector pos, PVector vel, PVector acc, float size, float top, Sprite img) {
    position = pos;
    velocity = vel;
    acceleration = acc;
    this.size = size;
    topSpeed = top;
    sprite = img;
    rotates = img == null;
    deleteMe = false;
  }

  /**
   */
  public void display() {
    pushMatrix();
    translate(position.x, position.y);
    if (debug) {
      fill(0);
      text("Angle: "+degrees(getAngle())+"\nSpeed: "+getSpeed(), -20, 40);
      text("Pos: "+position+" ", -20, 100);
      text("Vel: "+velocity+" ", -20, 120);
      text("Acc: "+acceleration+" ", -20, 140);
      if (sprite!=null) {
        text("size: "+sprite.width()+","+sprite.height(), -20, 160);
      }
    }
    if (rotates) {
      rotate(getAngle());
    }
    if (sprite==null) {
      fill(255);
      rectMode(CENTER);
      rect(0, 0, size, size);
      arrow();
    } else {
      sprite.display();
    }

    popMatrix();
  }

  /**
   */
  private void arrow() {
    stroke(0);
    line(0, 0, 10*velocity.mag(), 0);
    ellipse(10*velocity.mag(), 0, 4, 4);
  }

  /**
   */
  private void updatePhysics() {
    position.add(velocity);
    velocity.add(acceleration);
    velocity.limit(topSpeed);
    acceleration.mult(0);
  }
}

























public class Enemy extends Ship {

  public Enemy(float x, float y, Sprite img, Sprite bimg) {
    super(x, y, img, bimg);
  }


  public void keepInBounds() {
    //request that the game deletes the object when 
    //the object it passes out of the screen to the right
    if (getX() > width + getWidth()/2) {
      deleteMe();
    }
  }

  /**@returns true 1/500th of the time
   */
  public boolean canShoot() {
    return (int)(Math.random()*1000) < 2;
  }

  //shoot returns a new projectile with properties defined by the ship that creates it. 
  public Projectile shoot(ArrayList<Ship> targets) {

    Projectile p = new Projectile(getX(), getY()+ 30, 0.0f, 4.0f, myBulletSprite, targets);
    return p;
  }

  public void update() {
    super.update();
    //add logic to change how to behave!
  }
}


























public class Projectile extends Agent {
  ArrayList<Ship> targets;


  public Projectile(float x, float y, float dx, float dy, Sprite img, ArrayList<Ship> whoToShoot) {
    super(new PVector(x, y), new PVector(dx, dy), new PVector(0, 0), 100, 10, img);
    targets = whoToShoot;
  }


  public void update() {
    super.update();
    for (Ship each : targets) {
      if (!isToBeDeleted() &&  Dist(getX(), getY(), each.getX(), each.getY()) < 50 ) {
        each.deleteMe();
        deleteMe();
      }
    }
  }

  public void keepInBounds() {
    if (getY() < -10 || getY() > height) {
      deleteMe();
    }
  }
}























public class Ship extends Agent {
  private int timeBetweenBullets;
  private int timeSinceLastBullet;
  Sprite myBulletSprite;

  public Ship(float x, float y, Sprite img, Sprite bimg) {
    super(new PVector(x, y), new PVector(0.5f, 0.0f), new PVector(0, 0), 100, 10, img);
    myBulletSprite = bimg;
    timeBetweenBullets = 60;
    timeSinceLastBullet = 60;
  }

  public boolean isHit(Projectile p){
    
   return spaceInvaders.Dist(getX(),getY(),p.getX(),p.getY()) < 50; 
  }

  public void hit(){
    deleteMe(); 
  }

  public void update() {
    super.update();
    timeSinceLastBullet++;
  }

  public void display(boolean debug) {
    super.display();

    if (debug) {
      if (canShoot()) {
        text("CAN SHOOT!", width/2, height/2);
      }
    }
  }

  public void keepInBounds() {
    setX(constrain(getX(), 0, width));
    setY(constrain(getY(), 0, height));
  }

  public boolean canShoot() {
    return timeSinceLastBullet > timeBetweenBullets;
  }

  //shoot returns a new projectile with properties defined by the ship that creates it. 
  public Projectile shoot(ArrayList<Ship> targets) {
    timeSinceLastBullet = 0;
    Projectile p = new Projectile(getX(), getY()-30, 0.0f, -5.0f, myBulletSprite, targets);
    return p;
  }
}






























/**this is a wrapper class to simplify the PImage to a single line filename + dimensions.
*/

public class Sprite{
  PImage img;
  
  public Sprite(String name, int width, int height){
     img = loadImage(name);
     img.resize(width,height);
     
  }
  
  public int width(){
   return img.width; 
  }
  public int height(){
   return img.height; 
  }
  
  public void display(){
   image(img,0,0); 
  }
}







}
