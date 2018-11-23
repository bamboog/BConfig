package com.bconfig.client;

import com.bconfig.client.enums.ConfigLogType;
import com.bconfig.client.file.Meta;
import com.bconfig.client.file.VersionProfile;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/12
 * Time  : 下午10:00
 * 类描述 :
 */
public interface Client {

    ListenableFuture<Map<Meta, VersionProfile>> checkUpdate(Map<Meta, VersionProfile> files);

    ListenableFuture<String> loadData(Meta key, VersionProfile version);

    /**
     * 强制下载大于指定版本的文件.
     * @param key
     * @param minVersion
     * @return
     */
    ListenableFuture<Snapshot<String>> forceReload(Meta key, long minVersion);

    void recordLoading(ConfigLogType type, Meta meta, long version, String errorInfo) throws IOException;

    ListenableFuture<UploadResult> upload(Meta meta, VersionProfile versionProfile, String data);
}
