package engine.graphics;

import engine.maths.EulerAngle;
import engine.maths.Matrix4;
import engine.maths.vectors.Vector3;

public class PerspectiveCamera{

    private Vector3 position;
    private EulerAngle angles;

    private Matrix4 projection,view;

    private int width,height;
    private float field_of_view,near,far;

    public PerspectiveCamera(int width,int height,float near,float far,float field_of_view){
        this.width=width;
        this.height=height;
        this.near=near;
        this.far=far;
        this.field_of_view=field_of_view;

        position=new Vector3();
        angles=new EulerAngle();

        update();
    }

    public Matrix4 calculateProjection(){
        return new Matrix4().setToPerspective(width,height,near,far,field_of_view);
    }

    public Matrix4 calculateView(){
        Matrix4 result=new Matrix4();

        result.translate(position.mul(-1));

        Vector3 a=angles.toVector();
        result.rotate(a.x,1,0,0);
        result.rotate(a.y,0,1,0);
        result.rotate(a.z,0,0,1);

        result.scale(1,1,1);

        return result;
    }

    public void update(){
        projection=calculateProjection();
        view=calculateView();
    }

    public void lookTo(Vector3 direction){
        angles.toAngles(direction);
        update();
    }

    public void lookAt(Vector3 point){
        Vector3 direction=point.sub(position);
        lookTo(direction);
    }



    public void resize(int width,int height){
        this.width=width;
        this.height=height;
    }

    public Vector3 getPosition(){
        return position;
    }
    public void setPosition(Vector3 position){
        this.position=position;
    }
    public void translate(float x,float y,float z){
        position.add(x,y,z);
    }

    public Matrix4 getProjection(){
        return projection;
    }
    public Matrix4 getView(){
        return view;
    }

    public EulerAngle getRotation(){
        return angles;
    }
    public void setRotation(EulerAngle angles){
        this.angles=angles;
    }
    public void setRotation(float yaw,float pitch,float roll){
        this.angles.setAngles(pitch,yaw,roll);
    }
    public void rotate(EulerAngle eulerAngles){
        angles.setYaw(angles.getYaw()+eulerAngles.getYaw());
        angles.setPitch(angles.getPitch()+eulerAngles.getPitch());
        angles.setRoll(angles.getRoll()+eulerAngles.getRoll());
    }
    public void rotate(float yaw,float pitch,float roll){
        angles.setYaw(angles.getYaw()+yaw);
        angles.setPitch(angles.getPitch()+pitch);
        angles.setRoll(angles.getRoll()+roll);
    }

    public float getNear(){
        return near;
    }
    public void setNear(float near){
        this.near=near;
    }

    public void setFar(float far){
        this.far=far;
    }
    public float getFar(){
        return far;
    }

    public float getFOV(){
        return field_of_view;
    }
    public void setFOV(float fov){
        this.field_of_view=fov;
    }

}
