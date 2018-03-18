package utils;

public class ServletUtils {

    public static String getPathId(String pathInfo) {
        if (pathInfo != null) {
            pathInfo = pathInfo.substring(1, pathInfo.length());
            if (!pathInfo.isEmpty()) {
                return pathInfo.contains("/") ? pathInfo.substring(0, pathInfo.indexOf("/")) : pathInfo;
            }
        }
        return "0";
    }

}
