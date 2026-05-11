package EVIL.KATIE.PACKAGE;

import android.os.Build;
import android.util.Log;

public class Extras {

    // Source - https://stackoverflow.com/a/26117646
    // Posted by Ben Jima
    // Retrieved 2026-04-28, License - CC BY-SA 3.0
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    // end of code taken from stackoverflow (and edited by me a lil ;3)

    public static void sayHello(){
        Log.i("The Developer", "Hello, " + getDeviceName());
    }

    public static void LogKitty(String tag, String msg){
        Log.i(tag, msg);
    }
}
