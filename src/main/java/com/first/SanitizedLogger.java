package com.first;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SanitizedLogger {
    private final Logger logger;

    public SanitizedLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String msg, Object ... args) {
        logger.info(sanitize(msg, args));
    }

    public void error(String msg) {
        logger.error(sanitize(msg));
    }

    // Add other logging methods as needed

//    private String sanitize(String message) {
//        // Apply sanitization logic here
//        return message.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//    }

//    private String sanitize(String message, Object... args) {
//        // Apply sanitization logic to the message and all arguments
//        System.out.println("........................ sanitize inputs ..............");
//        String sanitizedMessage = StringEscapeUtils.escapeHtml4(message);
//        for (Object arg : args) {
//            if (arg != null) {
//                sanitizedMessage = sanitizedMessage.replace("{}", StringEscapeUtils.escapeHtml4(arg.toString()));
//            }
//        }
//        return sanitizedMessage;
//
//    }



    private String sanitize(String msg, Object... args) {
        // Apply sanitization logic to all arguments
        //System.out.println(Arrays.toString(args));

        Object[] sanitizedArgs = Arrays.stream(args)
                .map(arg -> {
                    System.out.println(arg);
                    if (arg instanceof String) {
                        return StringEscapeUtils.escapeHtml4((String) arg);
                    } else {
                        return arg;
                    }
                })
                .toArray();
        //System.out.println(Arrays.toString(sanitizedArgs));
        // Join sanitized arguments into a single string

        for (Object obj : sanitizedArgs) {
            msg = msg.replaceFirst("\\{\\}", String.valueOf(obj));
        }
        return msg;
//        return Arrays.stream(sanitizedArgs)
//                .map(Object::toString)
//                .reduce("", (acc, arg) -> acc + arg);
       // return sanitizedArgs;
    }
}
