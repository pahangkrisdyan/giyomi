package id.giyomi.vms.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.giyomi.vms.backend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JsonLogger {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger;
    public JsonLogger(Class c) {
         this.logger = LoggerFactory.getLogger(c);
    }

    public void log(Object o) throws JsonProcessingException {
        logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
    }

    public void log(String s)  {
        logger.debug(s);
    }

    public void logHeader(String s){
        log("=== " + s.toUpperCase() + "===");
    }
}
