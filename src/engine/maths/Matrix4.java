package engine.maths;

import engine.maths.vectors.Vector3;

public class Matrix4{


    public static final int m00=0;
    public static final int m10=1;
    public static final int m20=2;
    public static final int m30=3;

    public static final int m01=4;
    public static final int m11=5;
    public static final int m21=6;
    public static final int m31=7;

    public static final int m02=8;
    public static final int m12=9;
    public static final int m22=10;
    public static final int m32=11;

    public static final int m03=12;
    public static final int m13=13;
    public static final int m23=14;
    public static final int m33=15;

    public float[] val;


    public Matrix4(){
        val=new float[16];
        val[m00]=1;
        val[m11]=1;
        val[m22]=1;
        val[m33]=1;
    }

    public Matrix4(Matrix4 matrix4){
        val=new float[16];
        val[m00]=matrix4.val[m00];
        val[m10]=matrix4.val[m10];
        val[m20]=matrix4.val[m20];
        val[m30]=matrix4.val[m30];

        val[m01]=matrix4.val[m01];
        val[m11]=matrix4.val[m11];
        val[m21]=matrix4.val[m21];
        val[m31]=matrix4.val[m31];

        val[m02]=matrix4.val[m02];
        val[m12]=matrix4.val[m12];
        val[m22]=matrix4.val[m22];
        val[m32]=matrix4.val[m32];

        val[m03]=matrix4.val[m03];
        val[m13]=matrix4.val[m13];
        val[m23]=matrix4.val[m23];
        val[m33]=matrix4.val[m33];
    }

    public Matrix4(float[] floats){
        val=new float[16];
        val[m00]=floats[m00];
        val[m10]=floats[m10];
        val[m20]=floats[m20];
        val[m30]=floats[m30];

        val[m01]=floats[m01];
        val[m11]=floats[m11];
        val[m21]=floats[m21];
        val[m31]=floats[m31];

        val[m02]=floats[m02];
        val[m12]=floats[m12];
        val[m22]=floats[m22];
        val[m32]=floats[m32];

        val[m03]=floats[m03];
        val[m13]=floats[m13];
        val[m23]=floats[m23];
        val[m33]=floats[m33];
    }


    public Matrix4 setZero(){
        val[m00]=0;
        val[m10]=0;
        val[m20]=0;
        val[m30]=0;

        val[m01]=0;
        val[m11]=0;
        val[m21]=0;
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=0;
        val[m32]=0;

        val[m03]=0;
        val[m13]=0;
        val[m23]=0;
        val[m33]=0;

        return this;
    }

    public Matrix4 setIdentity(){
        val[m00]=1;
        val[m10]=0;
        val[m20]=0;
        val[m30]=0;

        val[m01]=0;
        val[m11]=1;
        val[m21]=0;
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=1;
        val[m32]=0;

        val[m03]=0;
        val[m13]=0;
        val[m23]=0;
        val[m33]=1;

        return this;
    }

    public Matrix4 set(Matrix4 matrix4){
        val[m00]=matrix4.val[m00];
        val[m10]=matrix4.val[m10];
        val[m20]=matrix4.val[m20];
        val[m30]=matrix4.val[m30];

        val[m01]=matrix4.val[m01];
        val[m11]=matrix4.val[m11];
        val[m21]=matrix4.val[m21];
        val[m31]=matrix4.val[m31];

        val[m02]=matrix4.val[m02];
        val[m12]=matrix4.val[m12];
        val[m22]=matrix4.val[m22];
        val[m32]=matrix4.val[m32];

        val[m03]=matrix4.val[m03];
        val[m13]=matrix4.val[m13];
        val[m23]=matrix4.val[m23];
        val[m33]=matrix4.val[m33];

        return this;
    }

