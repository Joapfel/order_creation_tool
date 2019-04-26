package Utils;

import java.io.IOException;

public class PathUtils {

    public static String getCurrentDir(){
        String current = "";
        try {
            current = new java.io.File(".").getCanonicalPath();
        }catch (IOException e) {
            System.out.println(e.toString());
        }
        return current;
    }

}
