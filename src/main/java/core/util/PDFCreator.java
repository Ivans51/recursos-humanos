package core.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFCreator {

    public static PdfPTable tabla;
    public String segundoParrafo = "Este es el segundo y tiene una fuente rara";
    public String arial = "arial";
    public int tamaño = 22;
    public int estilo = Font.ITALIC;
    public BaseColor color = BaseColor.CYAN;

    public PDFCreator() {
    }

    public void crearPDF(String name, String parrafo, int numColumns, PDFTabla pdfTabla) throws FileNotFoundException, DocumentException {

        // Se crea el documento
        Document documento = new Document();
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream(name);
         /*Se asocia el documento al OutputStream y se indica que el espaciado entre
         lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento*/
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        // Se abre el documento.
        documento.open();
        documento.add(new Paragraph(parrafo));
        get(documento, segundoParrafo, arial, tamaño, estilo, color);
        tabla = new PdfPTable(numColumns);
        pdfTabla.addCellTable();
        /*for (int i = 0; i < size; i++) {
            tabla.addCell("celda " + i);
        }*/
        documento.add(tabla);
        documento.close();
        System.out.println("Hola");
    }

    public void get(Document documento, String segundoParrafo, String fuente, int tamaño, int estilo, BaseColor color) throws DocumentException {
        documento.add(new Paragraph(segundoParrafo, FontFactory.getFont(fuente, tamaño, estilo, color)));
    }

    public interface PDFTabla {
        void addCellTable();
    }

    public static PdfPTable getTabla() {
        return tabla;
    }

    public void setSegundoParrafo(String segundoParrafo) {
        this.segundoParrafo = segundoParrafo;
    }

    public void setArial(String arial) {
        this.arial = arial;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    public void setColor(BaseColor color) {
        this.color = color;
    }
}