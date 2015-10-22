package com.mimacom.cloud.test.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping(value = "/command", produces = "text/plain")
public class CommandController {
    @RequestMapping(method = RequestMethod.GET)
    public String executeCommand(@RequestParam String cmd) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();

        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }
}