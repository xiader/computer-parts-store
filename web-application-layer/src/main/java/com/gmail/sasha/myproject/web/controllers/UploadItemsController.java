package com.gmail.sasha.myproject.web.controllers;

import com.gmail.sasha.myproject.service.service.UploadService;
import com.gmail.sasha.myproject.web.util.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadItemsController {

    @Autowired
    private PageProperties pageProperties;
    @Autowired
    private UploadService uploadService;

    @GetMapping
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String displayForm() {
        return "fileupload";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    @ResponseBody
    public String uploadFile(
            @RequestParam(value = "file") MultipartFile file
            //ModelMap modelMap
    ) throws IOException {
        //modelMap.addAttribute("file", file);
        uploadService.uploadByBytes(file.getBytes());
        return "fileupload";
    }
}
