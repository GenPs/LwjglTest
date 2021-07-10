package engine.graphics;

public class Color{

    private float r,g,b,a;

    public Color(float red,float green,float blue,float alpha){
        r=red;
        g=green;
        b=blue;
        a=alpha;
    }
    public Color(Color color){
        r=color.r;
        g=color.g;
        b=color.b;
        a=color.a;
    }
    public Color(){}

    public void set(float red,float green,float blue,float alpha){
        r=red;
        g=green;
        b=blue;
        a=alpha;
    }
    public void set(Color color){
        r=color.r;
        g=color.g;
        b=color.b;
        a=color.a;
    }

    public float red(){
        return r;
    }
    public float green(){
        return g;
    }
    public float blue(){
        return b;
    }
    public float alpha(){
        return a;
    }

}
