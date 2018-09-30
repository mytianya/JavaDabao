package com.dsys.plugin;

import com.dsys.bean.TextWrapper;
import java.util.LinkedList;

/***
 * 插件链
 */
public class DabaoChain {
   // private int i=0;
    public LinkedList<DabaoPlugin> plugins=new LinkedList<>();
    public DabaoChain addPlugins(DabaoPlugin plugin){
        plugins.addLast(plugin);
        return this;
    }
    public void start(TextWrapper textWrapper){
        //被遍历插件列表处理文件
        int i=0;
        while(i<plugins.size()){
            DabaoPlugin plugin=plugins.get(i);
            textWrapper=plugin.start(textWrapper);
            i++;
        }
    }
}
