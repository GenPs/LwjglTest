package engine.maths;

import engine.maths.vectors.*;

import java.awt.*;

public class Maths{


    static public final float FLOAT_ROUNDING_ERROR=0.000001f; // 32 bits
    static public final float PI=(float)Math.PI;
    static public final float PI2=PI*2;
    static public final float toDegrees=180f/PI;
    static public final float toRadians=PI/180f;


    public static float sinFromCos(float cos){
        return (float)Math.sqrt(1-cos*cos);
    }

    public static float cosFromSin(float sin){
        return (float)Math.sqrt(1-sin*sin);
    }

    public static Vector2 normDegAngle(float a){
        return new Vector2((float)Math.cos(a*toRadians),(float)Math.sin(a*toRadians));
    }

    public static float angle(float x,float y){
        return (float)Math.atan2(y,x)*toDegrees+180;
    }

    public static float angleBetweenVectors(Vector2 v1,Vector2 v2){
        return angle(v1.x-v2.x,v1.y-v2.y);
    }

    public static int module(int i){
        return i<0 ? -i : i;
    }

    public static float module(float i){
        return i<0 ? -i : i;
    }

    public static double module(double i){
        return i<0 ? -i : i;
    }

    public static boolean overlaps(Rectangle r1,Rectangle r2){
        return r1.x<r2.x+r2.width && r1.x+r1.width>r2.x && r1.y<r2.y+r2.height && r1.y+r1.height>r2.y;
    }

    public static boolean overlaps(float x1,float y1,float w1,float h1,float x2,float y2,float w2,float h2){
        return x1<x2+w2 && x1+w1>x2 && y1<y2+h2 && y1+h1>y2;
    }

    public static float clamp(float v,float min,float max){
        return Math.max(min,Math.min(v,max));
    }

    public static int random(int min,int max){
        return (int)(Math.random()*(max-min)+min);
    }

    public static float random(float min,float max){
        return (float)Math.random()*(max-min)+min;
    }

    public static Vector3 cross(Vector3 v1,Vector3 v2){
        return new Vector3(v1.y*v2.z-v1.z*v2.y,v1.z*v2.x-v1.x*v2.z,v1.x*v2.y-v1.y*v2.x);
    }

    public static long randomSeed(int c){
        if(c>0){
            String seed="";
            for(int i=0; i<c; i++){
                if(i==0)
                    seed+=random(1,9);
                else
                    seed+=random(0,9);
            }
            return Long.parseLong(seed);
        }
        return 0;
    }

    static public boolean isZero(float value){
        return Math.abs(value)<=FLOAT_ROUNDING_ERROR;
    }

    static public boolean isEqual(float a,float b){
        return Math.abs(a-b)<=FLOAT_ROUNDING_ERROR;
    }

    static public boolean isZero(float value,float tolerance){
        return Math.abs(value)<=tolerance;
    }

    static public boolean isEqual(float a,float b,float tolerance){
        return Math.abs(a-b)<=tolerance;
    }

}
