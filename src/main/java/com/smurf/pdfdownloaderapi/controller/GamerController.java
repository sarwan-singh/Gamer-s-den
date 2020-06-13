package com.smurf.pdfdownloaderapi.controller;

import com.itextpdf.text.DocumentException;
import com.smurf.pdfdownloaderapi.model.Gamer;
import com.smurf.pdfdownloaderapi.services.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@Controller
public class GamerController {

    @Autowired
    GamerService gamerService;

    @GetMapping("/")
    public String homePage(){
        return "Home.html";
    }

    @PostMapping("/create")
    public String CreateGamer( Gamer gamer){
        gamerService.addGamer(gamer);
        return "redirect:/added";
    }

    @GetMapping("/create")
    public String CreatePage(){
        return "Create.html";
    }

    @GetMapping("/delete")
    public String DeletePage(){
        return "Delete.html";
    }

    @GetMapping("/deleted")
    public String Deleted(){
        return "DeletedSuccessfully.html";
    }

    @GetMapping("/added")
    public String Added(){
        return "AddedSuccessfully.html";
    }

    @PostMapping("/delete")
    public String DeleteGamer(Gamer gamer){
        gamerService.deleteGamer(gamer.getName());
        return "redirect:/deleted";
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> DownloadPdf() throws DocumentException {

        ByteArrayInputStream in = gamerService.downloadPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Gamers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }

}
