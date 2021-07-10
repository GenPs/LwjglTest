package engine.maths;

import engine.maths.vectors.Vector2;
import engine.maths.vectors.Vector3;

public class Matrix3{

    public static final int m00=0;
    public static final int m01=3;
    public static final int m02=6;
    public static final int m10=1;
    public static final int m11=4;
    public static final int m12=7;
    public static final int m20=2;
    public static final int m21=5;
    public static final int m22=8;
    public float[] val=new float[9];
    private float[] tmp=new float[9];

    public Matrix3(){
        idt();
    }

    public Matrix3(Matrix3 matrix){
        set(matrix);
    }

    public Matrix3(float[] values){
        this.set(values);
    }

    public Matrix3 idt(){
        float[] val=this.val;
        val[m00]=1;
        val[m10]=0;
        val[m20]=0;
        val[m01]=0;
        val[m11]=1;
        val[m21]=0;
        val[m02]=0;
        val[m12]=0;
        val[m22]=1;
        return this;
    }

    public Matrix3 mul(Matrix3 m){
        float[] val=this.val;

        float v00=val[m00]*m.val[m00]+val[m01]*m.val[m10]+val[m02]*m.val[m20];
        float v01=val[m00]*m.val[m01]+val[m01]*m.val[m11]+val[m02]*m.val[m21];
        float v02=val[m00]*m.val[m02]+val[m01]*m.val[m12]+val[m02]*m.val[m22];

        float v10=val[m10]*m.val[m00]+val[m11]*m.val[m10]+val[m12]*m.val[m20];
        float v11=val[m10]*m.val[m01]+val[m11]*m.val[m11]+val[m12]*m.val[m21];
        float v12=val[m10]*m.val[m02]+val[m11]*m.val[m12]+val[m12]*m.val[m22];

        float v20=val[m20]*m.val[m00]+val[m21]*m.val[m10]+val[m22]*m.val[m20];
        float v21=val[m20]*m.val[m01]+val[m21]*m.val[m11]+val[m22]*m.val[m21];
        float v22=val[m20]*m.val[m02]+val[m21]*m.val[m12]+val[m22]*m.val[m22];

        val[m00]=v00;
        val[m10]=v10;
        val[m20]=v20;
        val[m01]=v01;
        val[m11]=v11;
        val[m21]=v21;
        val[m02]=v02;
        val[m12]=v12;
        val[m22]=v22;

        return this;
    }

    public Matrix3 mulLeft(Matrix3 m){
        float[] val=this.val;

        float v00=m.val[m00]*val[m00]+m.val[m01]*val[m10]+m.val[m02]*val[m20];
        float v01=m.val[m00]*val[m01]+m.val[m01]*val[m11]+m.val[m02]*val[m21];
        float v02=m.val[m00]*val[m02]+m.val[m01]*val[m12]+m.val[m02]*val[m22];

        float v10=m.val[m10]*val[m00]+m.val[m11]*val[m10]+m.val[m12]*val[m20];
        float v11=m.val[m10]*val[m01]+m.val[m11]*val[m11]+m.val[m12]*val[m21];
        float v12=m.val[m10]*val[m02]+m.val[m11]*val[m12]+m.val[m12]*val[m22];

        float v20=m.val[m20]*val[m00]+m.val[m21]*val[m10]+m.val[m22]*val[m20];
        float v21=m.val[m20]*val[m01]+m.val[m21]*val[m11]+m.val[m22]*val[m21];
        float v22=m.val[m20]*val[m02]+m.val[m21]*val[m12]+m.val[m22]*val[m22];

        val[m00]=v00;
        val[m10]=v10;
        val[m20]=v20;
        val[m01]=v01;
        val[m11]=v11;
        val[m21]=v21;
        val[m02]=v02;
        val[m12]=v12;
        val[m22]=v22;

        return this;
    }

    public Matrix3 setToRotation(float degrees){
        return setToRotationRad(Maths.toRadians*degrees);
    }

