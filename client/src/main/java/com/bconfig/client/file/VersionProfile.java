package com.bconfig.client.file;

import com.bconfig.client.constants.Constants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午9:25
 * 类描述 :
 */
public class VersionProfile {
    public static final String LOCAL_PROFILE = getLocalProfile();

    public static final VersionProfile ABSENT = new VersionProfile(Constants.NO_FILE_VERSION, LOCAL_PROFILE);

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


    private static String getLocalProfile() {
      return ""+":"+"";
    }

    public boolean needUpdate(VersionProfile versionProfile) {
        if (versionProfile == null) {
            return false;
        }

        if (versionProfile.getVersion() <= Constants.NO_FILE_VERSION) {
            return false;
        }

        if (this == ABSENT) {
            return true;
        }

        if (this.profile.equals(versionProfile.profile) && this.version >= versionProfile.version) {
            return false;
        }

        return true;
    }
}