    public Matrix4 set(float[] floats){
        val[m00]=floats[m00];
        val[m10]=floats[m10];
        val[m20]=floats[m20];
        val[m30]=floats[m30];

        val[m01]=floats[m01];
        val[m11]=floats[m11];
        val[m21]=floats[m21];
        val[m31]=floats[m31];

        val[m02]=floats[m02];
        val[m12]=floats[m12];
        val[m22]=floats[m22];
        val[m32]=floats[m32];

        val[m03]=floats[m03];
        val[m13]=floats[m13];
        val[m23]=floats[m23];
        val[m33]=floats[m33];

        return this;
    }

    /*public Matrix4 setToPerspective(float left,float right,float bottom,float top,float near,float far){
        val[m00]=2*near/(right-left);
        val[m10]=0;
        val[m20]=(right+left)/(right-left);
        val[m30]=0;

        val[m01]=0;
        val[m11]=2*near/(top-bottom);
        val[m21]=(top+bottom)/(top-bottom);
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=-(far+near)/(far-near);
        val[m32]=-2*far*near/(far-near);

        val[m03]=0;
        val[m13]=0;
        val[m23]=-1;
        val[m33]=0;

        return this;
    }*/

    public Matrix4 setToPerspective(float width,float height,float near,float far,float fov){
        float sy=1/(float)Math.tan(fov*Maths.toRadians/2);
        float mfl=near-far;

        val[m00]=height/width*sy;
        val[m10]=0;
        val[m20]=0;
        val[m30]=0;

        val[m01]=0;
        val[m11]=sy;
        val[m21]=0;
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=(far+near)/mfl;
        val[m32]=2*far*near/mfl;

        val[m03]=0;
        val[m13]=0;
        val[m23]=-1;
        val[m33]=0;

        return this;
    }

    public Matrix4 setToOrtho(float left,float right,float bottom,float top,float near,float far){
        val[m00]=2/(right-left);
        val[m10]=0;
        val[m20]=0;
        val[m30]=0;

        val[m01]=0;
        val[m11]=2/(top-bottom);
        val[m21]=0;
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=-2/(far-near);
        val[m32]=0;

        val[m03]=-(right+left)/(right-left);
        val[m13]=-(top+bottom)/(top-bottom);
        val[m23]=-(far+near)/(far-near);
        val[m33]=1;

        return this;
    }

    public Matrix4 setToOrtho2D(float x,float y,float width,float height){
        val[m00]=2/width;
        val[m10]=0;
        val[m20]=0;
        val[m30]=0;

        val[m01]=0;
        val[m11]=2/height;
        val[m21]=0;
        val[m31]=0;

        val[m02]=0;
        val[m12]=0;
        val[m22]=-2;
        val[m32]=0;

        val[m03]=-(x*2+width)/width;
        val[m13]=-(y*2+height)/height;
        val[m23]=-1;
        val[m33]=1;

        return this;
    }