    public Matrix3 setToRotationRad(float radians){
        float cos=(float)Math.cos(radians);
        float sin=(float)Math.sin(radians);
        float[] val=this.val;

        val[m00]=cos;
        val[m10]=sin;
        val[m20]=0;

        val[m01]=-sin;
        val[m11]=cos;
        val[m21]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=1;

        return this;
    }

    public Matrix3 setToRotation(Vector3 axis,float degrees){
        return setToRotation(axis,(float)Math.cos(degrees*Maths.toRadians),(float)Math.sin(degrees*Maths.toRadians));
    }

    public Matrix3 setToRotation(Vector3 axis,float cos,float sin){
        float[] val=this.val;
        float oc=1.0f-cos;
        val[m00]=oc*axis.x*axis.x+cos;
        val[m01]=oc*axis.x*axis.y-axis.z*sin;
        val[m02]=oc*axis.z*axis.x+axis.y*sin;
        val[m10]=oc*axis.x*axis.y+axis.z*sin;
        val[m11]=oc*axis.y*axis.y+cos;
        val[m12]=oc*axis.y*axis.z-axis.x*sin;
        val[m20]=oc*axis.z*axis.x-axis.y*sin;
        val[m21]=oc*axis.y*axis.z+axis.x*sin;
        val[m22]=oc*axis.z*axis.z+cos;
        return this;
    }

    public Matrix3 setToTranslation(float x,float y){
        float[] val=this.val;

        val[m00]=1;
        val[m10]=0;
        val[m20]=0;

        val[m01]=0;
        val[m11]=1;
        val[m21]=0;

        val[m02]=x;
        val[m12]=y;
        val[m22]=1;

        return this;
    }

    public Matrix3 setToTranslation(Vector2 translation){
        float[] val=this.val;

        val[m00]=1;
        val[m10]=0;
        val[m20]=0;

        val[m01]=0;
        val[m11]=1;
        val[m21]=0;

        val[m02]=translation.x;
        val[m12]=translation.y;
        val[m22]=1;

        return this;
    }

    public Matrix3 setToScaling(float scaleX,float scaleY){
        float[] val=this.val;
        val[m00]=scaleX;
        val[m10]=0;
        val[m20]=0;
        val[m01]=0;
        val[m11]=scaleY;
        val[m21]=0;
        val[m02]=0;
        val[m12]=0;
        val[m22]=1;
        return this;
    }

    public Matrix3 setToScaling(Vector2 scale){
        float[] val=this.val;
        val[m00]=scale.x;
        val[m10]=0;
        val[m20]=0;
        val[m01]=0;
        val[m11]=scale.y;
        val[m21]=0;
        val[m02]=0;
        val[m12]=0;
        val[m22]=1;
        return this;
    }

    public String toString(){
        float[] val=this.val;
        return "["+val[m00]+"|"+val[m01]+"|"+val[m02]+"]\n" //
                +"["+val[m10]+"|"+val[m11]+"|"+val[m12]+"]\n" //
                +"["+val[m20]+"|"+val[m21]+"|"+val[m22]+"]";
    }

    public float det(){
        float[] val=this.val;
        return val[m00]*val[m11]*val[m22]+val[m01]*val[m12]*val[m20]+val[m02]*val[m10]*val[m21]-val[m00]*val[m12]*val[m21]-val[m01]*val[m10]*val[m22]-val[m02]*val[m11]*val[m20];
    }

