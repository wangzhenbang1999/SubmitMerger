package com.wangzhenbang.SubmitMerger.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties jdbcProp;

    public static Properties getJDBCProp() {
        try {
            if(jdbcProp == null) {
                jdbcProp = new Properties();
                System.out.println("1");
                jdbcProp.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config/jdbc.properties"));
                System.out.println("2");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jdbcProp;
    }
}
