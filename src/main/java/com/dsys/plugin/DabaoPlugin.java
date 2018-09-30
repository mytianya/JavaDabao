package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;
import com.dsys.bean.TextWrapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * 插件抽象类
 */
public abstract class DabaoPlugin {
    String pluginName;//插件名称
    String destDir;//移动到的目录
    String catchDir;//需要处理的目录
    DabaoPlugin(String pluginName) {
        this.pluginName = pluginName;
    }
    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getCatchDir() {
        return catchDir;
    }

    public void setCatchDir(String catchDir) {
        this.catchDir = catchDir;
    }

    TextWrapper start(TextWrapper textWrapper) {
        if (filter(textWrapper.getOriginFilePath())) {//起始来源是否匹配到
            try {
                return work(textWrapper);
            } catch (Exception e) {
                System.err.println(String.format("{Exception cause by plugins:%s on file: %s}", pluginName, textWrapper.getOriginFilePath()));
                e.printStackTrace();
                textWrapper.increExceptionTimes();
                if (textWrapper.isIngoreException()) {
                    return textWrapper;
                } else {
                    System.exit(0);//不忽略，直接停止打包过程
                    return textWrapper;
                }
            }
        } else {
            return textWrapper;//过滤，不做任何处理
        }
    }

    /**
     * 实际插件处理文本过程，返回处理后存储的全路径
     *
     * @return
     * @throws Exception
     */
    abstract ResultWrapper deal(String origin, byte[] content,String charset) throws Exception;
    TextWrapper work(TextWrapper textWrapper) throws Exception {
        int times = textWrapper.getMatcherTimes();
        ResultWrapper output;//返回插件处理后的内容和存储的路径
        String origin = textWrapper.getOriginFilePath();
        String input = textWrapper.getInputFilePath();
        String charset = textWrapper.getCharset();
        if (times == 0) {
            output = deal(origin,Files.readAllBytes(Paths.get(origin)),textWrapper.getCharset());//没被处理过，文件在起始位置
        } else {
            if (input != null && input.trim() != "") {
                output = deal(origin, Files.readAllBytes(Paths.get(input)),textWrapper.getCharset());//文件被处理过，拿InputFilePath();
            } else {
                output = deal(origin, Files.readAllBytes(Paths.get(origin)),textWrapper.getCharset());//文件被处理过，但是地址未被更改
            }
        }
        textWrapper.setInputFilePath(output.getStoragePath());
        String destFile=output.getStoragePath();
        Path parent=Paths.get(destFile).getParent();
        Files.deleteIfExists(Paths.get(destFile));
        if(!parent.toFile().exists()){
            Files.createDirectories(parent);
        }
        Files.write(Paths.get(destFile),output.getResult());
        textWrapper.increMatcherTimes();//增加被处理次数
        return textWrapper;
    }

    /**
     * 插件匹配文件规则
     *
     * @param inputFileName
     * @return
     */
    abstract boolean filter(String inputFileName);
}
