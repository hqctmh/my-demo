package com.mh;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook();
        String sheetName = "firstsSheet";
        String safeName = WorkbookUtil.createSafeSheetName(sheetName); // returns " O'Brien's sales   "
        XSSFSheet sheet = (XSSFSheet) wb.createSheet(safeName);

        CTWorksheet ctWorksheet = sheet.getCTWorksheet();
        CTSheetPr ctSheetPr = CTSheetPr.Factory.newInstance();
        ctSheetPr.setCodeName("12");
        ctWorksheet.setSheetPr(ctSheetPr);


        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue("1122");

        CellAddress address = cell.getAddress();


        for (int i = 1; i <= 1000; i++) {
            Name namedCell = wb.createName();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            namedCell.setNameName("_"+uuid);
            namedCell.setComment(i + "");
            String reference = sheetName + "!$" + ITransformation(i) + "$1:$" + ITransformation(i) + "$1"; // area reference
            namedCell.setRefersToFormula(reference);
        }


        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        CreationHelper createHelper = wb.getCreationHelper();
        RichTextString richTextString = createHelper.createRichTextString("This is a string");


        row.createCell(2).setCellValue(richTextString);
        row.createCell(3).setCellValue(true);
        row.createCell(4).setCellValue("This is a string");


        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        }


    }

    /**
     * 方法说明: 数字转列号
     *
     * @param integer
     * @return java.lang.String
     * @version v1.0
     * @author liangalong
     * @date 2022-07-13 17:13
     */
    public static String ITransformation(Integer integer) {
        int len = 0, power = 1, pre = 0;
        for (; pre < integer; pre += power) {
            power *= 26;
            len++;
        }
        char[] excelNum = new char[len];
        integer -= pre - power;
        integer--;
        for (int i = 0; i < len; i++) {
            power /= 26;
            excelNum[i] = (char) (integer / power + 'A');
            integer %= power;
        }
        return String.valueOf(excelNum);
    }


    /**
     * 方法说明: 列号转数字
     *
     * @param s
     * @return java.lang.String
     * @version v1.0
     * @author liangalong
     * @date 2022-07-13 17:14
     */
    public String CTransformation(String s) {
        char[] chars = s.toUpperCase(Locale.ROOT).toCharArray();
        int i = 0;
        for (char c : chars) {
            i = i * 26 + (c - 'A' + 1);
        }
        return String.valueOf(i);
    }

}
