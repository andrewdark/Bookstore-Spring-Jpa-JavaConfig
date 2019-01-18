package ua.pp.darknsoft.controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.pp.darknsoft.view.BookPDFView;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class PdfController {

    @Value("classpath:times-font.ttf")
    Resource resourceFile;

    @RequestMapping(value = "/pdfbox", method = RequestMethod.GET)
    public void pdfBox(HttpServletResponse response) throws IOException {
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
        contentStream.addRect(25, 711, 570, 24);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.setLeading(12f);
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(25, 725);
        contentStream.showText("Hello Book Store");
        contentStream.newLine();
        contentStream.showText("Dark&Soft company");
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

    @RequestMapping(value = "/itext", method = RequestMethod.GET)
    public BookPDFView iText(Model dasModel) {
        dasModel.addAttribute("mymessage","Ололоев ");
        try {
            System.out.println(resourceFile.getFile().getPath());
            return new BookPDFView(resourceFile.getFile().getPath());
        } catch (IOException e) {
            System.out.println("Font file not found");
           return new BookPDFView();
        }

    }
}
