package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * html中css,js资源添加版本号插件
 */
public class VerPlugin extends DabaoPlugin {
    static final String scriptRegex="<script.*src=.*[\"']";
    static final String cssRegex="<link.*href=.*[\"']";
    static final String replaceScriptRegex="\\.js[?=\\w\\d]*(?=[\"'])";
    static final String replaceCssRegex="\\.css[?=\\w\\d]*(?=[\"'])";
    static Pattern scriptP=Pattern.compile(scriptRegex);
    static Pattern cssP=Pattern.compile(cssRegex);
    String currentVersion;
    public VerPlugin(String currentVersion, String moveDir, String catchDir){
        super("VerPlugin");
        if(currentVersion==null||currentVersion.trim()==""){
            this.currentVersion=String.valueOf(System.currentTimeMillis());
        }else{
            this.currentVersion=currentVersion;
        }
    }
    public VerPlugin(){
        super("VerPlugin");
        this.currentVersion=String.valueOf(System.currentTimeMillis());

    }
    //强制所有html中css,js都添加到最新的版本号
    public String forceChangeVersion(String html){
        //替换js版本号为当前时间戳
        if(html==null||html=="")
            return "";
        Matcher m=scriptP.matcher(html);
        while (m.find()){
            String temp=m.group();
            String changeJsVerText=temp.replaceFirst(replaceScriptRegex,".js?ver="+currentVersion);
            html=html.replace(temp,changeJsVerText);
        }
        m=cssP.matcher(html);
        while(m.find()){
            String temp=m.group();
            String changeCssVerText=temp.replaceFirst(replaceCssRegex,".css?ver="+currentVersion);
            html=html.replace(temp,changeCssVerText);
        }
        return html;
    }
    @Override
    ResultWrapper deal(String origin,byte[] content,String charset) throws Exception {
        String destFile=origin.replace(catchDir,destDir);
        String changeContent=forceChangeVersion(new String(content,charset));
        ResultWrapper resultWrapper=new ResultWrapper();
        resultWrapper.setResult(changeContent.getBytes(charset));
        resultWrapper.setStoragePath(destFile);
        return resultWrapper;
    }

    @Override
    boolean filter(String inputFileName) {
        return inputFileName.endsWith(".html")&&inputFileName.startsWith(catchDir);
    }

}
