package com.online.college.common.util;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 创建json工具类
 * @author 江龙
 * @date
 */
public class JsonUtil {
	
    private static  ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    public static String toJson(Object obj) throws IOException {
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
