package engine.maths;

import engine.maths.vectors.Vector3;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Matrix4f{

    public float m00,m10,m20,m30;
    public float m01,m11,m21,m31;
    public float m02,m12,m22,m32;
    public float m03,m13,m23,m33;

    public Matrix4f(){
        m00=1.0f;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=1.0f;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=1.0f;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
    }

    public Matrix4f(Matrix4f mat){
        m00=mat.m00;
        m01=mat.m01;
        m02=mat.m02;
        m03=mat.m03;
        m10=mat.m10;
        m11=mat.m11;
        m12=mat.m12;
        m13=mat.m13;
        m20=mat.m20;
        m21=mat.m21;
        m22=mat.m22;
        m23=mat.m23;
        m30=mat.m30;
        m31=mat.m31;
        m32=mat.m32;
        m33=mat.m33;
    }

    public Matrix4f(float m00,float m01,float m02,float m03,float m10,float m11,float m12,float m13,float m20,float m21,float m22,float m23,float m30,float m31,float m32,float m33){
        this.m00=m00;
        this.m01=m01;
        this.m02=m02;
        this.m03=m03;
        this.m10=m10;
        this.m11=m11;
        this.m12=m12;
        this.m13=m13;
        this.m20=m20;
        this.m21=m21;
        this.m22=m22;
        this.m23=m23;
        this.m30=m30;
        this.m31=m31;
        this.m32=m32;
        this.m33=m33;
    }


    public Matrix4f(FloatBuffer buffer){
        int pos=buffer.position();
        m00=buffer.get(pos);
        m01=buffer.get(pos+1);
        m02=buffer.get(pos+2);
        m03=buffer.get(pos+3);
        m10=buffer.get(pos+4);
        m11=buffer.get(pos+5);
        m12=buffer.get(pos+6);
        m13=buffer.get(pos+7);
        m20=buffer.get(pos+8);
        m21=buffer.get(pos+9);
        m22=buffer.get(pos+10);
        m23=buffer.get(pos+11);
        m30=buffer.get(pos+12);
        m31=buffer.get(pos+13);
        m32=buffer.get(pos+14);
        m33=buffer.get(pos+15);
    }

    public Matrix4f identity(){
        m00=1.0f;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=1.0f;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=1.0f;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f set(Matrix4f m){
        m00=m.m00;
        m01=m.m01;
        m02=m.m02;
        m03=m.m03;
        m10=m.m10;
        m11=m.m11;
        m12=m.m12;
        m13=m.m13;
        m20=m.m20;
        m21=m.m21;
        m22=m.m22;
        m23=m.m23;
        m30=m.m30;
        m31=m.m31;
        m32=m.m32;
        m33=m.m33;
        return this;
    }

    public Matrix4f set3x3(Matrix4f mat){
        m00=mat.m00;
        m01=mat.m01;
        m02=mat.m02;
        m03=0.0f;
        m10=mat.m10;
        m11=mat.m11;
        m12=mat.m12;
        m13=0.0f;
        m20=mat.m20;
        m21=mat.m21;
        m22=mat.m22;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f mul(Matrix4f right){
        return new Matrix4f(
                m00*right.m00+m10*right.m01+m20*right.m02+m30*right.m03,
                m01*right.m00+m11*right.m01+m21*right.m02+m31*right.m03,
                m02*right.m00+m12*right.m01+m22*right.m02+m32*right.m03,
                m03*right.m00+m13*right.m01+m23*right.m02+m33*right.m03,
                m00*right.m10+m10*right.m11+m20*right.m12+m30*right.m13,
                m01*right.m10+m11*right.m11+m21*right.m12+m31*right.m13,
                m02*right.m10+m12*right.m11+m22*right.m12+m32*right.m13,
                m03*right.m10+m13*right.m11+m23*right.m12+m33*right.m13,
                m00*right.m20+m10*right.m21+m20*right.m22+m30*right.m23,
                m01*right.m20+m11*right.m21+m21*right.m22+m31*right.m23,
                m02*right.m20+m12*right.m21+m22*right.m22+m32*right.m23,
                m03*right.m20+m13*right.m21+m23*right.m22+m33*right.m23,
                m00*right.m30+m10*right.m31+m20*right.m32+m30*right.m33,
                m01*right.m30+m11*right.m31+m21*right.m32+m31*right.m33,
                m02*right.m30+m12*right.m31+m22*right.m32+m32*right.m33,
                m03*right.m30+m13*right.m31+m23*right.m32+m33*right.m33
        );
    }

    public Matrix4f add(Matrix4f other){
        return new Matrix4f(
                m00+other.m00,
                m01+other.m01,
                m02+other.m02,
                m03+other.m03,
                m10+other.m10,
                m11+other.m11,
                m12+other.m12,
                m13+other.m13,
                m20+other.m20,
                m21+other.m21,
                m22+other.m22,
                m23+other.m23,
                m30+other.m30,
                m31+other.m31,
                m32+other.m32,
                m33+other.m33
        );
    }

    public Matrix4f sub(Matrix4f subtrahend){
        return new Matrix4f(
                m00-subtrahend.m00,
                m01-subtrahend.m01,
                m02-subtrahend.m02,
                m03-subtrahend.m03,
                m10-subtrahend.m10,
                m11-subtrahend.m11,
                m12-subtrahend.m12,
                m13-subtrahend.m13,
                m20-subtrahend.m20,
                m21-subtrahend.m21,
                m22-subtrahend.m22,
                m23-subtrahend.m23,
                m30-subtrahend.m30,
                m31-subtrahend.m31,
                m32-subtrahend.m32,
                m33-subtrahend.m33
        );
    }

    public Matrix4f mulComponentWise(Matrix4f other){
        return new Matrix4f(
            m00*other.m00,
            m01*other.m01,
            m02*other.m02,
            m03*other.m03,
            m10*other.m10,
            m11*other.m11,
            m12*other.m12,
            m13*other.m13,
            m20*other.m20,
            m21*other.m21,
            m22*other.m22,
            m23*other.m23,
            m30*other.m30,
            m31*other.m31,
            m32*other.m32,
            m33*other.m33
        );
    }

    public Matrix4f set(float m00,float m01,float m02,float m03,float m10,float m11,float m12,float m13,float m20,float m21,float m22,float m23,float m30,float m31,float m32,float m33){
        this.m00=m00;
        this.m01=m01;
        this.m02=m02;
        this.m03=m03;
        this.m10=m10;
        this.m11=m11;
        this.m12=m12;
        this.m13=m13;
        this.m20=m20;
        this.m21=m21;
        this.m22=m22;
        this.m23=m23;
        this.m30=m30;
        this.m31=m31;
        this.m32=m32;
        this.m33=m33;
        return this;
    }

    public Matrix4f set(float m[],int off){
        m00=m[off+0];
        m01=m[off+1];
        m02=m[off+2];
        m03=m[off+3];
        m10=m[off+4];
        m11=m[off+5];
        m12=m[off+6];
        m13=m[off+7];
        m20=m[off+8];
        m21=m[off+9];
        m22=m[off+10];
        m23=m[off+11];
        m30=m[off+12];
        m31=m[off+13];
        m32=m[off+14];
        m33=m[off+15];
        return this;
    }

    public Matrix4f set(FloatBuffer buffer){
        int pos=buffer.position();
        m00=buffer.get(pos);
        m01=buffer.get(pos+1);
        m02=buffer.get(pos+2);
        m03=buffer.get(pos+3);
        m10=buffer.get(pos+4);
        m11=buffer.get(pos+5);
        m12=buffer.get(pos+6);
        m13=buffer.get(pos+7);
        m20=buffer.get(pos+8);
        m21=buffer.get(pos+9);
        m22=buffer.get(pos+10);
        m23=buffer.get(pos+11);
        m30=buffer.get(pos+12);
        m31=buffer.get(pos+13);
        m32=buffer.get(pos+14);
        m33=buffer.get(pos+15);
        return this;
    }

    public Matrix4f set(ByteBuffer buffer){
        int pos=buffer.position();
        m00=buffer.getFloat(pos);
        m01=buffer.getFloat(pos+4);
        m02=buffer.getFloat(pos+8);
        m03=buffer.getFloat(pos+12);
        m10=buffer.getFloat(pos+16);
        m11=buffer.getFloat(pos+20);
        m12=buffer.getFloat(pos+24);
        m13=buffer.getFloat(pos+28);
        m20=buffer.getFloat(pos+32);
        m21=buffer.getFloat(pos+36);
        m22=buffer.getFloat(pos+40);
        m23=buffer.getFloat(pos+44);
        m30=buffer.getFloat(pos+48);
        m31=buffer.getFloat(pos+52);
        m32=buffer.getFloat(pos+56);
        m33=buffer.getFloat(pos+60);
        return this;
    }

    public float determinant(){
        return (m00*m11-m01*m10)*(m22*m33-m23*m32)
                +(m02*m10-m00*m12)*(m21*m33-m23*m31)
                +(m00*m13-m03*m10)*(m21*m32-m22*m31)
                +(m01*m12-m02*m11)*(m20*m33-m23*m30)
                +(m03*m11-m01*m13)*(m20*m32-m22*m30)
                +(m02*m13-m03*m12)*(m20*m31-m21*m30);
    }

    public Matrix4f invert(Matrix4f dest){
        float a=m00*m11-m01*m10;
        float b=m00*m12-m02*m10;
        float c=m00*m13-m03*m10;
        float d=m01*m12-m02*m11;
        float e=m01*m13-m03*m11;
        float f=m02*m13-m03*m12;
        float g=m20*m31-m21*m30;
        float h=m20*m32-m22*m30;
        float i=m20*m33-m23*m30;
        float j=m21*m32-m22*m31;
        float k=m21*m33-m23*m31;
        float l=m22*m33-m23*m32;
        float det=a*l-b*k+c*j+d*i-e*h+f*g;
        det=1.0f/det;
        dest.set((m11*l-m12*k+m13*j)*det,
                (-m01*l+m02*k-m03*j)*det,
                (m31*f-m32*e+m33*d)*det,
                (-m21*f+m22*e-m23*d)*det,
                (-m10*l+m12*i-m13*h)*det,
                (m00*l-m02*i+m03*h)*det,
                (-m30*f+m32*c-m33*b)*det,
                (m20*f-m22*c+m23*b)*det,
                (m10*k-m11*i+m13*g)*det,
                (-m00*k+m01*i-m03*g)*det,
                (m30*e-m31*c+m33*a)*det,
                (-m20*e+m21*c-m23*a)*det,
                (-m10*j+m11*h-m12*g)*det,
                (m00*j-m01*h+m02*g)*det,
                (-m30*d+m31*b-m32*a)*det,
                (m20*d-m21*b+m22*a)*det);
        return dest;
    }

    public Matrix4f invert(){
        return invert(this);
    }

    public Matrix4f translation(float x,float y,float z){
        m00=1.0f;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=1.0f;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=1.0f;
        m23=0.0f;
        m30=x;
        m31=y;
        m32=z;
        m33=1.0f;
        return this;
    }

    public Matrix4f translation(Vector3 offset){
        return translation(offset.x,offset.y,offset.z);
    }

    public Matrix4f setTranslation(float x,float y,float z){
        m30=x;
        m31=y;
        m32=z;
        return this;
    }

    public Matrix4f setTranslation(Vector3 xyz){
        m30=xyz.x;
        m31=xyz.y;
        m32=xyz.z;
        return this;
    }

    public Vector3 getTranslation(Vector3 dest){
        dest.x=m30;
        dest.y=m31;
        dest.z=m32;
        return dest;
    }

    public Vector3 getScale(){
        return new Vector3(
                (float)Math.sqrt(m00*m00+m01*m01+m02*m02),
                (float)Math.sqrt(m10*m10+m11*m11+m12*m12),
                (float)Math.sqrt(m20*m20+m21*m21+m22*m22)
        );
    }

    public FloatBuffer getFloatBuffer(int index){
        FloatBuffer buffer=BufferUtils.createFloatBuffer(16);
        buffer.put(index,m00);
        buffer.put(index+1,m01);
        buffer.put(index+2,m02);
        buffer.put(index+3,m03);
        buffer.put(index+4,m10);
        buffer.put(index+5,m11);
        buffer.put(index+6,m12);
        buffer.put(index+7,m13);
        buffer.put(index+8,m20);
        buffer.put(index+9,m21);
        buffer.put(index+10,m22);
        buffer.put(index+11,m23);
        buffer.put(index+12,m30);
        buffer.put(index+13,m31);
        buffer.put(index+14,m32);
        buffer.put(index+15,m33);
        return buffer;
    }

    public ByteBuffer getByteBuffer(int index){
        ByteBuffer buffer=BufferUtils.createByteBuffer(64);
        buffer.putFloat(index,m00);
        buffer.putFloat(index+4,m01);
        buffer.putFloat(index+8,m02);
        buffer.putFloat(index+12,m03);
        buffer.putFloat(index+16,m10);
        buffer.putFloat(index+20,m11);
        buffer.putFloat(index+24,m12);
        buffer.putFloat(index+28,m13);
        buffer.putFloat(index+32,m20);
        buffer.putFloat(index+36,m21);
        buffer.putFloat(index+40,m22);
        buffer.putFloat(index+44,m23);
        buffer.putFloat(index+48,m30);
        buffer.putFloat(index+52,m31);
        buffer.putFloat(index+56,m32);
        buffer.putFloat(index+60,m33);
        return buffer;
    }

    public float[] getArray(){
        return new float[]{m00,m01,m02,m03,m10,m11,m12,m13,m20,m21,m22,m23,m30,m31,m32,m33};
    }

    public Matrix4f zero(){
        m00=0.0f;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=0.0f;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=0.0f;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=0.0f;
        return this;
    }

    public Matrix4f scaling(float factor){
        m00=factor;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=factor;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=factor;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f scaling(float x,float y,float z){
        m00=x;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=y;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=z;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f scaling(Vector3 xyz){
        return scaling(xyz.x,xyz.y,xyz.z);
    }

    public Matrix4f rotation(float angle,Vector3 axis){
        return rotation(angle,axis.x,axis.y,axis.z);
    }

    public Matrix4f rotation(float angle,float x,float y,float z){
        float cos=(float)Math.cos(angle);
        float sin=(float)Math.sin(angle);
        float C=1.0f-cos;
        float xy=x*y,xz=x*z,yz=y*z;
        m00=cos+x*x*C;
        m10=xy*C-z*sin;
        m20=xz*C+y*sin;
        m30=0.0f;
        m01=xy*C+z*sin;
        m11=cos+y*y*C;
        m21=yz*C-x*sin;
        m31=0.0f;
        m02=xz*C-y*sin;
        m12=yz*C+x*sin;
        m22=cos+z*z*C;
        m32=0.0f;
        m03=0.0f;
        m13=0.0f;
        m23=0.0f;
        m33=1.0f;
        return this;
    }


    public Matrix4f rotationX(float ang){
        float cos=(float)Math.cos(ang);
        float sin=(float)Math.sin(ang);
        m00=1.0f;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=cos;
        m12=sin;
        m13=0.0f;
        m20=0.0f;
        m21=-sin;
        m22=cos;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }


    public Matrix4f rotationY(float ang){
        float cos=(float)Math.cos(ang);
        float sin=(float)Math.sin(ang);
        m00=cos;
        m01=0.0f;
        m02=-sin;
        m03=0.0f;
        m10=0.0f;
        m11=1.0f;
        m12=0.0f;
        m13=0.0f;
        m20=sin;
        m21=0.0f;
        m22=cos;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f rotationZ(float ang){
        float cos=(float)Math.cos(ang);
        float sin=(float)Math.sin(ang);
        m00=cos;
        m01=sin;
        m02=0.0f;
        m03=0.0f;
        m10=-sin;
        m11=cos;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=1.0f;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }


    public Matrix4f rotationXYZ(float angleX,float angleY,float angleZ){
        float cosX=(float)Math.cos(angleX);
        float sinX=(float)Math.sin(angleX);
        float cosY=(float)Math.cos(angleY);
        float sinY=(float)Math.sin(angleY);
        float cosZ=(float)Math.cos(angleZ);
        float sinZ=(float)Math.sin(angleZ);
        float m_sinX=-sinX;
        float m_sinY=-sinY;
        float m_sinZ=-sinZ;
        // rotateX
        float nm11=cosX;
        float nm12=sinX;
        float nm21=m_sinX;
        float nm22=cosX;
        // rotateY
        float nm00=cosY;
        float nm01=nm21*m_sinY;
        float nm02=nm22*m_sinY;
        m20=sinY;
        m21=nm21*cosY;
        m22=nm22*cosY;
        m23=0.0f;
        // rotateZ
        m00=nm00*cosZ;
        m01=nm01*cosZ+nm11*sinZ;
        m02=nm02*cosZ+nm12*sinZ;
        m03=0.0f;
        m10=nm00*m_sinZ;
        m11=nm01*m_sinZ+nm11*cosZ;
        m12=nm02*m_sinZ+nm12*cosZ;
        m13=0.0f;
        // set last column to identity
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f rotationZYX(float angleZ,float angleY,float angleX){
        float cosZ=(float)Math.cos(angleZ);
        float sinZ=(float)Math.sin(angleZ);
        float cosY=(float)Math.cos(angleY);
        float sinY=(float)Math.sin(angleY);
        float cosX=(float)Math.cos(angleX);
        float sinX=(float)Math.sin(angleX);
        float m_sinZ=-sinZ;
        float m_sinY=-sinY;
        float m_sinX=-sinX;
        // rotateZ
        float nm00=cosZ;
        float nm01=sinZ;
        float nm10=m_sinZ;
        float nm11=cosZ;
        // rotateY
        float nm20=nm00*sinY;
        float nm21=nm01*sinY;
        float nm22=cosY;
        m00=nm00*cosY;
        m01=nm01*cosY;
        m02=m_sinY;
        m03=0.0f;
        // rotateX
        m10=nm10*cosX+nm20*sinX;
        m11=nm11*cosX+nm21*sinX;
        m12=nm22*sinX;
        m13=0.0f;
        m20=nm10*m_sinX+nm20*cosX;
        m21=nm11*m_sinX+nm21*cosX;
        m22=nm22*cosX;
        m23=0.0f;
        // set last column to identity
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f rotationYXZ(float angleY,float angleX,float angleZ){
        float cosY=(float) Math.cos(angleY);
        float sinY=(float) Math.sin(angleY);
        float cosX=(float) Math.cos(angleX);
        float sinX=(float) Math.sin(angleX);
        float cosZ=(float) Math.cos(angleZ);
        float sinZ=(float) Math.sin(angleZ);
        float m_sinY=-sinY;
        float m_sinX=-sinX;
        float m_sinZ=-sinZ;
        // rotateY
        float nm00=cosY;
        float nm02=m_sinY;
        float nm20=sinY;
        float nm22=cosY;
        // rotateX
        float nm10=nm20*sinX;
        float nm11=cosX;
        float nm12=nm22*sinX;
        m20=nm20*cosX;
        m21=m_sinX;
        m22=nm22*cosX;
        m23=0.0f;
        // rotateZ
        m00=nm00*cosZ+nm10*sinZ;
        m01=nm11*sinZ;
        m02=nm02*cosZ+nm12*sinZ;
        m03=0.0f;
        m10=nm00*m_sinZ+nm10*cosZ;
        m11=nm11*cosZ;
        m12=nm02*m_sinZ+nm12*cosZ;
        m13=0.0f;
        // set last column to identity
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f setRotationXYZ(float angleX,float angleY,float angleZ){
        float cosX=(float)Math.cos(angleX);
        float sinX=(float)Math.sin(angleX);
        float cosY=(float)Math.cos(angleY);
        float sinY=(float)Math.sin(angleY);
        float cosZ=(float)Math.cos(angleZ);
        float sinZ=(float)Math.sin(angleZ);
        float m_sinX=-sinX;
        float m_sinY=-sinY;
        float m_sinZ=-sinZ;
        // rotateX
        float nm11=cosX;
        float nm12=sinX;
        float nm21=m_sinX;
        float nm22=cosX;
        // rotateY
        float nm00=cosY;
        float nm01=nm21*m_sinY;
        float nm02=nm22*m_sinY;
        m20=sinY;
        m21=nm21*cosY;
        m22=nm22*cosY;
        // rotateZ
        m00=nm00*cosZ;
        m01=nm01*cosZ+nm11*sinZ;
        m02=nm02*cosZ+nm12*sinZ;
        m10=nm00*m_sinZ;
        m11=nm01*m_sinZ+nm11*cosZ;
        m12=nm02*m_sinZ+nm12*cosZ;
        return this;
    }

    public Matrix4f setRotationZYX(float angleZ,float angleY,float angleX){
        float cosZ=(float)Math.cos(angleZ);
        float sinZ=(float)Math.sin(angleZ);
        float cosY=(float)Math.cos(angleY);
        float sinY=(float)Math.sin(angleY);
        float cosX=(float)Math.cos(angleX);
        float sinX=(float)Math.sin(angleX);
        float m_sinZ=-sinZ;
        float m_sinY=-sinY;
        float m_sinX=-sinX;
        // rotateZ
        float nm00=cosZ;
        float nm01=sinZ;
        float nm10=m_sinZ;
        float nm11=cosZ;
        // rotateY
        float nm20=nm00*sinY;
        float nm21=nm01*sinY;
        float nm22=cosY;
        m00=nm00*cosY;
        m01=nm01*cosY;
        m02=m_sinY;
        // rotateX
        m10=nm10*cosX+nm20*sinX;
        m11=nm11*cosX+nm21*sinX;
        m12=nm22*sinX;
        m20=nm10*m_sinX+nm20*cosX;
        m21=nm11*m_sinX+nm21*cosX;
        m22=nm22*cosX;
        return this;
    }

    public Matrix4f setRotationYXZ(float angleY,float angleX,float angleZ){
        float cosY=(float)Math.cos(angleY);
        float sinY=(float)Math.sin(angleY);
        float cosX=(float)Math.cos(angleX);
        float sinX=(float)Math.sin(angleX);
        float cosZ=(float)Math.cos(angleZ);
        float sinZ=(float)Math.sin(angleZ);
        float m_sinY=-sinY;
        float m_sinX=-sinX;
        float m_sinZ=-sinZ;
        // rotateY
        float nm00=cosY;
        float nm02=m_sinY;
        float nm20=sinY;
        float nm22=cosY;
        // rotateX
        float nm10=nm20*sinX;
        float nm11=cosX;
        float nm12=nm22*sinX;
        m20=nm20*cosX;
        m21=m_sinX;
        m22=nm22*cosX;
        // rotateZ
        m00=nm00*cosZ+nm10*sinZ;
        m01=nm11*sinZ;
        m02=nm02*cosZ+nm12*sinZ;
        m10=nm00*m_sinZ+nm10*cosZ;
        m11=nm11*cosZ;
        m12=nm02*m_sinZ+nm12*cosZ;
        return this;
    }

    public Matrix4f translationRotateScale(float tx,float ty,float tz,float qx,float qy,float qz,float qw,float sx,float sy,float sz){
        float dqx=2.0f*qx,dqy=2.0f*qy,dqz=2.0f*qz;
        float q00=dqx*qx;
        float q11=dqy*qy;
        float q22=dqz*qz;
        float q01=dqx*qy;
        float q02=dqx*qz;
        float q03=dqx*qw;
        float q12=dqy*qz;
        float q13=dqy*qw;
        float q23=dqz*qw;
        m00=sx-(q11+q22)*sx;
        m01=(q01+q23)*sx;
        m02=(q02-q13)*sx;
        m03=0.0f;
        m10=(q01-q23)*sy;
        m11=sy-(q22+q00)*sy;
        m12=(q12+q03)*sy;
        m13=0.0f;
        m20=(q02+q13)*sz;
        m21=(q12-q03)*sz;
        m22=sz-(q11+q00)*sz;
        m23=0.0f;
        m30=tx;
        m31=ty;
        m32=tz;
        m33=1.0f;
        return this;
    }

    public Matrix4f translate(Vector3 offset){
        return translate(offset.x,offset.y,offset.z);
    }

    public Matrix4f translate(float x,float y,float z){
        // translation matrix elements:
        // m00, m11, m22, m33 = 1
        // m30 = x, m31 = y, m32 = z
        // all others = 0
        m30=m00*x+m10*y+m20*z+m30;
        m31=m01*x+m11*y+m21*z+m31;
        m32=m02*x+m12*y+m22*z+m32;
        m33=m03*x+m13*y+m23*z+m33;
        return this;
    }

    public Matrix4f ortho(float left,float right,float bottom,float top,float zNear,float zFar){
        // calculate right matrix elements
        float rm00=2.0f/(right-left);
        float rm11=2.0f/(top-bottom);
        float rm22=-2.0f/(zFar-zNear);
        float rm30=-(right+left)/(right-left);
        float rm31=-(top+bottom)/(top-bottom);
        float rm32=-(zFar+zNear)/(zFar-zNear);
        // perform optimized multiplication
        // compute the last column first, because other columns do not depend on it
        m30=m00*rm30+m10*rm31+m20*rm32+m30;
        m31=m01*rm30+m11*rm31+m21*rm32+m31;
        m32=m02*rm30+m12*rm31+m22*rm32+m32;
        m33=m03*rm30+m13*rm31+m23*rm32+m33;
        m00=m00*rm00;
        m01=m01*rm00;
        m02=m02*rm00;
        m03=m03*rm00;
        m10=m10*rm11;
        m11=m11*rm11;
        m12=m12*rm11;
        m13=m13*rm11;
        m20=m20*rm22;
        m21=m21*rm22;
        m22=m22*rm22;
        m23=m23*rm22;
        return this;
    }

    public Matrix4f setOrtho(float left,float right,float bottom,float top,float zNear,float zFar){
        m00=2.0f/(right-left);
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=2.0f/(top-bottom);
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=-2.0f/(zFar-zNear);
        m23=0.0f;
        m30=-(right+left)/(right-left);
        m31=-(top+bottom)/(top-bottom);
        m32=-(zFar+zNear)/(zFar-zNear);
        m33=1.0f;
        return this;
    }

    public Matrix4f orthoSymmetric(float width,float height,float zNear,float zFar){
        // calculate right matrix elements
        float rm00=2.0f/width;
        float rm11=2.0f/height;
        float rm22=-2.0f/(zFar-zNear);
        float rm32=-(zFar+zNear)/(zFar-zNear);
        // perform optimized multiplication
        // compute the last column first, because other columns do not depend on it
        m30=m20*rm32+m30;
        m31=m21*rm32+m31;
        m32=m22*rm32+m32;
        m33=m23*rm32+m33;
        m00=m00*rm00;
        m01=m01*rm00;
        m02=m02*rm00;
        m03=m03*rm00;
        m10=m10*rm11;
        m11=m11*rm11;
        m12=m12*rm11;
        m13=m13*rm11;
        m20=m20*rm22;
        m21=m21*rm22;
        m22=m22*rm22;
        m23=m23*rm22;
        return this;
    }

    public Matrix4f setOrthoSymmetric(float width,float height,float zNear,float zFar){
        m00=2.0f/width;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=2.0f/height;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=-2.0f/(zFar-zNear);
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=-(zFar+zNear)/(zFar-zNear);
        m33=1.0f;
        return this;
    }

    public Matrix4f ortho2D(float left,float right,float bottom,float top){
        // calculate right matrix elements
        float rm00=2.0f/(right-left);
        float rm11=2.0f/(top-bottom);
        float rm30=-(right+left)/(right-left);
        float rm31=-(top+bottom)/(top-bottom);

        // perform optimized multiplication
        // compute the last column first, because other columns do not depend on it
        m30=m00*rm30+m10*rm31+m30;
        m31=m01*rm30+m11*rm31+m31;
        m32=m02*rm30+m12*rm31+m32;
        m33=m03*rm30+m13*rm31+m33;
        m00=m00*rm00;
        m01=m01*rm00;
        m02=m02*rm00;
        m03=m03*rm00;
        m10=m10*rm11;
        m11=m11*rm11;
        m12=m12*rm11;
        m13=m13*rm11;
        m20=-m20;
        m21=-m21;
        m22=-m22;
        m23=-m23;

        return this;
    }

    public Matrix4f setOrtho2D(float left,float right,float bottom,float top){
        m00=2.0f/(right-left);
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=2.0f/(top-bottom);
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=-1.0f;
        m23=0.0f;
        m30=-(right+left)/(right-left);
        m31=-(top+bottom)/(top-bottom);
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f lookAlong(float dirX,float dirY,float dirZ,float upX,float upY,float upZ){
        // Normalize direction
        float invDirLength=1.0f/(float)Math.sqrt(dirX*dirX+dirY*dirY+dirZ*dirZ);
        float dirnX=dirX*invDirLength;
        float dirnY=dirY*invDirLength;
        float dirnZ=dirZ*invDirLength;
        // right = direction x up
        float rightX,rightY,rightZ;
        rightX=dirnY*upZ-dirnZ*upY;
        rightY=dirnZ*upX-dirnX*upZ;
        rightZ=dirnX*upY-dirnY*upX;
        // normalize right
        float invRightLength=1.0f/(float)Math.sqrt(rightX*rightX+rightY*rightY+rightZ*rightZ);
        rightX*=invRightLength;
        rightY*=invRightLength;
        rightZ*=invRightLength;
        // up = right x direction
        float upnX=rightY*dirnZ-rightZ*dirnY;
        float upnY=rightZ*dirnX-rightX*dirnZ;
        float upnZ=rightX*dirnY-rightY*dirnX;
        // calculate right matrix elements
        float rm00=rightX;
        float rm01=upnX;
        float rm02=-dirnX;
        float rm10=rightY;
        float rm11=upnY;
        float rm12=-dirnY;
        float rm20=rightZ;
        float rm21=upnZ;
        float rm22=-dirnZ;
        // perform optimized matrix multiplication
        // introduce temporaries for dependent results
        float nm00=m00*rm00+m10*rm01+m20*rm02;
        float nm01=m01*rm00+m11*rm01+m21*rm02;
        float nm02=m02*rm00+m12*rm01+m22*rm02;
        float nm03=m03*rm00+m13*rm01+m23*rm02;
        float nm10=m00*rm10+m10*rm11+m20*rm12;
        float nm11=m01*rm10+m11*rm11+m21*rm12;
        float nm12=m02*rm10+m12*rm11+m22*rm12;
        float nm13=m03*rm10+m13*rm11+m23*rm12;
        m20=m00*rm20+m10*rm21+m20*rm22;
        m21=m01*rm20+m11*rm21+m21*rm22;
        m22=m02*rm20+m12*rm21+m22*rm22;
        m23=m03*rm20+m13*rm21+m23*rm22;
        // set the rest of the matrix elements
        m00=nm00;
        m01=nm01;
        m02=nm02;
        m03=nm03;
        m10=nm10;
        m11=nm11;
        m12=nm12;
        m13=nm13;
        return this;
    }

    public Matrix4f setLookAlong(float dirX,float dirY,float dirZ,float upX,float upY,float upZ){
        // Normalize direction
        float invDirLength=1.0f/(float)Math.sqrt(dirX*dirX+dirY*dirY+dirZ*dirZ);
        float dirnX=dirX*invDirLength;
        float dirnY=dirY*invDirLength;
        float dirnZ=dirZ*invDirLength;
        // right = direction x up
        float rightX,rightY,rightZ;
        rightX=dirnY*upZ-dirnZ*upY;
        rightY=dirnZ*upX-dirnX*upZ;
        rightZ=dirnX*upY-dirnY*upX;
        // normalize right
        float invRightLength=1.0f/(float)Math.sqrt(rightX*rightX+rightY*rightY+rightZ*rightZ);
        rightX*=invRightLength;
        rightY*=invRightLength;
        rightZ*=invRightLength;
        // up = right x direction
        float upnX=rightY*dirnZ-rightZ*dirnY;
        float upnY=rightZ*dirnX-rightX*dirnZ;
        float upnZ=rightX*dirnY-rightY*dirnX;
        m00=rightX;
        m01=upnX;
        m02=-dirnX;
        m03=0.0f;
        m10=rightY;
        m11=upnY;
        m12=-dirnY;
        m13=0.0f;
        m20=rightZ;
        m21=upnZ;
        m22=-dirnZ;
        m23=0.0f;
        m30=0.0f;
        m31=0.0f;
        m32=0.0f;
        m33=1.0f;
        return this;
    }

    public Matrix4f setLookAt(float eyeX,float eyeY,float eyeZ,float centerX,float centerY,float centerZ,float upX,float upY,float upZ){
        // Compute direction from position to lookAt
        float dirX,dirY,dirZ;
        dirX=centerX-eyeX;
        dirY=centerY-eyeY;
        dirZ=centerZ-eyeZ;
        // Normalize direction
        float invDirLength=1.0f/(float)Math.sqrt((centerX-eyeX)*(centerX-eyeX)+(centerY-eyeY)*(centerY-eyeY)+(centerZ-eyeZ)*(centerZ-eyeZ));
        dirX*=invDirLength;
        dirY*=invDirLength;
        dirZ*=invDirLength;
        // right = direction x up
        float rightX,rightY,rightZ;
        rightX=dirY*upZ-dirZ*upY;
        rightY=dirZ*upX-dirX*upZ;
        rightZ=dirX*upY-dirY*upX;
        // normalize right
        float invRightLength=1.0f/(float)Math.sqrt(rightX*rightX+rightY*rightY+rightZ*rightZ);
        rightX*=invRightLength;
        rightY*=invRightLength;
        rightZ*=invRightLength;
        // up = right x direction
        float upnX=rightY*dirZ-rightZ*dirY;
        float upnY=rightZ*dirX-rightX*dirZ;
        float upnZ=rightX*dirY-rightY*dirX;
        m00=rightX;
        m01=upnX;
        m02=-dirX;
        m03=0.0f;
        m10=rightY;
        m11=upnY;
        m12=-dirY;
        m13=0.0f;
        m20=rightZ;
        m21=upnZ;
        m22=-dirZ;
        m23=0.0f;
        m30=-rightX*eyeX-rightY*eyeY-rightZ*eyeZ;
        m31=-upnX*eyeX-upnY*eyeY-upnZ*eyeZ;
        m32=dirX*eyeX+dirY*eyeY+dirZ*eyeZ;
        m33=1.0f;

        return this;
    }

    public Matrix4f lookAt(Vector3 eye,Vector3 center,Vector3 up){
        return lookAt(eye.x,eye.y,eye.z,center.x,center.y,center.z,up.x,up.y,up.z);
    }

    public Matrix4f lookAt(float eyeX,float eyeY,float eyeZ,float centerX,float centerY,float centerZ,float upX,float upY,float upZ){
        // Compute direction from position to lookAt
        float dirX,dirY,dirZ;
        dirX=centerX-eyeX;
        dirY=centerY-eyeY;
        dirZ=centerZ-eyeZ;
        // Normalize direction
        float invDirLength=1.0f/(float)Math.sqrt((eyeX-centerX)*(eyeX-centerX)+(eyeY-centerY)*(eyeY-centerY)+(eyeZ-centerZ)*(eyeZ-centerZ));
        dirX*=invDirLength;
        dirY*=invDirLength;
        dirZ*=invDirLength;
        // right = direction x up
        float rightX,rightY,rightZ;
        rightX=dirY*upZ-dirZ*upY;
        rightY=dirZ*upX-dirX*upZ;
        rightZ=dirX*upY-dirY*upX;
        // normalize right
        float invRightLength=1.0f/(float)Math.sqrt(rightX*rightX+rightY*rightY+rightZ*rightZ);
        rightX*=invRightLength;
        rightY*=invRightLength;
        rightZ*=invRightLength;
        // up = right x direction
        float upnX=rightY*dirZ-rightZ*dirY;
        float upnY=rightZ*dirX-rightX*dirZ;
        float upnZ=rightX*dirY-rightY*dirX;
        // calculate right matrix elements
        float rm00=rightX;
        float rm01=upnX;
        float rm02=-dirX;
        float rm10=rightY;
        float rm11=upnY;
        float rm12=-dirY;
        float rm20=rightZ;
        float rm21=upnZ;
        float rm22=-dirZ;
        float rm30=-rightX*eyeX-rightY*eyeY-rightZ*eyeZ;
        float rm31=-upnX*eyeX-upnY*eyeY-upnZ*eyeZ;
        float rm32=dirX*eyeX+dirY*eyeY+dirZ*eyeZ;
        // perform optimized matrix multiplication
        // compute last column first, because others do not depend on it
        m30=m00*rm30+m10*rm31+m20*rm32+m30;
        m31=m01*rm30+m11*rm31+m21*rm32+m31;
        m32=m02*rm30+m12*rm31+m22*rm32+m32;
        m33=m03*rm30+m13*rm31+m23*rm32+m33;
        // introduce temporaries for dependent results
        float nm00=m00*rm00+m10*rm01+m20*rm02;
        float nm01=m01*rm00+m11*rm01+m21*rm02;
        float nm02=m02*rm00+m12*rm01+m22*rm02;
        float nm03=m03*rm00+m13*rm01+m23*rm02;
        float nm10=m00*rm10+m10*rm11+m20*rm12;
        float nm11=m01*rm10+m11*rm11+m21*rm12;
        float nm12=m02*rm10+m12*rm11+m22*rm12;
        float nm13=m03*rm10+m13*rm11+m23*rm12;
        m20=m00*rm20+m10*rm21+m20*rm22;
        m21=m01*rm20+m11*rm21+m21*rm22;
        m22=m02*rm20+m12*rm21+m22*rm22;
        m23=m03*rm20+m13*rm21+m23*rm22;
        // set the rest of the matrix elements
        m00=nm00;
        m01=nm01;
        m02=nm02;
        m03=nm03;
        m10=nm10;
        m11=nm11;
        m12=nm12;
        m13=nm13;
        return this;
    }

    public Matrix4f perspective(float fovy,float aspect,float zNear,float zFar){
        float h=(float) Math.tan(fovy*0.5f)*zNear;
        float w=h*aspect;
        // calculate right matrix elements
        float rm00=zNear/w;
        float rm11=zNear/h;
        float rm22=-(zFar+zNear)/(zFar-zNear);
        float rm32=-2.0f*zFar*zNear/(zFar-zNear);
        // perform optimized matrix multiplication
        float nm20=m20*rm22-m30;
        float nm21=m21*rm22-m31;
        float nm22=m22*rm22-m32;
        float nm23=m23*rm22-m33;
        m00=m00*rm00;
        m01=m01*rm00;
        m02=m02*rm00;
        m03=m03*rm00;
        m10=m10*rm11;
        m11=m11*rm11;
        m12=m12*rm11;
        m13=m13*rm11;
        m30=m20*rm32;
        m31=m21*rm32;
        m32=m22*rm32;
        m33=m23*rm32;
        m20=nm20;
        m21=nm21;
        m22=nm22;
        m23=nm23;
        return this;
    }

    public Matrix4f setPerspective(float fovy,float aspect,float zNear,float zFar){
        float h=(float)Math.tan(fovy*0.5f)*zNear;
        float w=h*aspect;
        m00=zNear/w;
        m01=0.0f;
        m02=0.0f;
        m03=0.0f;
        m10=0.0f;
        m11=zNear/h;
        m12=0.0f;
        m13=0.0f;
        m20=0.0f;
        m21=0.0f;
        m22=-(zFar+zNear)/(zFar-zNear);
        m23=-1.0f;
        m30=0.0f;
        m31=0.0f;
        m32=-2.0f*zFar*zNear/(zFar-zNear);
        m33=0.0f;
        return this;
    }


}
