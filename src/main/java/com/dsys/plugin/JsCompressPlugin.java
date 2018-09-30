package com.dsys.plugin;

import com.dsys.bean.ResultWrapper;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;

/***
 * javascript文件压缩混淆插件
 */
public class JsCompressPlugin extends DabaoPlugin {
    boolean munge;
    public JsCompressPlugin(boolean munge) {
        super("JsCompressPlugin");
        this.munge=munge;
    }
    @Override
    ResultWrapper deal(String origin, byte[] content, String charset) throws Exception {
        try (StringWriter out = new StringWriter(); StringReader in = new StringReader(new String(content, charset));) {
            JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
                @Override
                public void warning(String message, String sourceName,
                                    int line, String lineSource, int lineOffset) {
                    System.err.println("\n[WARNING] in " + origin);
                    if (line < 0) {
                        System.err.println("  " + message);
                    } else {
                        System.err.println("  " + line + ':' + lineOffset + ':' + message);
                    }
                }

                @Override
                public void error(String message, String sourceName,
                                  int line, String lineSource, int lineOffset) {
                    System.err.println("[ERROR] in " + origin);
                    System.err.println(lineSource);
                    if (line < 0) {
                        System.err.println("  " + message);
                    } else {
                        System.err.println("  " + line + ':' + lineOffset + ':' + message);
                    }
                }

                @Override
                public EvaluatorException runtimeError(String message, String sourceName,
                                                       int line, String lineSource, int lineOffset) {
                    return new EvaluatorException(message);
                }
            });
            //preserveAllSemiColons 保留所有分号
            //disableOptimizations 禁止优化
            //munge true混淆,false 仅缩小
            compressor.compress(out, -1, munge, false,
                    true, false);
            String destFile=origin.replace(catchDir,destDir);
            ResultWrapper resultWrapper=new ResultWrapper();
            resultWrapper.setStoragePath(destFile);
            resultWrapper.setResult(out.toString().getBytes(charset));
            return resultWrapper;
        }
    }
    @Override
    boolean filter(String inputFileName) {
        return inputFileName.endsWith(".js");
    }
}
