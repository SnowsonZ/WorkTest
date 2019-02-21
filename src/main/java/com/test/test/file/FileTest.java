package com.test.test.file;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/15 15:59
 */
@Slf4j
//@Component
public class FileTest implements ApplicationRunner {

    @Value("${temp.path}")
    private String tempPath;

    public static FileTest of() {
        return new FileTest();
    }

    /**
     * 根据目录及文件名创建文件
     *
     * @param dir  待创建的file目录
     * @param name 待创建的file名称
     */
    public void createFile(String dir, String name) {
        String filePath = dir + "/" + name;
        File file = createTreeFile(filePath);

        // Excel operation
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetOne = workbook.createSheet("20190115");
        HSSFCell cell_1_1 = sheetOne.createRow(0).createCell(0, CellType.NUMERIC);
        cell_1_1.setCellValue(1234);

        //输出到文件
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            log.warn("createFile, create fileOutputStream error, case: {}", e.getMessage());
        } catch (IOException e) {
            log.warn("createFile, Excel write to file error, case: {}", e.getMessage());
            e.printStackTrace();
        }

    }

    public File createTreeFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileTest.of().createFile(tempPath, "20190115.xls");
    }
}
