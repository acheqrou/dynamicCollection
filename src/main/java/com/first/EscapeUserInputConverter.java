package com.first;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.text.StringEscapeUtils;

public class EscapeUserInputConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        String userInput = event.getFormattedMessage();
        // Perform escaping logic here
       // return StringEscapeUtils.escapeHtml4(userInput);
//        System.out.println("...................... start ...................");
//        System.out.println(event);
//        System.out.println(event.getLevel());
//        System.out.println(event.getLoggerName());
//        System.out.println(Arrays.toString(event.getArgumentArray()) );
//        System.out.println("Message :");
//        System.out.println(event.getMessage());
//        System.out.println(userInput);
//        System.out.println("...................... ends ...................");
        return StringEscapeUtils.escapeHtml4(userInput);
    }
}
