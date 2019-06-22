package org.msgtu.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Wonshine on 2017-10-19.
 */
public class FileUtils {
    public static void saveToFile(final String filetext,final String filename) throws IOException {
        int index=filename.lastIndexOf("/");
        if(index<1){
            index=filename.lastIndexOf("\\");
        }
        if(index>1){
            new File(filename.substring(0,index)).mkdirs();
        }
        saveToFile(filetext,new File(filename));
    }

    public static void saveToFile(final String filetext,final File file) throws IOException {
        FileWriter fw=null;
        try {
            fw=new FileWriter(file);
            fw.write(filetext);
        }finally {
            if(fw!=null){
                fw.close();
            }
        }
    }
}
