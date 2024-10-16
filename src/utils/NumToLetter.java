package utils;

import java.util.Map;

public class NumToLetter {

    public static String numToLetter(int num){
        String resp ="";
        switch (num){
            case 0:
                resp = "a";
                break;
            case 1:
                resp = "b";
                break;
            case 2:
                resp = "c";
                break;
            case 3:
                resp = "d";
                break;
            case 4:
                resp = "e";
                break;
            case 5:
                resp = "f";
                break;
            case 6:
                resp = "g";
                break;
            case 7:
                resp = "h";
                break;
        }
        return resp;
    }
}
