package com.proyecto.movibus.backend.servicios;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReporteGeneradorService {

    // Colores MoviBus
    private static final DeviceRgb COLOR_PRIMARIO    = new DeviceRgb(21,  101, 192);
    private static final DeviceRgb COLOR_SECUNDARIO  = new DeviceRgb(66,  165, 245);
    private static final DeviceRgb COLOR_HEADER_FILA = new DeviceRgb(227, 242, 253);
    private static final DeviceRgb COLOR_FILA_PAR    = new DeviceRgb(245, 249, 255);
    private static final DeviceRgb COLOR_BLANCO      = new DeviceRgb(255, 255, 255);

    private final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // PDF

    public byte[] generarPDF(String titulo, String subtitulo,
                             String[] columnas, List<Object[]> datos)
            throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf, PageSize.A4.rotate());
        doc.setMargins(30, 30, 30, 30);

        PdfFont fontBold    = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont fontRegular = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Encabezado principal
        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{1, 3, 1}));
        headerTable.setWidth(UnitValue.createPercentValue(100));

        Cell iconoCell = new Cell()
                .add(new Paragraph("🚌 MoviBus")
                        .setFont(fontBold).setFontSize(18)
                        .setFontColor(COLOR_PRIMARIO))
                .setBorder(Border.NO_BORDER)
                .setPaddingBottom(5);

        Cell tituloCell = new Cell()
                .add(new Paragraph(titulo)
                        .setFont(fontBold).setFontSize(16)
                        .setFontColor(COLOR_PRIMARIO)
                        .setTextAlignment(TextAlignment.CENTER))
                .add(new Paragraph(subtitulo)
                        .setFont(fontRegular).setFontSize(10)
                        .setFontColor(new DeviceRgb(100, 100, 100))
                        .setTextAlignment(TextAlignment.CENTER))
                .setBorder(Border.NO_BORDER);

        Cell fechaCell = new Cell()
                .add(new Paragraph("Generado:")
                        .setFont(fontBold).setFontSize(8)
                        .setFontColor(new DeviceRgb(120, 120, 120))
                        .setTextAlignment(TextAlignment.RIGHT))
                .add(new Paragraph(LocalDateTime.now().format(FORMATO_FECHA))
                        .setFont(fontRegular).setFontSize(8)
                        .setFontColor(new DeviceRgb(100, 100, 100))
                        .setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER);

        headerTable.addCell(iconoCell);
        headerTable.addCell(tituloCell);
        headerTable.addCell(fechaCell);
        doc.add(headerTable);

        // Línea separadora
        Table linea = new Table(UnitValue.createPercentArray(new float[]{1}));
        linea.setWidth(UnitValue.createPercentValue(100));
        linea.addCell(new Cell()
                .setHeight(3)
                .setBackgroundColor(COLOR_PRIMARIO)
                .setBorder(Border.NO_BORDER));
        doc.add(linea);

        doc.add(new Paragraph("\n").setFontSize(4));

        // Tabla de datos
        float[] anchos = new float[columnas.length];
        for (int i = 0; i < columnas.length; i++) anchos[i] = 1;

        Table tabla = new Table(UnitValue.createPercentArray(anchos));
        tabla.setWidth(UnitValue.createPercentValue(100));

        // Cabeceras
        for (String col : columnas) {
            tabla.addHeaderCell(
                    new Cell()
                            .add(new Paragraph(col)
                                    .setFont(fontBold)
                                    .setFontSize(9)
                                    .setFontColor(COLOR_BLANCO)
                                    .setTextAlignment(TextAlignment.CENTER))
                            .setBackgroundColor(COLOR_PRIMARIO)
                            .setBorderBottom(new SolidBorder(COLOR_SECUNDARIO, 2))
                            .setBorderTop(Border.NO_BORDER)
                            .setBorderLeft(Border.NO_BORDER)
                            .setBorderRight(Border.NO_BORDER)
                            .setPadding(6)
            );
        }

        // Filas de datos
        boolean filaPar = false;
        if (datos == null || datos.isEmpty()) {
            tabla.addCell(
                    new Cell(1, columnas.length)
                            .add(new Paragraph("No hay datos disponibles para este reporte.")
                                    .setFont(fontRegular).setFontSize(10)
                                    .setFontColor(new DeviceRgb(150, 150, 150))
                                    .setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                            .setPadding(12)
            );
        } else {
            for (Object[] fila : datos) {
                DeviceRgb colorFila = filaPar ? COLOR_FILA_PAR : COLOR_BLANCO;
                for (Object celda : fila) {
                    String valor = celda != null ? celda.toString() : "—";
                    tabla.addCell(
                            new Cell()
                                    .add(new Paragraph(valor)
                                            .setFont(fontRegular)
                                            .setFontSize(8.5f))
                                    .setBackgroundColor(colorFila)
                                    .setBorderBottom(new SolidBorder(COLOR_HEADER_FILA, 0.5f))
                                    .setBorderTop(Border.NO_BORDER)
                                    .setBorderLeft(Border.NO_BORDER)
                                    .setBorderRight(Border.NO_BORDER)
                                    .setPaddingTop(5).setPaddingBottom(5)
                                    .setPaddingLeft(6).setPaddingRight(6)
                    );
                }
                filaPar = !filaPar;
            }
        }

        doc.add(tabla);

        // Pie de página
        doc.add(new Paragraph("\n").setFontSize(6));
        Table pie = new Table(UnitValue.createPercentArray(new float[]{1, 1}));
        pie.setWidth(UnitValue.createPercentValue(100));
        pie.addCell(new Cell()
                .add(new Paragraph("MoviBus — Sistema de Gestión de Transporte")
                        .setFont(fontRegular).setFontSize(7)
                        .setFontColor(new DeviceRgb(150, 150, 150)))
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new SolidBorder(COLOR_HEADER_FILA, 1)));
        pie.addCell(new Cell()
                .add(new Paragraph("Total de registros: " + (datos != null ? datos.size() : 0))
                        .setFont(fontBold).setFontSize(7)
                        .setFontColor(COLOR_PRIMARIO)
                        .setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new SolidBorder(COLOR_HEADER_FILA, 1)));
        doc.add(pie);

        doc.close();
        return out.toByteArray();
    }

    // EXCEL

    public byte[] generarExcel(String titulo, String subtitulo,
                               String[] columnas, List<Object[]> datos)
            throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(titulo.length() > 31
                ? titulo.substring(0, 31) : titulo);

        // Colores
        XSSFColor azulPrimario    = new XSSFColor(new byte[]{(byte)21,  (byte)101, (byte)192}, null);
        XSSFColor azulSecundario  = new XSSFColor(new byte[]{(byte)66,  (byte)165, (byte)245}, null);
        XSSFColor azulClaro       = new XSSFColor(new byte[]{(byte)227, (byte)242, (byte)253}, null);
        XSSFColor grisClaro       = new XSSFColor(new byte[]{(byte)245, (byte)249, (byte)255}, null);
        XSSFColor blanco          = new XSSFColor(new byte[]{(byte)255, (byte)255, (byte)255}, null);

        // ── Estilos ──
        // Título
        XSSFCellStyle estiloTitulo = wb.createCellStyle();
        estiloTitulo.setFillForegroundColor(azulPrimario);
        estiloTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);
        estiloTitulo.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont fuenteTitulo = wb.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 16);
        fuenteTitulo.setColor(blanco);
        estiloTitulo.setFont(fuenteTitulo);

        // Subtítulo
        XSSFCellStyle estiloSubtitulo = wb.createCellStyle();
        estiloSubtitulo.setFillForegroundColor(azulSecundario);
        estiloSubtitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloSubtitulo.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont fuenteSub = wb.createFont();
        fuenteSub.setFontHeightInPoints((short) 10);
        fuenteSub.setColor(blanco);
        estiloSubtitulo.setFont(fuenteSub);

        // Fecha
        XSSFCellStyle estiloFecha = wb.createCellStyle();
        estiloFecha.setFillForegroundColor(azulClaro);
        estiloFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloFecha.setAlignment(HorizontalAlignment.RIGHT);
        XSSFFont fuenteFecha = wb.createFont();
        fuenteFecha.setFontHeightInPoints((short) 8);
        fuenteFecha.setColor(azulPrimario);
        estiloFecha.setFont(fuenteFecha);

        // Cabecera de columnas
        XSSFCellStyle estiloHeader = wb.createCellStyle();
        estiloHeader.setFillForegroundColor(azulPrimario);
        estiloHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloHeader.setAlignment(HorizontalAlignment.CENTER);
        estiloHeader.setVerticalAlignment(VerticalAlignment.CENTER);
        estiloHeader.setBorderBottom(BorderStyle.MEDIUM);
        estiloHeader.setBottomBorderColor(azulSecundario.getIndex());
        XSSFFont fuenteHeader = wb.createFont();
        fuenteHeader.setBold(true);
        fuenteHeader.setFontHeightInPoints((short) 10);
        fuenteHeader.setColor(blanco);
        estiloHeader.setFont(fuenteHeader);

        // Filas normales
        XSSFCellStyle estiloNormal = wb.createCellStyle();
        estiloNormal.setVerticalAlignment(VerticalAlignment.CENTER);
        estiloNormal.setBorderBottom(BorderStyle.THIN);
        estiloNormal.setBottomBorderColor(azulClaro.getIndex());
        XSSFFont fuenteNormal = wb.createFont();
        fuenteNormal.setFontHeightInPoints((short) 9);
        estiloNormal.setFont(fuenteNormal);

        // Filas pares
        XSSFCellStyle estiloPar = wb.createCellStyle();
        estiloPar.cloneStyleFrom(estiloNormal);
        estiloPar.setFillForegroundColor(grisClaro);
        estiloPar.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Total
        XSSFCellStyle estiloTotal = wb.createCellStyle();
        estiloTotal.setFillForegroundColor(azulClaro);
        estiloTotal.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloTotal.setAlignment(HorizontalAlignment.RIGHT);
        XSSFFont fuenteTotal = wb.createFont();
        fuenteTotal.setBold(true);
        fuenteTotal.setColor(azulPrimario);
        estiloTotal.setFont(fuenteTotal);

        int fila = 0;

        // Fila vacía superior
        sheet.createRow(fila++).setHeightInPoints(8);

        // Título
        Row filaTitulo = sheet.createRow(fila++);
        filaTitulo.setHeightInPoints(30);
        org.apache.poi.ss.usermodel.Cell celdaTitulo = filaTitulo.createCell(0);
        celdaTitulo.setCellValue("🚌  MoviBus — " + titulo);
        celdaTitulo.setCellStyle(estiloTitulo);
        sheet.addMergedRegion(new CellRangeAddress(fila - 1, fila - 1, 0, columnas.length - 1));

        // Subtítulo
        Row filaSubtitulo = sheet.createRow(fila++);
        filaSubtitulo.setHeightInPoints(20);
        org.apache.poi.ss.usermodel.Cell celdaSub = filaSubtitulo.createCell(0);
        celdaSub.setCellValue(subtitulo);
        celdaSub.setCellStyle(estiloSubtitulo);
        sheet.addMergedRegion(new CellRangeAddress(fila - 1, fila - 1, 0, columnas.length - 1));

        // Fecha de generación
        Row filaFecha = sheet.createRow(fila++);
        filaFecha.setHeightInPoints(18);
        org.apache.poi.ss.usermodel.Cell celdaFecha = filaFecha.createCell(0);
        celdaFecha.setCellValue("Generado: " + LocalDateTime.now().format(FORMATO_FECHA));
        celdaFecha.setCellStyle(estiloFecha);
        sheet.addMergedRegion(new CellRangeAddress(fila - 1, fila - 1, 0, columnas.length - 1));

        // Fila vacía
        sheet.createRow(fila++).setHeightInPoints(6);

        // Cabeceras de columnas
        Row filaHeader = sheet.createRow(fila++);
        filaHeader.setHeightInPoints(22);
        for (int i = 0; i < columnas.length; i++) {
            org.apache.poi.ss.usermodel.Cell c = filaHeader.createCell(i);
            c.setCellValue(columnas[i].toUpperCase());
            c.setCellStyle(estiloHeader);
        }

        // Datos
        if (datos == null || datos.isEmpty()) {
            Row filaVacia = sheet.createRow(fila++);
            org.apache.poi.ss.usermodel.Cell c = filaVacia.createCell(0);
            c.setCellValue("No hay datos disponibles.");
            sheet.addMergedRegion(new CellRangeAddress(fila - 1, fila - 1, 0, columnas.length - 1));
        } else {
            boolean par = false;
            for (Object[] dato : datos) {
                Row r = sheet.createRow(fila++);
                r.setHeightInPoints(18);
                for (int i = 0; i < dato.length; i++) {
                    org.apache.poi.ss.usermodel.Cell c = r.createCell(i);
                    String valor = dato[i] != null ? dato[i].toString() : "—";
                    c.setCellValue(valor);
                    c.setCellStyle(par ? estiloPar : estiloNormal);
                }
                par = !par;
            }

            // Fila de total
            Row filaTotal = sheet.createRow(fila);
            filaTotal.setHeightInPoints(20);
            org.apache.poi.ss.usermodel.Cell celdaTotal = filaTotal.createCell(0);
            celdaTotal.setCellValue("Total de registros: " + datos.size());
            celdaTotal.setCellStyle(estiloTotal);
            sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, columnas.length - 1));
        }

        // Autoajustar columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
        }

        wb.write(out);
        wb.close();
        return out.toByteArray();
    }

    // WORD

    public byte[] generarWord(String titulo, String subtitulo,
                              String[] columnas, List<Object[]> datos)
            throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XWPFDocument doc = new XWPFDocument();

        // Márgenes
        org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr sectPr =
                doc.getDocument().getBody().addNewSectPr();
        org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar mar =
                sectPr.addNewPgMar();
        mar.setTop(BigInteger.valueOf(720));
        mar.setBottom(BigInteger.valueOf(720));
        mar.setLeft(BigInteger.valueOf(900));
        mar.setRight(BigInteger.valueOf(900));

        // ── Encabezado ──
        XWPFParagraph parrafoHeader = doc.createParagraph();
        parrafoHeader.setAlignment(ParagraphAlignment.CENTER);
        parrafoHeader.setSpacingAfter(0);

        XWPFRun runLogo = parrafoHeader.createRun();
        runLogo.setText("🚌  MoviBus");
        runLogo.setBold(true);
        runLogo.setFontSize(20);
        runLogo.setColor("1565C0");
        runLogo.addCarriageReturn();
        runLogo.setText(titulo);
        runLogo.setBold(true);
        runLogo.setFontSize(15);
        runLogo.setColor("1565C0");

        XWPFParagraph parrafoSub = doc.createParagraph();
        parrafoSub.setAlignment(ParagraphAlignment.CENTER);
        parrafoSub.setSpacingAfter(0);
        XWPFRun runSub = parrafoSub.createRun();
        runSub.setText(subtitulo);
        runSub.setFontSize(10);
        runSub.setColor("5C6BC0");

        XWPFParagraph parrafoFecha = doc.createParagraph();
        parrafoFecha.setAlignment(ParagraphAlignment.CENTER);
        parrafoFecha.setSpacingAfter(120);
        XWPFRun runFecha = parrafoFecha.createRun();
        runFecha.setText("Generado: " + LocalDateTime.now().format(FORMATO_FECHA));
        runFecha.setFontSize(8);
        runFecha.setColor("888888");

        // Línea separadora
        XWPFParagraph lineaSep = doc.createParagraph();
        lineaSep.setSpacingAfter(200);
        XWPFRun runLinea = lineaSep.createRun();
        runLinea.addBreak();

        // ── Tabla ──
        int numCols = columnas.length;
        int numFilas = (datos == null ? 0 : datos.size()) + 1;
        XWPFTable tabla = doc.createTable(numFilas, numCols);
        tabla.setWidth("100%");

        // Fila de cabeceras
        XWPFTableRow rowHeader = tabla.getRow(0);
        rowHeader.setRepeatHeader(true);
        for (int i = 0; i < columnas.length; i++) {
            XWPFTableCell celda = rowHeader.getCell(i);
            aplicarColorCelda(celda, "1565C0");
            celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

            XWPFParagraph p = celda.getParagraphs().get(0);
            p.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = p.createRun();
            run.setText(columnas[i]);
            run.setBold(true);
            run.setFontSize(9);
            run.setColor("FFFFFF");
        }

        // Filas de datos
        if (datos == null || datos.isEmpty()) {
            XWPFTableRow rowVacia = tabla.getRow(1);
            XWPFTableCell celda = rowVacia.getCell(0);
            celda.setText("No hay datos disponibles.");
        } else {
            for (int f = 0; f < datos.size(); f++) {
                Object[] dato = datos.get(f);
                XWPFTableRow row = tabla.getRow(f + 1);
                String colorFondo = (f % 2 == 0) ? "FFFFFF" : "E3F2FD";

                for (int c = 0; c < dato.length && c < numCols; c++) {
                    XWPFTableCell celda = row.getCell(c);
                    aplicarColorCelda(celda, colorFondo);
                    celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

                    XWPFParagraph p = celda.getParagraphs().get(0);
                    p.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun run = p.createRun();
                    run.setText(dato[c] != null ? dato[c].toString() : "—");
                    run.setFontSize(8);
                    run.setColor("333333");
                }
            }
        }

        // Total de registros
        XWPFParagraph parrafoTotal = doc.createParagraph();
        parrafoTotal.setAlignment(ParagraphAlignment.RIGHT);
        parrafoTotal.setSpacingBefore(200);
        XWPFRun runTotal = parrafoTotal.createRun();
        runTotal.setText("Total de registros: " + (datos != null ? datos.size() : 0));
        runTotal.setBold(true);
        runTotal.setFontSize(9);
        runTotal.setColor("1565C0");

        // Pie de página
        XWPFParagraph pie = doc.createParagraph();
        pie.setAlignment(ParagraphAlignment.CENTER);
        pie.setSpacingBefore(300);
        XWPFRun runPie = pie.createRun();
        runPie.setText("MoviBus — Sistema de Gestión de Transporte");
        runPie.setFontSize(7);
        runPie.setColor("AAAAAA");

        doc.write(out);
        doc.close();
        return out.toByteArray();
    }

    private void aplicarColorCelda(XWPFTableCell celda, String hexColor) {
        CTTcPr tcPr = celda.getCTTc().isSetTcPr()
                ? celda.getCTTc().getTcPr()
                : celda.getCTTc().addNewTcPr();
        CTShd shd = tcPr.isSetShd() ? tcPr.getShd() : tcPr.addNewShd();
        shd.setVal(STShd.CLEAR);
        shd.setColor("auto");
        shd.setFill(hexColor);
    }
}