package edu.uic.cs.postalservice.dao;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 10/26/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Converter {

    private String pojo2Json(Object obj) throws JAXBException,
            JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(obj);
        return jsonString;
    }
}