    public static void mul(Matrix4 a,Matrix4 b){
        a.val[m00]=a.val[m00]*b.val[m00]+a.val[m01]*b.val[m10]+a.val[m02]*b.val[m20]+a.val[m03]*b.val[m30];
        a.val[m10]=a.val[m00]*b.val[m01]+a.val[m01]*b.val[m11]+a.val[m02]*b.val[m21]+a.val[m03]*b.val[m31];
        a.val[m20]=a.val[m00]*b.val[m02]+a.val[m01]*b.val[m12]+a.val[m02]*b.val[m22]+a.val[m03]*b.val[m32];
        a.val[m30]=a.val[m00]*b.val[m03]+a.val[m01]*b.val[m13]+a.val[m02]*b.val[m23]+a.val[m03]*b.val[m33];
        a.val[m01]=a.val[m10]*b.val[m00]+a.val[m11]*b.val[m10]+a.val[m12]*b.val[m20]+a.val[m13]*b.val[m30];
        a.val[m11]=a.val[m10]*b.val[m01]+a.val[m11]*b.val[m11]+a.val[m12]*b.val[m21]+a.val[m13]*b.val[m31];
        a.val[m21]=a.val[m10]*b.val[m02]+a.val[m11]*b.val[m12]+a.val[m12]*b.val[m22]+a.val[m13]*b.val[m32];
        a.val[m31]=a.val[m10]*b.val[m03]+a.val[m11]*b.val[m13]+a.val[m12]*b.val[m23]+a.val[m13]*b.val[m33];
        a.val[m02]=a.val[m20]*b.val[m00]+a.val[m21]*b.val[m10]+a.val[m22]*b.val[m20]+a.val[m23]*b.val[m30];
        a.val[m12]=a.val[m20]*b.val[m01]+a.val[m21]*b.val[m11]+a.val[m22]*b.val[m21]+a.val[m23]*b.val[m31];
        a.val[m22]=a.val[m20]*b.val[m02]+a.val[m21]*b.val[m12]+a.val[m22]*b.val[m22]+a.val[m23]*b.val[m32];
        a.val[m32]=a.val[m20]*b.val[m03]+a.val[m21]*b.val[m13]+a.val[m22]*b.val[m23]+a.val[m23]*b.val[m33];
        a.val[m03]=a.val[m30]*b.val[m00]+a.val[m31]*b.val[m10]+a.val[m32]*b.val[m20]+a.val[m33]*b.val[m30];
        a.val[m13]=a.val[m30]*b.val[m01]+a.val[m31]*b.val[m11]+a.val[m32]*b.val[m21]+a.val[m33]*b.val[m31];
        a.val[m23]=a.val[m30]*b.val[m02]+a.val[m31]*b.val[m12]+a.val[m32]*b.val[m22]+a.val[m33]*b.val[m32];
        a.val[m33]=a.val[m30]*b.val[m03]+a.val[m31]*b.val[m13]+a.val[m32]*b.val[m23]+a.val[m33]*b.val[m33];
    }

    public Matrix4 mul(Matrix4 a){
        val[m00]=val[m00]*a.val[m00]+val[m01]*a.val[m10]+val[m02]*a.val[m20]+a.val[m03]*a.val[m30];
        val[m10]=val[m00]*a.val[m01]+val[m01]*a.val[m11]+val[m02]*a.val[m21]+a.val[m03]*a.val[m31];
        val[m20]=val[m00]*a.val[m02]+val[m01]*a.val[m12]+val[m02]*a.val[m22]+a.val[m03]*a.val[m32];
        val[m30]=val[m00]*a.val[m03]+val[m01]*a.val[m13]+val[m02]*a.val[m23]+a.val[m03]*a.val[m33];
        val[m01]=val[m10]*a.val[m00]+val[m11]*a.val[m10]+val[m12]*a.val[m20]+a.val[m13]*a.val[m30];
        val[m11]=val[m10]*a.val[m01]+val[m11]*a.val[m11]+val[m12]*a.val[m21]+a.val[m13]*a.val[m31];
        val[m21]=val[m10]*a.val[m02]+val[m11]*a.val[m12]+val[m12]*a.val[m22]+a.val[m13]*a.val[m32];
        val[m31]=val[m10]*a.val[m03]+val[m11]*a.val[m13]+val[m12]*a.val[m23]+a.val[m13]*a.val[m33];
        val[m02]=val[m20]*a.val[m00]+val[m21]*a.val[m10]+val[m22]*a.val[m20]+a.val[m23]*a.val[m30];
        val[m12]=val[m20]*a.val[m01]+val[m21]*a.val[m11]+val[m22]*a.val[m21]+a.val[m23]*a.val[m31];
        val[m22]=val[m20]*a.val[m02]+val[m21]*a.val[m12]+val[m22]*a.val[m22]+a.val[m23]*a.val[m32];
        val[m32]=val[m20]*a.val[m03]+val[m21]*a.val[m13]+val[m22]*a.val[m23]+a.val[m23]*a.val[m33];
        val[m03]=val[m30]*a.val[m00]+val[m31]*a.val[m10]+val[m32]*a.val[m20]+a.val[m33]*a.val[m30];
        val[m13]=val[m30]*a.val[m01]+val[m31]*a.val[m11]+val[m32]*a.val[m21]+a.val[m33]*a.val[m31];
        val[m23]=val[m30]*a.val[m02]+val[m31]*a.val[m12]+val[m32]*a.val[m22]+a.val[m33]*a.val[m32];
        val[m33]=val[m30]*a.val[m03]+val[m31]*a.val[m13]+val[m32]*a.val[m23]+a.val[m33]*a.val[m33];

        return this;
    }

