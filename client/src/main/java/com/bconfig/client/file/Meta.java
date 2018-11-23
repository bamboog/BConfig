package com.bconfig.client.file;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午9:23
 * 类描述 :
 */
public class Meta {
    private final String key;
    private final String groupName;
    private final String fileName;

    public Meta(String groupName, String fileName) {
        this.key = generateKey(groupName, fileName);
        this.groupName = groupName;
        this.fileName = fileName;
    }


    public String getKey() {
        return key;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getFileName() {
        return fileName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;

        Meta meta = (Meta) o;

        return key.equals(meta.key);

    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public static String generateKey(String groupName, String fileName) {
        return groupName + "/" + fileName;
    }

}
