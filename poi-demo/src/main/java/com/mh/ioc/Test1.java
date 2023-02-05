package com.mh.ioc;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = (XSSFWorkbook)WorkbookFactory.create(Paths.get("workbook.xlsx").toFile());

        List<XSSFName> allNames = workbook.getAllNames();
        XSSFName xssfName = allNames.get(0);
        System.out.println(xssfName.getNameName());
        System.out.println(xssfName.getComment());
        System.out.println(xssfName.getSheetName());
        System.out.println(xssfName.getFunction());
        System.out.println(xssfName.getFunctionGroupId());
        System.out.println(xssfName.getSheetIndex());
        System.out.println(xssfName.getRefersToFormula());


        System.out.println();


    }
}
