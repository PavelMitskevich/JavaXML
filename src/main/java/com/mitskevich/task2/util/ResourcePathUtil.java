package com.mitskevich.task2.util;

import com.mitskevich.task2.exception.CustomParserXmlException;

import java.net.URL;

public class ResourcePathUtil {
    public static String getResourcePath(String resourceName) throws CustomParserXmlException {
        final int pathStartPosition = 6;
        ClassLoader loader = ResourcePathUtil.class.getClassLoader();
        URL resource = loader.getResource(resourceName);
        if (resource == null) {
            throw new CustomParserXmlException("Resource " + resourceName + " is not found");
        }
        return resource.toString().substring(pathStartPosition);
    }
}
