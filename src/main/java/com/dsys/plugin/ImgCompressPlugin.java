package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;

import java.io.*;

/***
 * 图片压缩插件，压缩质量过小失真
 */

public class ImgCompressPlugin extends DabaoPlugin{
    private  float scale=1.0f;//原样尺寸输出
    private float quality=0.8f;//压缩质量
    private int filesizeLimit=300;//多少kb才压缩
    public ImgCompressPlugin(){
        super("ImgCompressPlugin");
    }
    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public int getFilesizeLimit() {
        return filesizeLimit;
    }

    public void setFilesizeLimit(int filesizeLimit) {
        this.filesizeLimit = filesizeLimit;
    }

    @Override
    ResultWrapper deal(String origin, byte[] content, String charset) throws Exception {
       try(ByteArrayInputStream in=new ByteArrayInputStream(content);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        ){
           Thumbnails.of(in)
                   .scale(scale)
                   .outputQuality(quality)
                   .toOutputStream(out);
           out.flush();
           String destFile=origin.replace(catchDir,destDir);
           ResultWrapper resultWrapper=new ResultWrapper();
           resultWrapper.setResult(out.toByteArray());
           resultWrapper.setStoragePath(destFile);
           return null;
       }catch (Exception e){
           throw e;
       }

    }

    @Override
    boolean filter(String inputFileName) {
        long sizeKB=FileUtils.sizeOf(new File(inputFileName))/1024;
        return (inputFileName.endsWith(".png")
                ||inputFileName.endsWith(".jpg")
                ||inputFileName.endsWith("jpeg")
                ||inputFileName.endsWith("gif"))&&(sizeKB>filesizeLimit);
    }
}
