package com.dsys.main;

import com.dsys.bean.CommonArgs;
import com.dsys.bean.TextWrapper;
import com.dsys.plugin.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/***
 * 打包类
 */
public class Dabao {
    private CommonArgs commonArgs;
    private DaobaoFileVistor daobaoFileVistor;
    Dabao(CommonArgs commonArgs, DaobaoFileVistor daobaoFileVistor){
        this.commonArgs=commonArgs;
        this.daobaoFileVistor = daobaoFileVistor;
    }
    public static void main(String args[]) throws Exception {
        CommonArgs commonArgs=new CommonArgs();
        String projectDir="F:\\front\\wangpuwd";
        commonArgs.setProjectDir(projectDir);
        String destDir="C:\\Users\\dsys\\Desktop\\testc";
        VerPlugin verPlugin= new VerPlugin();
        verPlugin.setCatchDir(projectDir);
        verPlugin.setDestDir(destDir);
        BasePlugin basePlugin=new BasePlugin();
        basePlugin.setCatchDir(projectDir);
        basePlugin.setDestDir(destDir);
        CssCompressPlugin cssCompressPlugin=new CssCompressPlugin();
        cssCompressPlugin.setCatchDir(projectDir);
        cssCompressPlugin.setDestDir(destDir);
        JsCompressPlugin jsCompressPlugin=new JsCompressPlugin(true);
        jsCompressPlugin.setCatchDir(projectDir);
        jsCompressPlugin.setDestDir(destDir);
        ImgCompressPlugin imgCompressPlugin=new ImgCompressPlugin();
        imgCompressPlugin.setCatchDir(projectDir);
        imgCompressPlugin.setDestDir(destDir);
        DabaoChain dabaoChain =new DabaoChain();
        dabaoChain.addPlugins(verPlugin)
                .addPlugins(cssCompressPlugin)
                .addPlugins(jsCompressPlugin)
                .addPlugins(imgCompressPlugin)
                .addPlugins(basePlugin);//这里是为异常情况，直接copy
        DaobaoFileVistor daobaoFileVistor=new DaobaoFileVistor(commonArgs, dabaoChain);
        Dabao dabao=new Dabao(commonArgs,daobaoFileVistor);
        dabao.start();
    }
    void start() throws Exception{
        Files.walkFileTree(Paths.get(commonArgs.getProjectDir()), daobaoFileVistor);
    }
    static class DaobaoFileVistor extends SimpleFileVisitor<Path> {
        CommonArgs commonArgs;
        DabaoChain dabaoChain;
        public DaobaoFileVistor(CommonArgs commonArgs, DabaoChain dabaoChain){
            this.commonArgs=commonArgs;
            this.dabaoChain = dabaoChain;
        }

        /**
         * 移动目录
         * @param dir
         * @param attrs
         * @return
         * @throws IOException
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //String moveDir=dir.toRealPath().toString().replace(commonArgs.getProjectDir());
         //   Files.createDirectories(Paths.get(moveDir));
            return super.preVisitDirectory(dir, attrs);
        }
        /**
         * 处理文件 一个又一个移动文件，文件经过一个又一个过滤器
         * @param file
         * @param attrs
         * @return
         * @throws IOException
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String sourcePath=file.toRealPath().toString();
            TextWrapper textWrapper=new TextWrapper();
            textWrapper.setInputFilePath(sourcePath);
            textWrapper.setOriginFilePath(sourcePath);
            textWrapper.setIngoreException(true);
            dabaoChain.start(textWrapper);
            return super.visitFile(file, attrs);
        }
    }
}
