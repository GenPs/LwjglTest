package engine.graphics;

import engine.maths.Matrix4;
import engine.maths.vectors.Vector2;

public class OrthographicCamera{

    private float scale,rotation;
    private final Vector2 position;
    private float width,height;
    private Matrix4 projection;

    public OrthographicCamera(float width,float height){
        this.width=width;
        this.height=height;
        position=new Vector2();
        scale=1;

        projection=new Matrix4();
        projection.setToOrtho2D(0,0,width,height);
    }

    public void resize(int width,int height){
        this.width=width;
        this.height=height;
    }

    public void update(){
        projection=new Matrix4();
        projection.setToOrtho2D(0,0,width,height);
        projection.rotate(-rotation,0,0,1);
        projection.translate(-position.x,-position.y,0);
        projection.scale(scale);
    }

    public void translate(float x,float y){
        position.add(x,y);
    }

    public void translate(Vector2 v){
        position.add(v);
    }

    public void setPosition(float x,float y){
        position.set(x,y);
    }

    public void setPosition(Vector2 pos){
        position.set(pos);
    }

    public void scale(float scale){
        this.scale*=scale;
    }

    public void setScale(float scale){
        this.scale=scale;
    }

    public void rotate(float deg){
        rotation+=deg;
    }

    public void setRotation(float deg){
        rotation=deg;
    }

    public Matrix4 getProjection(){
        return projection;
    }

    public Vector2 getPos(){
        return position;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getScale(){
        return scale;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

}
