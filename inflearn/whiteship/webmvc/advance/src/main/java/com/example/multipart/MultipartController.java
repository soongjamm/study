package com.example.multipart;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class MultipartController {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        return "multipart/multipart";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam HttpEntity<MultipartFile> entity, RedirectAttributes attr) {
        MultipartFile file = entity.getBody();
        String msg = file.getOriginalFilename() + " is uploaded";
        attr.addFlashAttribute("msg", msg);
        return "redirect:/upload";
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:my.txt");

        File file = resource.getFile();
        Tika tika = new Tika();
        String contentType = tika.detect(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;") // or inline
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_LENGTH, file.length() +"")
                .body(resource);
    }
}
