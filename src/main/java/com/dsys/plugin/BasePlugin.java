package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;

import java.nio.file.Paths;

/***
 * 原样copy文件到打包工程下，可用于发生异常时丢失文件
 */
public class BasePlugin extends DabaoPlugin {
    public BasePlugin() {
        super("basePlugin");
    }
    @Override
    ResultWrapper deal(String origin, byte[] content,String charset) throws Exception {
        String destFile=origin.replace(catchDir,destDir);
        ResultWrapper resultWrapper=new ResultWrapper();
        resultWrapper.setStoragePath(destFile);
        resultWrapper.setResult(content);//原样返回，只copy到目的地
        return resultWrapper;
    }

    @Override
    boolean filter(String inputFileName) {
        return !Paths.get(inputFileName).getParent().toString().contains(".svn");
    }
}
