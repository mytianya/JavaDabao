package com.dsys.bean;

/***
 * 封装待处理的文件信息
 */
public class TextWrapper {
    String charset="utf-8";
    String inputFilePath;//读取的文件路径
    String originFilePath;//文件来源路径
    String storeFilePath;//处理后存储路径
    boolean ingoreException=false;//是否忽略异样
    int matcherTimes=0;//匹配的次数
    int exceptionTimes=0;//异常发生次数
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public int getMatcherTimes() {
        return matcherTimes;
    }
    public void increMatcherTimes(){
        this.matcherTimes++;
    }

    public String getOriginFilePath() {
        return originFilePath;
    }

    public void setOriginFilePath(String originFilePath) {
        this.originFilePath = originFilePath;
    }

    public String getStoreFilePath() {
        return storeFilePath;
    }

    public void setStoreFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath;
    }

    public boolean isIngoreException() {
        return ingoreException;
    }

    public void setIngoreException(boolean ingoreException) {
        this.ingoreException = ingoreException;
    }

    public int getExceptionTimes() {
        return exceptionTimes;
    }

    public void increExceptionTimes() {
        this.exceptionTimes++;
    }
}
