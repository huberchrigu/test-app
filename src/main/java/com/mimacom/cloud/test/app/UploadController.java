package com.mimacom.cloud.test.app;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author christoph.huber
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam(value = "path", required = false, defaultValue = ".") String path) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path + "/" + name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
