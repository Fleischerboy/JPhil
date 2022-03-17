package org.jphil.utils;


public class StringUtils {


    public static int compute(String str, char ch){
        int count = 0 ;
        for(char c : str.toCharArray()){
            if(c==ch){
                count++;
            }
        }
        return count;
    }



}
