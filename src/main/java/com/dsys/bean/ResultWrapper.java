package com.dsys.bean;

/**
 * 插件处理后结果封装
 */
public class ResultWrapper {
    private byte[] result;//插件处理后的文件字节流
    private String storagePath;//要存储的路径

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }
}
