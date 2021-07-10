package engine.maths.vectors;

public class Vector3{

    public float x,y,z;

    public Vector3(){}
    public Vector3(float xyz){
        x=xyz;
        y=xyz;
        z=xyz;
    }
    public Vector3(float x,float y,float z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Vector3(Vector3 v){
        x=v.x;
        y=v.y;
        z=v.z;
    }

    public Vector3 set(float xyz){
        x=xyz;
        y=xyz;
        z=xyz;
        return this;
    }
    public Vector3 set(float x,float y,float z){
        this.x=x;
        this.y=y;
        this.z=z;
        return this;
    }
    public Vector3 set(Vector3 v){
        x=v.x;
        y=v.y;
        z=v.z;
        return this;
    }

    public Vector3 add(float xyz){
        x+=xyz;
        y+=xyz;
        z+=xyz;
        return this;
    }
    public Vector3 add(float x,float y,float z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }
    public Vector3 add(Vector3 v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
        return this;
    }

    public Vector3 sub(float xyz){
        x-=xyz;
        y-=xyz;
        z-=xyz;
        return this;
    }
    public Vector3 sub(float x,float y,float z){
        this.x-=x;
        this.y-=y;
        this.z-=z;
        return this;
    }
    public Vector3 sub(Vector3 v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
        return this;
    }

    public Vector3 mul(float xyz){
        x*=xyz;
        y*=xyz;
        z*=xyz;
        return this;
    }
    public Vector3 mul(float x,float y,float z){
        this.x*=x;
        this.y*=y;
        this.z*=z;
        return this;
    }
    public Vector3 mul(Vector3 v){
        x*=v.x;
        y*=v.y;
        z*=v.z;
        return this;
    }

    public Vector3 div(float xyz){
        x/=xyz;
        y/=xyz;
        z/=xyz;
        return this;
    }
    public Vector3 div(float x,float y,float z){
        this.x/=x;
        this.y/=y;
        this.z/=z;
        return this;
    }
    public Vector3 div(Vector3 v){
        x/=v.x;
        y/=v.y;
        z/=v.z;
        return this;
    }

    public float len(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }

    public Vector3 zero(){
        x=0;
        y=0;
        z=0;
        return this;
    }
    public boolean isZero(){
        return x==0 && y==0 && z==0;
    }

    public Vector3 nor(){
        double len2=x*x+y*y+z*z;
        if(len2==0 || len2==1)
            return this;
        double invLen=1/Math.sqrt(len2);
        x*=invLen;
        y*=invLen;
        z*=invLen;
        return this;
    }

    public float dot(float x,float y,float z){
        return this.x*x+this.y*y+this.z*z;
    }
    public float dot(Vector3 v){
        return x*v.x+y*v.y+z*v.z;
    }

    public float dst(float x,float y,float z){
        float dx=this.x-x;
        float dy=this.y-y;
        float dz=this.z-z;
        return (float)Math.sqrt(dx*dx+dy*dy+dz*dz);
    }
    public float dst(Vector3 v){
        float dx=x-v.x;
        float dy=y-v.y;
        float dz=z-v.z;
        return (float)Math.sqrt(dx*dx+dy*dy+dz*dz);
    }

    public boolean equals(Vector3 v){
        return v.x==x && v.y==y && v.z==z;
    }
    public boolean equals(float x,float y){
        return this.x==x && this.y==y && this.z==z;
    }

    public Vector3 crs(Vector3 vector){
        this.x=y*vector.z-z*vector.y;
        this.y=z*vector.x-x*vector.z;
        this.z=x*vector.y-y*vector.x;
        return this;
    }
    public Vector3 crs(float x,float y,float z){
        this.x=this.y*z-this.z*y;
        this.y=this.z*x-this.x*z;
        this.z=this.x*y-this.y*x;
        return this;
    }


    public static float dot(float x1,float y1,float z1,float x2,float y2,float z2){
        return x1*x2+y1*y2+z1*z2;
    }
    public static float dot(Vector3 v1,Vector3 v2){
        return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
    }

    public static float dst(float x1,float y1,float z1,float x2,float y2,float z2){
        float dx=x1-x2;
        float dy=y1-y2;
        float dz=z1-z2;
        return (float)Math.round(dx*dx+dy*dy+dz*dz);
    }
    public static float dst(Vector3 v1,Vector3 v2){
        float dx=v1.x-v2.x;
        float dy=v1.y-v2.y;
        float dz=v1.z-v2.z;
        return (float)Math.round(dx*dx+dy*dy+dz*dz);
    }

    public static float len(float x,float y,float z){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }
    public static float len(Vector3 v){
        return (float)Math.sqrt(v.x*v.x+v.y*v.y+v.z*v.z);
    }

    public static boolean equals(Vector3 v1,Vector3 v2){
        return v1.x==v2.x && v1.y==v2.y && v1.z==v2.z;
    }
    public static boolean equals(float x1,float y1,float z1,float x2,float y2,float z2){
        return x1==x2 && y1==y2 && z1==z2;
    }
    
}
