package com.mh;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        CreationHelper factory = wb.getCreationHelper();
        Sheet sheet = wb.createSheet();

        Row row   = sheet.createRow(3);
        Cell cell = row.createCell(5);
        cell.setCellValue("F4");
        Drawing drawing = sheet.createDrawingPatriarch();
// When the comment box is visible, have it show in a 1x3 space
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex()+1);
        anchor.setRow1(row.getRowNum());
        anchor.setRow2(row.getRowNum()+3);

// Create the comment and set the text+author
        Comment comment = drawing.createCellComment(anchor);
        RichTextString str = factory.createRichTextString("Hello, World!");
        comment.setString(str);
        comment.setAuthor("Apache POI");
// Assign the comment to the cell
        cell.setCellComment(comment);
        String fname = "comment-xssf.xls";
        if(wb instanceof XSSFWorkbook) fname += "x";
        try (OutputStream out = new FileOutputStream(fname)) {
            wb.write(out);
        }
        wb.close();
    }
}
