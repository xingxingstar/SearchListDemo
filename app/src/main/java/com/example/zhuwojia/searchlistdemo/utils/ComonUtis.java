package com.example.zhuwojia.searchlistdemo.utils;

/**
 * author：shixinxin on 2017/5/8
 * version：v1.0
 */

public  class ComonUtis {
    public static String exChange(String str){
        StringBuffer sb = new StringBuffer();
        if(str!=null){
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(Character.isUpperCase(c)){
                    sb.append(Character.toLowerCase(c));
                }else{
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