    public Matrix3 inv(){
        float det=det();
        if(det==0)
            throw new RuntimeException("Can't invert a singular matrix");

        float inv_det=1.0f/det;
        float[] tmp=this.tmp, val=this.val;

        tmp[m00]=val[m11]*val[m22]-val[m21]*val[m12];
        tmp[m10]=val[m20]*val[m12]-val[m10]*val[m22];
        tmp[m20]=val[m10]*val[m21]-val[m20]*val[m11];
        tmp[m01]=val[m21]*val[m02]-val[m01]*val[m22];
        tmp[m11]=val[m00]*val[m22]-val[m20]*val[m02];
        tmp[m21]=val[m20]*val[m01]-val[m00]*val[m21];
        tmp[m02]=val[m01]*val[m12]-val[m11]*val[m02];
        tmp[m12]=val[m10]*val[m02]-val[m00]*val[m12];
        tmp[m22]=val[m00]*val[m11]-val[m10]*val[m01];

        val[m00]=inv_det*tmp[m00];
        val[m10]=inv_det*tmp[m10];
        val[m20]=inv_det*tmp[m20];
        val[m01]=inv_det*tmp[m01];
        val[m11]=inv_det*tmp[m11];
        val[m21]=inv_det*tmp[m21];
        val[m02]=inv_det*tmp[m02];
        val[m12]=inv_det*tmp[m12];
        val[m22]=inv_det*tmp[m22];

        return this;
    }

    public Matrix3 set(Matrix3 mat){
        System.arraycopy(mat.val,0,val,0,val.length);
        return this;
    }

    public Matrix3 set(Affine2 affine){
        float[] val=this.val;

        val[m00]=affine.m00;
        val[m10]=affine.m10;
        val[m20]=0;
        val[m01]=affine.m01;
        val[m11]=affine.m11;
        val[m21]=0;
        val[m02]=affine.m02;
        val[m12]=affine.m12;
        val[m22]=1;

        return this;
    }

    public Matrix3 set(GdxMatrix4 mat){
        float[] val=this.val;
        val[m00]=mat.val[GdxMatrix4.M00];
        val[m10]=mat.val[GdxMatrix4.M10];
        val[m20]=mat.val[GdxMatrix4.M20];
        val[m01]=mat.val[GdxMatrix4.M01];
        val[m11]=mat.val[GdxMatrix4.M11];
        val[m21]=mat.val[GdxMatrix4.M21];
        val[m02]=mat.val[GdxMatrix4.M02];
        val[m12]=mat.val[GdxMatrix4.M12];
        val[m22]=mat.val[GdxMatrix4.M22];
        return this;
    }

    public Matrix3 set(float[] values){
        System.arraycopy(values,0,val,0,val.length);
        return this;
    }

    public Matrix3 trn(Vector2 vector){
        val[m02]+=vector.x;
        val[m12]+=vector.y;
        return this;
    }

    public Matrix3 trn(float x,float y){
        val[m02]+=x;
        val[m12]+=y;
        return this;
    }

    public Matrix3 trn(Vector3 vector){
        val[m02]+=vector.x;
        val[m12]+=vector.y;
        return this;
    }

    public Matrix3 translate(float x,float y){
        float[] val=this.val;
        tmp[m00]=1;
        tmp[m10]=0;
        tmp[m20]=0;

        tmp[m01]=0;
        tmp[m11]=1;
        tmp[m21]=0;

        tmp[m02]=x;
        tmp[m12]=y;
        tmp[m22]=1;
        mul(val,tmp);
        return this;
    }

    public Matrix3 translate(Vector2 translation){
        float[] val=this.val;
        tmp[m00]=1;
        tmp[m10]=0;
        tmp[m20]=0;

        tmp[m01]=0;
        tmp[m11]=1;
        tmp[m21]=0;

        tmp[m02]=translation.x;
        tmp[m12]=translation.y;
        tmp[m22]=1;
        mul(val,tmp);
        return this;
    }

    public Matrix3 rotate(float degrees){
        return rotateRad(Maths.toRadians*degrees);
    }

    public Matrix3 rotateRad(float radians){
        if(radians==0)
            return this;
        float cos=(float)Math.cos(radians);
        float sin=(float)Math.sin(radians);
        float[] tmp=this.tmp;

        tmp[m00]=cos;
        tmp[m10]=sin;
        tmp[m20]=0;

        tmp[m01]=-sin;
        tmp[m11]=cos;
        tmp[m21]=0;

        tmp[m02]=0;
        tmp[m12]=0;
        tmp[m22]=1;
        mul(val,tmp);
        return this;
    }

