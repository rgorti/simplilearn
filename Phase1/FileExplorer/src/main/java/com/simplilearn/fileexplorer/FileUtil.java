package com.simplilearn.fileexplorer;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static void printList(List<String> list){
        for(int i=0; i<list.size(); i++)
            System.out.println((i+1) + " "+list.get(i));
    }

    public static void print(String message){
        System.out.println(message);
    }

    public static void printError(String message, Exception e){
        System.out.println(message);
       // e.printStackTrace();
    }

    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

}
