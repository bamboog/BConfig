package com.bconfig.client.file;

import com.bconfig.client.constants.Constant;
import com.bconfig.client.file.utils.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午8:45
 * 类描述 :
 */
public class FileStore {

    private static final File FILE_PATH_HOME = new File(FileUtil.getFilePath(), "bconfig/");
    private static final String FILE_TAIL_VERSION_EXT = ".ver1";

    public static Map<Meta, VersionProfile> getAllFiles() {
        Map<Meta, VersionProfile> map = new HashMap<Meta, VersionProfile>();
        try {
            for (File groupFile : FILE_PATH_HOME.listFiles()) {
                if (!groupFile.isDirectory()) {
                    continue;
                }
                String groupName = groupFile.getName();

                for (File file : groupFile.listFiles()) {
                    String fileName = file.getName();
                    if (!fileName.endsWith(FILE_TAIL_VERSION_EXT)){
                        continue;
                    }
                    VersionProfile version = readVersion(file);
                    fileName = fileName.substring(0, fileName.length() - FILE_TAIL_VERSION_EXT.length());
                    map.put(new Meta(groupName, fileName), version);
                }
            }
        } catch (Throwable t) {
            System.out.printf("++init_error++");
        }
        return map;
    }

   private static VersionProfile readVersion(File file) {
        try {
            if (file.canRead()) {
                String line = com.google.common.io.Files.readFirstLine(file, Constant.UTF_8);
                Iterator<String> iterator = Constant.COMMA_SPLITTER.split(line).iterator();
                long version = Long.parseLong(iterator.next());
                String profile = iterator.next();
                return new VersionProfile(version, profile);
            }
        } catch (Exception e) {
            System.out.printf("can't_get_version_file");
        }
        return null;
    }
}