    public Matrix4 translate(float x,float y,float z){
        val[m03]+=val[m00]*x+val[m01]*y+val[m02]*z;
        val[m13]+=val[m10]*x+val[m11]*y+val[m12]*z;
        val[m23]+=val[m20]*x+val[m21]*y+val[m22]*z;
        val[m33]+=val[m30]*x+val[m31]*y+val[m32]*z;

        return this;
    }

    public Matrix4 translate(Vector3 vector3){
        val[m03]+=val[m00]*vector3.x+val[m01]*vector3.y+val[m02]*vector3.z;
        val[m13]+=val[m10]*vector3.x+val[m11]*vector3.y+val[m12]*vector3.z;
        val[m23]+=val[m20]*vector3.x+val[m21]*vector3.y+val[m22]*vector3.z;
        val[m33]+=val[m30]*vector3.x+val[m31]*vector3.y+val[m32]*vector3.z;

        return this;
    }

    public Matrix4 setTranslation(float x,float y,float z){
        val[m03]=x;
        val[m13]=y;
        val[m23]=z;

        return this;
    }

    public Matrix4 setTranslation(Vector3 vector3){
        val[m03]=vector3.x;
        val[m13]=vector3.y;
        val[m23]=vector3.z;

        return this;
    }

    /*public Matrix4 rotate(float angle,float x,float y,float z){
        float cos=(float)Math.cos(Math.toRadians(angle));
        float sin=Maths.sinFromCos(cos);
        float C=1-cos;

        val[m00]=x*x*C+cos;
        val[m10]=x*y*C-z*sin;
        val[m20]=x*z*C+y*sin;
        val[m01]=y*x*C+z*sin;
        val[m11]=y*y*C+cos;
        val[m21]=y*z*C-x*sin;
        val[m02]=z*x*C-y*sin;
        val[m12]=z*y*C+x*sin;
        val[m22]=z*z*C+cos;

        return this;
    }*/

    public Matrix4 rotate(float angle,float x,float y,float z){
        float cos=(float)Math.cos(Math.toRadians(angle));
        float sin=Maths.sinFromCos(cos);
        float C=1-cos;

        Matrix4 rotated=new Matrix4();
        rotated.val[m00]=x*x*C+cos;
        rotated.val[m10]=x*y*C-z*sin;
        rotated.val[m20]=x*z*C+y*sin;
        rotated.val[m01]=y*x*C+z*sin;
        rotated.val[m11]=y*y*C+cos;
        rotated.val[m21]=y*z*C-x*sin;
        rotated.val[m02]=z*x*C-y*sin;
        rotated.val[m12]=z*y*C+x*sin;
        rotated.val[m22]=z*z*C+cos;

        mul(rotated);
        return this;
    }

    public Matrix4 rotate(float angle,Vector3 axis){
        float cos=(float)Math.cos(Math.toRadians(angle));
        float sin=Maths.sinFromCos(cos);
        float C=1-cos;

        val[m00]=axis.x*axis.x*C+cos;
        val[m10]=axis.x*axis.y*C-axis.z*sin;
        val[m20]=axis.x*axis.z*C+axis.y*sin;
        val[m01]=axis.y*axis.x*C+axis.z*sin;
        val[m11]=axis.y*axis.y*C+cos;
        val[m21]=axis.y*axis.z*C-axis.x*sin;
        val[m02]=axis.z*axis.x*C-axis.y*sin;
        val[m12]=axis.z*axis.y*C+axis.x*sin;
        val[m22]=axis.z*axis.z*C+cos;

        return this;
    }

    public Matrix4 scale(float x,float y,float z){
        val[m00]*=x;
        val[m11]*=y;
        val[m22]*=z;

        return this;
    }

