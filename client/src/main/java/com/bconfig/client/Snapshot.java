package com.bconfig.client;

import com.bconfig.client.file.VersionProfile;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/12
 * Time  : 下午10:05
 * 类描述 :
 */
public class Snapshot<T> {
    private final VersionProfile version;
    private final T content;

    Snapshot(String profile, long version, T content) {
        this.version = new VersionProfile(version, profile);
        this.content = content;
    }

    public VersionProfile getVersion() {
        return version;
    }

    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "version=" + version +
                ", content=" + content +
                '}';
    }
}
