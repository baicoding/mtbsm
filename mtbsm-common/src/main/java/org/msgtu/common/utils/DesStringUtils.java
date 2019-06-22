package org.msgtu.common.utils;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wonshine on 2017-09-28.
 */
public class DesStringUtils {
    public static String underline2Camel(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }


    /**
     * 首字母大写，其他的小写
     * @param string
     * @return
     */
    public static String capitalize(String string){
        if(string==null || "".equals(string)){
            return string;
        }
        return Character.toUpperCase(string.charAt(0))+string.substring(1).toLowerCase();
    }

    /**
     * 首字母小写，其他大写
     * @param string
     * @return
     */
    public static String uncapitalize(String string){
        if(string==null || "".equals(string)){
            return string;
        }
        return Character.toLowerCase(string.charAt(0))+string.substring(1).toUpperCase();
    }

    /**
     * 首字母大写
     * @param string
     * @return
     */
    public static String capFirst(String string){
        if(string==null || "".equals(string)){
            return string;
        }
        return Character.toUpperCase(string.charAt(0))+string.substring(1);
    }

    /**
     * 首字母小写
     * @param string
     * @return
     */
    public static String uncapFirst(String string){
        if(string==null || "".equals(string)){
            return string;
        }
        return Character.toLowerCase(string.charAt(0))+string.substring(1);
    }

    public static void main(String[] args) {
        String line="dtable_naNe";
        String camel=underline2Camel(line);
        System.out.println(camel);
        System.out.println(camel2Underline(camel));
        System.out.println(underline2Camel("abc"));
        System.out.println(capitalize("a"));
        System.out.println(uncapitalize("A"));
        System.out.println(capitalize("abC"));
        System.out.println(uncapitalize("abC"));
        System.out.println(capFirst("abC"));
        System.out.println(uncapFirst("abC"));
    }
}
