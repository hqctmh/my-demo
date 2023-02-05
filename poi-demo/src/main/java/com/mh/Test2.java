package com.mh;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.handler.context.RowWriteHandlerContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.RichTextString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class Test2 {
    public static void main(String[] args) {

        EasyExcel.write("workbook1.xlsx")
                .sheet("模板")
                .registerWriteHandler(new CellWriteHandler() {
                    @Override
                    public void afterCellCreate(CellWriteHandlerContext context) {
                        CellWriteHandler.super.afterCellCreate(context);
                        Cell cell = context.getCell();
                        String stringCellValue = cell.getStringCellValue();
                        System.out.println();
                    }

                    @Override
                    public void afterCellDataConverted(CellWriteHandlerContext context) {
                        CellWriteHandler.super.afterCellDataConverted(context);
                        Cell cell = context.getCell();
                        String stringCellValue = cell.getStringCellValue();
                        System.out.println();
                    }

                    @Override
                    public void afterCellDispose(CellWriteHandlerContext context) {
                        CellWriteHandler.super.afterCellDispose(context);
                        Cell cell = context.getCell();
                        String stringCellValue = cell.getStringCellValue();
                        cell.setCellValue(stringCellValue+"test");

                        System.out.println();
                    }
                })
                .registerWriteHandler(new RowWriteHandler() {
                    @Override
                    public void beforeRowCreate(RowWriteHandlerContext context) {
                        RowWriteHandler.super.beforeRowCreate(context);
                    }
                });

    }

    private static Supplier<Collection<?>> getCollectionSupplier() {
        return () -> {
            List<List<String>> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                List<String> subList = new ArrayList<>();
                for (int j = 0; j < 100; j++) {
                    subList.add(j + "");
                }
                list.add(subList);
            }
            return list;
        };
    }
}
