package com.bconfig.client.file;

import com.bconfig.client.constants.Constants;
import com.bconfig.client.file.utils.FileUtil;
import com.google.common.io.Files;
import sun.plugin.cache.FileVersion;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午8:45
 * 类描述 :
 */
public class FileStore<T> {

    private static final File FILE_PATH_HOME = new File(FileUtil.getFilePath(), "bconfig/");
    private static final String FILE_TAIL_VERSION_EXT = ".ver1";
    private final Meta meta;

    public FileStore(Meta meta) {

        this.meta = meta;
    }

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
                String line = com.google.common.io.Files.readFirstLine(file, Constants.UTF_8);
                Iterator<String> iterator = Constants.COMMA_SPLITTER.split(line).iterator();
                long version = Long.parseLong(iterator.next());
                String profile = iterator.next();
                return new VersionProfile(version, profile);
            }
        } catch (Exception e) {
            System.out.printf("can't_get_version_file");
        }
        return null;
    }

    /*********************/



    private static final String LOCAL_TEST_DIR = "qconfig_test/";

    private static final String SNAPSHOT_DIR = "/snapshot2/";


    private static final int MAX_RETAIN_VERSION = 3;
    public static File getSnapshotFile(VersionProfile version, String groupName, String fileName) {
        return new File(FILE_PATH_HOME, groupName + SNAPSHOT_DIR + fileName + "." + version.getVersion() + "." + profileToFileName(version.getProfile()));
    }

    // 文件名不能有':'
    static String profileToFileName(String profile) {
        return profile.replace(":", "-");
    }

    public static void storeData(Meta meta, VersionProfile version, String data) throws IOException {
        final File file = FileStore.getSnapshotFile(version, meta.getGroupName(), meta.getFileName());
        file.getParentFile().mkdirs();
        File tempFile = null;
        try {
            tempFile = File.createTempFile("config", ".tmp");
            Files.write(data, tempFile, Constants.UTF_8);
            if (!file.exists() && !tempFile.renameTo(file)) {
                synchronized (FileStore.class) {
                    if (!file.exists()) {
                        Files.copy(tempFile, file);
                    }
                }
            }
        } finally {
            if (tempFile != null && tempFile.exists() && !tempFile.delete()){
                System.out.println("delete temp file failed");
            }
        }
    }

    public Meta getMeta() {
        return meta;
    }
    private AtomicReference<FileVersion> currentVersion = new AtomicReference<FileVersion>();
    public boolean isLoaded() {
        return currentVersion.get() != null;
    }





}
