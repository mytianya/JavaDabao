package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;
import com.yahoo.platform.yui.compressor.CssCompressor;

import java.io.*;

/**
 * css压缩插件
 */
public class CssCompressPlugin extends DabaoPlugin {
    public CssCompressPlugin(){
        super("CssCompressPlugin");
    }
    @Override
    ResultWrapper deal(String origin, byte[] content, String charset) throws Exception {
        try(StringWriter out=new StringWriter();StringReader in=new StringReader(new String(content,charset));){
            String destFile=origin.replace(catchDir,destDir);
            CssCompressor cssCompressor=new CssCompressor(in);
            cssCompressor.compress(out,-1);
            ResultWrapper resultWrapper=new ResultWrapper();
            resultWrapper.setStoragePath(destFile);
            resultWrapper.setResult(out.toString().getBytes(charset));
            return resultWrapper;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    boolean filter(String inputFileName) {
        return inputFileName.endsWith(".css");
    }
}
