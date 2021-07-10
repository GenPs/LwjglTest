package engine.utils;

import  java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils{

    public static String readFile(String path){
        try{
            byte[] encoded=Files.readAllBytes(Paths.get(path));
            return new String(encoded,StandardCharsets.UTF_8);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static float numbersAfterPoint(float value,int nums){
        String val=""+value;
        int point=val.indexOf(".")+1;
        return Float.parseFloat(val.substring(0,point+nums));
    }

}
