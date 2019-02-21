package com.test.test.excel;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/20 14:42
 * @Description:
 */
@Slf4j
public class POIExcelTest {
    public static void main(String[] args) throws IOException {
//        new POIExcelTest().createListBox(new String[]{"123", "456", "abc", "def"});
//        String templatePath = "res/batch-template.xlsx";
//        new POIExcelTest().buildDataWithTemple(templatePath);
//        new POIExcelTest().createCustomConstraint();
//        new POIExcelTest().createWorkBook();

//        POIExcelTest instance = new POIExcelTest();
//        Workbook workbook = instance.createWorkBook("name");
//        Cell cell = workbook.getSheet("name").getRow(0).getCell(0);
//        CellStyle cellStyle = cell.getCellStyle();
//        cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
//        cell.setCellStyle(cellStyle);
//        instance.writeToFile(workbook, "style.xlsx");

        new POIExcelTest().readTemplate("number_constraint.xls");
    }

    public void createCustomConstraint() {
        //文件初始化
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("new sheet");
        CellRangeAddressList regions = new CellRangeAddressList(0, 10, 0, 9);
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("[^A-Za-z0-9]");
        sheet.addValidationData(new HSSFDataValidation(regions, constraint));
        writeToFile(wb, "custom_constraint.xls");
    }
    public void createNumberConstraint() {
        //文件初始化
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("new sheet");
        CellRangeAddressList regions = new CellRangeAddressList(0, 10, 0, 9);
        DVConstraint constraint = DVConstraint.createNumericConstraint(DataValidationConstraint.ValidationType.INTEGER, DVConstraint.OperatorType.BETWEEN, "1", "100");
        sheet.addValidationData(new HSSFDataValidation(regions, constraint));
        writeToFile(wb, "number_constraint.xls");

    }


    public void createListBox(String[] list) {

        //文件初始化
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("new sheet");

        //在第一行第一个单元格，插入下拉框
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);

        //普通写入操作
        cell.setCellValue("请选择");//这是实验

        //生成下拉列表
        //只对（0，0）单元格有效
        for (int i = 0; i <= 10; i++) {
            if(i % 3 == 0) {
                CellRangeAddressList regions = new CellRangeAddressList(0, 10, i, i);

                //生成下拉框内容
                DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);

                //绑定下拉框和作用区域
                HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

                //对sheet页生效
                sheet.addValidationData(data_validation);
            }
        }

        writeToFile(wb, "workbook.xls");

        //结束
        System.out.println("Over");

    }

    public void writeToFile(Workbook hfb, String path) {
        //写入文件
        FileOutputStream fileOut;

        try {

            fileOut = new FileOutputStream(path);

            hfb.write(fileOut);

            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void buildDataWithTemple(String templePath) throws IOException {
//        POIFSFileSystem fs = new POIFSFileSystem(getClass().getClassLoader()
//                .getResourceAsStream(templePath));
        InputStream is = Resources.getResource(templePath).openStream();
        XSSFWorkbook wb = new XSSFWorkbook(is);
        wb.iterator().forEachRemaining((sheet) -> {
            if(sheet.getSheetName().equals("Type")) {
                Cell cell_1_1 = sheet.getRow(0).getCell(0);
                String placeholder = cell_1_1.getStringCellValue();
//                cell_1_1.setCellValue(String.format(placeholder, "Application"));
                XSSFFont font = wb.createFont();
                font.setColor(XSSFFont.COLOR_RED);
                XSSFRichTextString richText = new XSSFRichTextString("Application*");
                richText.applyFont( richText.length() - 1, richText.length(), font);
                cell_1_1.setCellValue(richText);
                Cell cell_2_1 = sheet.getRow(1).getCell(0);
                placeholder = cell_2_1.getStringCellValue();
                cell_2_1.setCellValue(String.format(placeholder, "Application", "ip+port"));
            }
            if(sheet.getSheetName().equals("Relationship")) {
                Cell cell_2_1 = sheet.getRow(2).getCell(1);
                String placeholder = cell_2_1.getStringCellValue();
                cell_2_1.setCellValue(String.format(placeholder, "Application"));
            }

        });
        writeToFile(wb, "result_temple.xlsx");
    }

    public void buildExcelWithJXL() throws IOException {
        Configuration config = new Configuration();
        config.setMetaInfoToken("\\\\");

        XLSTransformer transformer = new XLSTransformer(config);

        URL resource = Resources.getResource("template/batch-template.xls");
        ByteSource byteSource = Resources.asByteSource(resource);
        try (InputStream bufferedStream = byteSource.openBufferedStream()) {

        }
    }

    public void partColorFont() {
        HSSFRichTextString richString = new HSSFRichTextString("Hello, World!");

//        richString.applyFont(6, 13, );
    }

    public void createWorkBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetType = workbook.createSheet("type");
        XSSFRow row = sheetType.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(1234);
        XSSFCell cell_1_2 = row.createCell(1);
        cell_1_2.setCellValue(3210);
        writeToFile(workbook, "cell_test.xlsx");
    }

    public void readTemplate(String path) throws IOException {
        InputStream is = Resources.getResource(path).openStream();
        HSSFWorkbook workbook = new HSSFWorkbook(is);
//        XSSFWorkbook workbook = new XSSFWorkbook(is);
//        XSSFCell cell = workbook.getSheetAt(0).getRow(0).getCell(0);

        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        if(row == null) {
            row = sheet.createRow(0);
        }
        HSSFCell cell = row.getCell(0);
        if(cell == null) {
            cell = row.createCell(0, CellType.NUMERIC);
        }
        if(cell.getCellType() == CellType.NUMERIC) {
            log.info("Numeric, result: {}", cell.getNumericCellValue());
        }else {
            log.info("Other, result: {}", cell.getStringCellValue());
        }
    }

    public Workbook createWorkBook(String sheetName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("name");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        return workbook;
    }

}
