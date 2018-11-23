package com.bconfig.client.file.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午9:27
 * 类描述 :
 */
public class FileUtil {

    /**
     * 获取文件配置路径--
     * @return
     */
    // TODO: 2018/6/11 需要单例处理 
    public static String getFilePath() {
        String path = System.getProperty("self.cache", null);

        if (path == null) {
            path = System.getProperty("catalina.base");
            if (path == null) {
                path = System.getProperty("java.io.tmpdir");
            }
            path = path + File.separator + "cache";
            System.setProperty("self.cache", path);
        }

        File file = new File(path);
        try {
            file.mkdirs();
            path = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
