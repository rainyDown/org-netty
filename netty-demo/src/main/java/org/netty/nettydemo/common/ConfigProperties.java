package org.netty.nettydemo.common;

import org.netty.nettydemo.util.IOUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

public class ConfigProperties {

    private String properiesName = "";
    private Properties properties = new Properties();

    public ConfigProperties() {
    }

    public ConfigProperties(String fileName) {
        this.properiesName = fileName;
    }

    protected void loadFromFile() {
        InputStream in = null;
        InputStreamReader ireader = null;

        try {
            String filePath = IOUtil.getResourcePath(this.properiesName);
            in = new FileInputStream(filePath);
            ireader = new InputStreamReader(in, "utf-8");
            this.properties.load(ireader);
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            IOUtil.closeQuietly(ireader);
        }

    }

    public String readProperty(String key) {
        String value = "";
        value = this.properties.getProperty(key);
        return value;
    }

    public String getValue(String key) {
        return this.readProperty(key);
    }

    public int getIntValue(String key) {
        return Integer.parseInt(this.readProperty(key));
    }

    public static ConfigProperties loadFromFile(Class aClass) throws IllegalAccessException {
        ConfigProperties propertiesUtil = null;
        return (ConfigProperties)propertiesUtil;
    }

    public static void loadAnnotations(Class aClass) {
        ConfigProperties configProperties = null;

        try {
            configProperties = loadFromFile(aClass);
            if (null == configProperties) {
                return;
            }

            Field[] var2 = aClass.getDeclaredFields();
        } catch (IllegalAccessException var3) {
            var3.printStackTrace();
        }

    }
}
