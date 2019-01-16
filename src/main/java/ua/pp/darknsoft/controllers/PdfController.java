package ua.pp.darknsoft.controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class PdfController {

    @RequestMapping(value = "/getpdf", method = RequestMethod.GET)
    public void getPdf(HttpServletResponse response) throws IOException {
        /*
        System.out.println(page.getMediaBox().getHeight());
        System.out.println(page.getMediaBox().getWidth());

        842.0
        595.22
        */
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDFont font = PDType1Font.HELVETICA;
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        contentStream.setStrokingColor(Color.RED);
        contentStream.addRect(25,711,570,24);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.setLeading(12f);
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(25,725);
        contentStream.showText("Hello Book Store");
        contentStream.newLine();
        contentStream.showText("Dark&Soft company");
        contentStream.newLine();
        contentStream.showText("Руслянд лэнгвич");
        contentStream.endText();

        contentStream.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.save(baos);
        doc.close();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"file.pdf\"");
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }
}
