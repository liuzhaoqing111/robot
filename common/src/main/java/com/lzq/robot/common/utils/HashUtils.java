package com.lzq.robot.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class HashUtils {

    public static String sha256(String source){
        Sha256Hash sha256Hash = new Sha256Hash(source);
        return sha256Hash.toString();
    }

    public static String sha256(String source, String salt){
        Sha256Hash sha256Hash = new Sha256Hash(source, salt);
        return sha256Hash.toString();
    }

    public static String md5(String source){
        Md5Hash md5Hash = new Md5Hash(source);
        return md5Hash.toString();
    }
    public static String md5(String source, String salt){
        Md5Hash md5Hash = new Md5Hash(source, salt);
        return md5Hash.toString();
    }

}
