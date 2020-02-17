package us.gonet.jxiserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.config.Properties;
import us.gonet.jxiserver.config.PropertiesServices;

import java.lang.reflect.Method;

@Component
public class UrlBuilder {

    @Autowired
    PropertiesServices propertiesServices;


    public String buildUrl(String service, String propertiesClass) {

        Object instance = getRestProperties(propertiesClass);
        String protocol = get(instance, "protocol");
        String host = get(instance, "host");
        String port = get(instance, "port");
        String path = get(instance, "path");

        return protocol + host + ":" + port + path + service;
    }

    @SuppressWarnings("unchecked")
    private static <V> V get(Object object, String fieldName)
    {
        Class<?> clazz = object.getClass();
        String methodName;
        while (clazz != null) {
            try {
                clazz.getDeclaredField(fieldName);
                methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Method method = clazz.getMethod(methodName);
                return (V) method.invoke(object);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }

    private Properties getRestProperties(String propertiesClass)
    {
        return (Properties) propertiesServices.getProperties().get(propertiesClass);
    }
}
