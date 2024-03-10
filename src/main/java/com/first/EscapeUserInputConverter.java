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
        return StringEscapeUtils.escapeXml11(userInput);
    }
}
