package com.mimacom.cloud.test.app;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author christoph.huber
 */
@RestController
@RequestMapping("/input")
public class InputController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputController.class);

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.PATCH, RequestMethod.OPTIONS})
    public String log(HttpServletRequest request, @RequestBody(required = false) String content) throws IOException {
        StringBuilder builder = new StringBuilder()
                .append(request.getMethod())
                .append(" ")
                .append(request.getRequestURI());
        if (request.getQueryString() != null) {
            builder.append("?").append(request.getQueryString());
        }
        builder.append(", HEADERS: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            builder.append(headerName + " : " + request.getHeader(headerName) + ", ");
        }
        builder.append("PARAMETERS: ");
        request.getParameterMap().entrySet().stream().forEach(
                entry -> builder
                        .append(entry.getKey())
                        .append(" : ")
                        .append(StringUtils.join(entry.getValue(), ", "))
                        .append(", ")
        );
        builder.append("BODY: ").append(content);
        String string = builder.toString();
        LOGGER.info(string);
        return string;
    }
}