    public Matrix4 scale(float xyz){
        val[m00]*=xyz;
        val[m11]*=xyz;
        val[m22]*=xyz;

        return this;
    }

    public Matrix4 scale(Vector3 scale){
        val[m00]*=scale.x;
        val[m11]*=scale.y;
        val[m22]*=scale.z;

        return this;
    }

    public Matrix4 rotateX(float deg){
        float cos=(float)Math.cos(deg*Maths.toRadians);
        float sin=Maths.sinFromCos(cos);

        val[m11]*=cos;
        val[m21]*=-sin;
        val[m12]*=sin;
        val[m22]*=cos;

        return this;
    }

    public Matrix4 rotateY(float deg){
        float cos=(float)Math.cos(deg*Maths.toRadians);
        float sin=Maths.sinFromCos(cos);

        val[m00]*=cos;
        val[m20]*=-sin;
        val[m02]*=sin;
        val[m22]*=cos;

        return this;
    }

    public Matrix4 rotateZ(float deg){
        float cos=(float)Math.cos(deg*Maths.toRadians);
        float sin=Maths.sinFromCos(cos);

        val[m00]*=cos;
        val[m10]*=-sin;
        val[m01]*=sin;
        val[m11]*=cos;

        return this;
    }

    /*public Matrix4 rotate(float x,float y,float z,float deg){
        if(deg==0)
            return this;
        return rotate(new Quaternion().setFromAxis(x,y,z,deg));
    }

    public Matrix4 rotateRad(float x,float y,float z,float rad){
        if(rad==0)
            return this;
        return rotate(new Quaternion().setFromAxisRad(x,y,z,rad));
    }

    public Matrix4 rotate(float yaw,float pitch,float roll){
        if(yaw==0 && pitch==0 && roll==0)
            return this;
        return rotate(new Quaternion().setEulerAngles(yaw,pitch,roll));
    }

    public Matrix4 rotate(EulerAngle eulerAngles){
        if(eulerAngles.getYaw()==0 && eulerAngles.getPitch()==0 && eulerAngles.getRoll()==0)
            return this;
        return rotate(new Quaternion().setEulerAngles(eulerAngles));
    }

    public Matrix4 rotate(Quaternion rotation){
        float xx=rotation.x*rotation.x;
        float xy=rotation.x*rotation.y;
        float xz=rotation.x*rotation.z;
        float xw=rotation.x*rotation.w;
        float yy=rotation.y*rotation.y;
        float yz=rotation.y*rotation.z;
        float yw=rotation.y*rotation.w;
        float zz=rotation.z*rotation.z;
        float zw=rotation.z*rotation.w;

        float r00=1-2*(yy+zz);
        float r01=2*(xy-zw);
        float r02=2*(xz+yw);
        float r10=2*(xy+zw);
        float r11=1-2*(xx+zz);
        float r12=2*(yz-xw);
        float r20=2*(xz-yw);
        float r21=2*(yz+xw);
        float r22=1-2*(xx+yy);

        val[m00]=val[m00]*r00+val[m01]*r10+val[m02]*r20;
        val[m01]=val[m00]*r01+val[m01]*r11+val[m02]*r21;
        val[m02]=val[m00]*r02+val[m01]*r12+val[m02]*r22;

        val[m10]=val[m10]*r00+val[m11]*r10+val[m12]*r20;
        val[m11]=val[m10]*r01+val[m11]*r11+val[m12]*r21;
        val[m12]=val[m10]*r02+val[m11]*r12+val[m12]*r22;

        val[m20]=val[m20]*r00+val[m21]*r10+val[m22]*r20;
        val[m21]=val[m20]*r01+val[m21]*r11+val[m22]*r21;
        val[m22]=val[m20]*r02+val[m21]*r12+val[m22]*r22;

        val[m30]=val[m30]*r00+val[m31]*r10+val[m32]*r20;
        val[m31]=val[m30]*r01+val[m31]*r11+val[m32]*r21;
        val[m32]=val[m30]*r02+val[m31]*r12+val[m32]*r22;

        return this;
    }*/


}
