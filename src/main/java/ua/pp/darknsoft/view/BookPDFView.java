package ua.pp.darknsoft.view;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class BookPDFView extends AbstractITextPdfView {

    private String font = "Times-Bold";

    public BookPDFView() {
    }

    public BookPDFView(String pathToFontFile) {
        this.font = pathToFontFile;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //System.out.println(resourceFile.exists());

        Table table = new Table(new float[]{4, 4, 4});
        table.setWidth(UnitValue.createPercentValue(100));
        PdfFont bold = PdfFontFactory.createFont(font, "CP1251", true);
        // adding header
        table.addHeaderCell(new Cell().add(new Paragraph("First column").setFont(bold)));
        table.addHeaderCell(new Cell().add(new Paragraph("Second column").setFont(bold)));
        table.addHeaderCell(new Cell().add(new Paragraph("Third column").setFont(bold)));
        // adding rows
        for (int i = 0; i < 3; i++) {
            table.addCell(new Paragraph(model.get("mymessage").toString() + i).setFont(bold));
            table.addCell(new Paragraph(model.get("mymessage").toString() + i).setFont(bold));
            table.addCell(new Paragraph(model.get("mymessage").toString() + i).setFont(bold));
        }

        document.add(table);
    }
}
