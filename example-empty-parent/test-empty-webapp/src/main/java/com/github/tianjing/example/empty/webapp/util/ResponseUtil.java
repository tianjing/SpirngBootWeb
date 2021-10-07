package com.github.tianjing.example.empty.webapp.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 19:14
 */
@Component
public class ResponseUtil {

    public void writeStream(InputStream pSource, OutputStream pTarget)throws IOException
    {
        byte[] temp=new byte[10*1024];
        int length=0;
        while ((length=pSource.read(temp))>0)
        {
            pTarget.write(temp,0,length);
        }
        try {
            pSource.close();
        }
        catch (Exception e)
        {}
    }
}
