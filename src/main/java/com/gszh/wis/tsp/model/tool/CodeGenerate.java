package com.gszh.wis.tsp.model.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhixiong on 2016/9/9.
 */
public class CodeGenerate {
    public static void main(String[] args) {
//        for (int i = 1; i < 101; i++) {
//            generate(i, "SerialJob");
//        }
//        generate(101, "SerialJob");
//        javac(".\\src\\main\\java\\com\\gszh\\wis\\tsp\\task\\serial\\SerialJob101.java");
    }

    /**
     * 编译java类
     *
     * @param writerPath
     */
    private static void javac(String writerPath) {
        try {
            //java编译器
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            //文件管理器，参数1：diagnosticListener  监听器,监听编译过程中出现的错误
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            //java文件转换到java对象，可以是多个文件
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(writerPath);
            //编译任务,可以编译多个文件
            JavaCompiler.CompilationTask t = compiler.getTask(null, manager, null, null, null, it);
            //执行任务
            if(t.call()){
                System.out.println("...");
            }
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generate(int i, String className) {
        //创建freemarker配置实例
        Configuration cfg = new Configuration(new Version(2, 3, 0));
        //设置模板所在的路径
        cfg.setClassForTemplateLoading(CodeGenerate.class, "/job_temp");

        try {
            //加载模板文件
            Template t = cfg.getTemplate("javabean.temp");
            //显示生成后的数据
            FileOutputStream fos = new FileOutputStream(new File("./src/main/java/com/gszh/wis/tsp/task/serial/SerialJob" + i + ".java"));
            //创建数据模型
            Map<String, String> map = new HashMap<String, String>();
            map.put("className", className + i);
            map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            //生成代码
            t.process(map, new OutputStreamWriter(fos, "utf-8"));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
