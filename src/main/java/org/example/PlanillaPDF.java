package org.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.io.File;


public class PlanillaPDF {
        public static void GenerarPdf(Planilla p, String nombreArchivo) throws IOException {
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage();
                doc.addPage(page);

                File fontFile = new File("src/main/fonts/Oswald-Regular.ttf");
                PDType0Font fontOswald = PDType0Font.load(doc, fontFile);

                try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
                    contentStream.beginText();
                    contentStream.setFont(fontOswald, 14);
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText("Planilla de Lavado ");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.setFont(fontOswald, 12);
                    contentStream.newLineAtOffset(100, 650);
                    contentStream.showText("Nombre: " + p.getNombre());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Lavados: " + p.getCantDeLavados());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Cloro: " + p.getCantidadCloro());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Pintura: " + p.getPintura());
                    contentStream.endText();
                }

                File outFile = new File(nombreArchivo);
                doc.save(outFile);
                System.out.println("PDF generado correctamente: " + outFile.getAbsolutePath());

                // 👇 Abrir el PDF automáticamente
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(outFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

