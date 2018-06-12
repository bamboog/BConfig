package com.bconfig.client.constants;

import com.google.common.base.Splitter;

import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/11
 * Time  : 下午10:18
 * 类描述 :
 */
public class Constant {


    public static final String COMMA = ",";
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Splitter COMMA_SPLITTER = Splitter.on(COMMA).omitEmptyStrings().trimResults();
}
