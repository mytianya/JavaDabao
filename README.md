# JavaDabao
本项目主要解决一个后端程序员怎么接手前端项目，打包上线开发的一款小工具。

## 已实现功能

1. Html文件中资源文件css,js添加版本号，让客户端强制更新到最新文件。
2. Css文件压缩功能。
3. Javascript压缩、混淆功能。
4. 体积过大图片压缩功能

## 项目特点
1. 项目具有可扩展性，插件可自由开发，组合使用
2. 项目容错性高，异常信息捕获打印清晰。
3. 项目代码结构清楚，便于二次开发

## 开始使用
````java
    public static void main(String args[]) throws Exception {
        CommonArgs commonArgs=new CommonArgs();
        String projectDir="F:\\front\\test";//前端项目根路径
        commonArgs.setProjectDir(projectDir);
        String destDir="C:\\Users\\dsys\\Desktop\\testc";
        VerPlugin verPlugin= new VerPlugin();//版本插件配置
        verPlugin.setCatchDir(projectDir);
        verPlugin.setDestDir(destDir);
        BasePlugin basePlugin=new BasePlugin();//基本插件配置
        basePlugin.setCatchDir(projectDir);
        basePlugin.setDestDir(destDir);
        CssCompressPlugin cssCompressPlugin=new CssCompressPlugin();//css压缩插件配置
        cssCompressPlugin.setCatchDir(projectDir);
        cssCompressPlugin.setDestDir(destDir);
        JsCompressPlugin jsCompressPlugin=new JsCompressPlugin(true);//js压缩混淆插件配置
        jsCompressPlugin.setCatchDir(projectDir);
        jsCompressPlugin.setDestDir(destDir);
        ImgCompressPlugin imgCompressPlugin=new ImgCompressPlugin();//图片压缩插件配置
        imgCompressPlugin.setCatchDir(projectDir);
        imgCompressPlugin.setDestDir(destDir);
        DabaoChain dabaoChain =new DabaoChain();//插件链处理顺序
        dabaoChain.addPlugins(verPlugin)
                .addPlugins(cssCompressPlugin)
                .addPlugins(jsCompressPlugin)
                .addPlugins(imgCompressPlugin)
                .addPlugins(basePlugin);//这里是为异常情况，直接copy
        DaobaoFileVistor daobaoFileVistor=new DaobaoFileVistor(commonArgs, dabaoChain);
        Dabao dabao=new Dabao(commonArgs,daobaoFileVistor);
        dabao.start();
    }
````