    public Matrix3 scale(float scaleX,float scaleY){
        float[] tmp=this.tmp;
        tmp[m00]=scaleX;
        tmp[m10]=0;
        tmp[m20]=0;
        tmp[m01]=0;
        tmp[m11]=scaleY;
        tmp[m21]=0;
        tmp[m02]=0;
        tmp[m12]=0;
        tmp[m22]=1;
        mul(val,tmp);
        return this;
    }

    public Matrix3 scale(Vector2 scale){
        float[] tmp=this.tmp;
        tmp[m00]=scale.x;
        tmp[m10]=0;
        tmp[m20]=0;
        tmp[m01]=0;
        tmp[m11]=scale.y;
        tmp[m21]=0;
        tmp[m02]=0;
        tmp[m12]=0;
        tmp[m22]=1;
        mul(val,tmp);
        return this;
    }

    public float[] getValues(){
        return val;
    }

    public Vector2 getTranslation(Vector2 position){
        position.x=val[m02];
        position.y=val[m12];
        return position;
    }

    public Vector2 getScale(Vector2 scale){
        float[] val=this.val;
        scale.x=(float)Math.sqrt(val[m00]*val[m00]+val[m01]*val[m01]);
        scale.y=(float)Math.sqrt(val[m10]*val[m10]+val[m11]*val[m11]);
        return scale;
    }

    public float getRotation(){
        return Maths.toDegrees*(float)Math.atan2(val[m10],val[m00]);
    }

    public float getRotationRad(){
        return (float)Math.atan2(val[m10],val[m00]);
    }

    public Matrix3 scl(float scale){
        val[m00]*=scale;
        val[m11]*=scale;
        return this;
    }

    public Matrix3 scl(Vector2 scale){
        val[m00]*=scale.x;
        val[m11]*=scale.y;
        return this;
    }

    public Matrix3 scl(Vector3 scale){
        val[m00]*=scale.x;
        val[m11]*=scale.y;
        return this;
    }

    public Matrix3 transpose(){
        // Where MXY you do not have to change MXX
        float[] val=this.val;
        float v01=val[m10];
        float v02=val[m20];
        float v10=val[m01];
        float v12=val[m21];
        float v20=val[m02];
        float v21=val[m12];
        val[m01]=v01;
        val[m02]=v02;
        val[m10]=v10;
        val[m12]=v12;
        val[m20]=v20;
        val[m21]=v21;
        return this;
    }

    private static void mul(float[] mata,float[] matb){
        float v00=mata[m00]*matb[m00]+mata[m01]*matb[m10]+mata[m02]*matb[m20];
        float v01=mata[m00]*matb[m01]+mata[m01]*matb[m11]+mata[m02]*matb[m21];
        float v02=mata[m00]*matb[m02]+mata[m01]*matb[m12]+mata[m02]*matb[m22];

        float v10=mata[m10]*matb[m00]+mata[m11]*matb[m10]+mata[m12]*matb[m20];
        float v11=mata[m10]*matb[m01]+mata[m11]*matb[m11]+mata[m12]*matb[m21];
        float v12=mata[m10]*matb[m02]+mata[m11]*matb[m12]+mata[m12]*matb[m22];

        float v20=mata[m20]*matb[m00]+mata[m21]*matb[m10]+mata[m22]*matb[m20];
        float v21=mata[m20]*matb[m01]+mata[m21]*matb[m11]+mata[m22]*matb[m21];
        float v22=mata[m20]*matb[m02]+mata[m21]*matb[m12]+mata[m22]*matb[m22];

        mata[m00]=v00;
        mata[m10]=v10;
        mata[m20]=v20;
        mata[m01]=v01;
        mata[m11]=v11;
        mata[m21]=v21;
        mata[m02]=v02;
        mata[m12]=v12;
        mata[m22]=v22;
    }

}