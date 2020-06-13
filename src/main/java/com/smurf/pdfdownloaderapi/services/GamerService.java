package com.smurf.pdfdownloaderapi.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.smurf.pdfdownloaderapi.Repository.GamerRepository;
import com.smurf.pdfdownloaderapi.model.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GamerService {

    @Autowired
    GamerRepository db;

    public void addGamer(Gamer gamer){
        db.save(gamer);
    }

    public void deleteGamer(String name){
        if(db.existsByName(name)) {
            db.delete(db.findGamerByName(name));
        }
    }

    public List<Gamer> getAllGamers(){
        return (List<Gamer>) db.findAll();
    }

    public ByteArrayInputStream downloadPdf() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] columnWidths = {1f,1f,1f};
        table.setWidths(columnWidths);
        table.addCell("ID");
        table.addCell("Name of Gamer");
        table.addCell("Rank");
        List<Gamer> gamers = (List<Gamer>) db.findAll();

        int count = 1;
        for(Gamer i : gamers){
            table.addCell(Integer.toString(count));
            count = count + 1;
            table.addCell(i.getName());
            table.addCell(i.getRank());
        }
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(table);
        document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GamerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(out.toByteArray());

    }



}
