package com.dsys.bean;

/**
 * 基本参数
 */
public class CommonArgs {
    private String projectDir;//前端项目目录
    private boolean ignoreException=true;//是否忽略异常，还是停止,默认忽略，即文件原样copy到打包目录

    public String getProjectDir() {
        return projectDir;
    }
    public void setProjectDir(String projectDir) {
        this.projectDir = projectDir;
    }

    public boolean isIgnoreException() {
        return ignoreException;
    }

    public void setIgnoreException(boolean ignoreException) {
        this.ignoreException = ignoreException;
    }
}
