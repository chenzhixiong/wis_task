package com.gszh.wis.tsp.controller.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by chenzhixiong on 2016/8/12.
 */

public class TestMain {

    public static void main(String[] args) {
//        testTimeUse();
//        testStirngArr();
//        testReflex();
//        testList();
        System.out.println(camelName("id\n" +
                "job_name\n" +
                "job_group\n" +
                "job_ch_name\n" +
                "job_file\n" +
                "job_class\n" +
                "entity_class\n" +
                "description\n" +
                "username\n" +
                "password\n" +
                "create_time\n" +
                "update_time\n" +
                "time\n" +
                "time_type\n"));
    }

    /**
     * List 下标实验
     */
    private static void testList() {
        List<String> list = new LinkedList<String>();
        List<String> list2 = new LinkedList<String>();

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
            list2.add("" + i);
        }
        list2.add("1");
        list2.add("2");
        list2.add("3");
        System.out.println(list.toString());


        System.out.println(list2);
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    List<String> getIntersection(List<String> aList, List<String> bList) {
        List<String> cList = new ArrayList<String>();
        //清除 bList 中与 aList 无关的元素
        cList.addAll(bList);
        bList.removeAll(aList);
        cList.removeAll(bList);
        bList.clear();
        bList.addAll(cList);
        //遍历 bList 每次去除一组与 aList 相同的元素
        while (bList.size() > aList.size()) {
            for (String a : aList) {
                for (int i = 0; i < bList.size(); i++) {
                    if (bList.get(i).equals(a)) {
                        bList.remove(i);
                        break;
                    }
                }
            }
        }
        return bList;
    }

    private static void testReflex() {
        try {
            Class<?> classType = Class.forName("com.gszh.wis.tsp.task.HelloJob");
            Object object = classType.newInstance();
            System.out.println(object.getClass().getName());
            Method method = classType.getMethod("test", Map.class);
            method.invoke(object, new HashMap<String, String>());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //测试 String split 方法
    static void testStirngArr() {
        String a = "hheheh";
        String b = "bjijii;";
        String[] c = a.split(";");
        System.out.println(c[0]);
        String[] d = b.split(";");
        System.out.println(d[0]);
    }

    /**
     * 测试执行时间
     */
    private static void testTimeUse() {
        System.out.println(new Date());
        long flag = 1L;
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < 20000000; i++) {
            flag++;
            list.add(flag);
        }
        System.out.println(list.size() + "次计算任务");
        list.clear();
        System.out.println(new Date());
    }
}
