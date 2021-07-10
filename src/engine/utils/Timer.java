package engine.utils;

public class Timer{


    private long lastMillis,millis;
    private boolean started;
    private boolean paused;


    public Timer start(){
        if(!started){
            started=true;
            lastMillis=System.currentTimeMillis();
            millis=System.currentTimeMillis();
        }
        return this;
    }

    public Timer setMillis(long millis){
        lastMillis=this.millis-millis;
        return this;
    }

    public Timer setSeconds(long seconds){
        setMillis(seconds*1000);
        return this;
    }

    public Timer setMinutes(long minutes){
        setSeconds(minutes*60);
        return this;
    }

    public Timer setHours(long hours){
        setMinutes(hours*60);
        return this;
    }

    public long getMillis(){
        if(started){
            if(!paused)
                millis=System.currentTimeMillis();
            return millis-lastMillis;
        }
        return 0;
    }

    public long getSeconds(){
        return getMillis()/1000;
    }

    public long getMinutes(){
        return getSeconds()/60;
    }

    public long getHours(){
        return getMinutes()/60;
    }

    public Timer stop(){
        if(started)
            started=false;
        return this;
    }

    public Timer pause(){
        paused=true;
        return this;
    }

    public Timer resume(){
        paused=false;
        return this;
    }


}
