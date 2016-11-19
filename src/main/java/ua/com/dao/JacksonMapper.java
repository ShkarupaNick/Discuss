package ua.com.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ShkarupaN on 14.10.2016.
 */
public class JacksonMapper {
    private static ObjectMapper objectMapper;

    private JacksonMapper(){
        super();
    }

    public static ObjectMapper getInstance() {
        if(null==objectMapper){
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
