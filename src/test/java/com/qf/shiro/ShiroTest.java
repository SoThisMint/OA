package com.qf.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/26 10:02
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ShiroTest {

    public static void main(String[] args) {
        //1.加密算法
        String algorithmName = "MD5";

        //2.密码
        Object source = "mike";

        //3.盐值(一般是用户名或用户id)
        Object salt = ByteSource.Util.bytes("mike");

        //4.加密次数
        int hashIteratorations = 1024;

        SimpleHash simpleHash = new SimpleHash(algorithmName,source,salt,hashIteratorations);

        //5.得到加密后的密码
        System.out.println(simpleHash);
    }
}
