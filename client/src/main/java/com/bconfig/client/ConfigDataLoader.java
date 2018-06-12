package com.bconfig.client;

import com.bconfig.client.file.FileStore;
import com.bconfig.client.file.Meta;
import com.bconfig.client.file.VersionProfile;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午10:21
 * 类描述 :
 */
public class ConfigDataLoader {

    static {

        //取本地文件
        Map<Meta, VersionProfile> allFiles = FileStore.getAllFiles();


    }
}
