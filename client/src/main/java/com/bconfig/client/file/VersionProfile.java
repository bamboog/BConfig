package com.bconfig.client.file;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午9:25
 * 类描述 :
 */
public class VersionProfile {

    private final long version;

    private final String profile;

    public VersionProfile(long version, String profile) {
        this.profile = profile;
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public String getProfile() {
        return profile;
    }
}
