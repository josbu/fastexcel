package cn.idev.excel.test.core.annotation;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.test.core.StyleTestUtils;
import cn.idev.excel.test.util.TestFileUtil;
import cn.idev.excel.util.DateUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AnnotationDataTest {

    private static File file07;
    private static File file03;
    private static File fileCsv;
    private static File fileStyle07;
    private static File fileStyle03;

    @BeforeAll
    public static void init() {
        file07 = TestFileUtil.createNewFile("annotation07.xlsx");
        file03 = TestFileUtil.createNewFile("annotation03.xls");
        fileStyle07 = TestFileUtil.createNewFile("annotationStyle07.xlsx");
        fileStyle03 = TestFileUtil.createNewFile("annotationStyle03.xls");
        fileCsv = TestFileUtil.createNewFile("annotationCsv.csv");
    }

    @Test
    public void t01ReadAndWrite07() throws Exception {
        readAndWrite(file07);
    }

    @Test
    public void t02ReadAndWrite03() throws Exception {
        readAndWrite(file03);
    }

    @Test
    public void t03ReadAndWriteCsv() throws Exception {
        readAndWrite(fileCsv);
    }

    @Test
    public void t11WriteStyle07() throws Exception {
        writeStyle(fileStyle07);
    }

    @Test
    public void t12Write03() throws Exception {
        writeStyle(fileStyle03);
    }

    private void writeStyle(File file) throws Exception {
        EasyExcel.write().file(file).head(AnnotationStyleData.class).sheet().doWrite(dataStyle());

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        Row row0 = sheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        Assertions.assertArrayEquals(new byte[] {-1, 0, -1}, StyleTestUtils.getFillForegroundColor(cell00));
        Assertions.assertArrayEquals(new byte[] {-1, -52, 0}, StyleTestUtils.getFontColor(cell00, workbook));
        Assertions.assertEquals(40, StyleTestUtils.getFontHeightInPoints(cell00, workbook));

        Cell cell01 = row0.getCell(1);
        Assertions.assertArrayEquals(new byte[] {-1, 0, 0}, StyleTestUtils.getFillForegroundColor(cell01));
        Assertions.assertArrayEquals(new byte[] {0, -1, -1}, StyleTestUtils.getFontColor(cell01, workbook));
        Assertions.assertEquals(20, StyleTestUtils.getFontHeightInPoints(cell01, workbook));

        Row row1 = sheet.getRow(1);
        Cell cell10 = row1.getCell(0);
        Assertions.assertArrayEquals(new byte[] {0, -52, -1}, StyleTestUtils.getFillForegroundColor(cell10));
        Assertions.assertArrayEquals(new byte[] {0, 0, -1}, StyleTestUtils.getFontColor(cell10, workbook));
        Assertions.assertEquals(50, StyleTestUtils.getFontHeightInPoints(cell10, workbook));
        Cell cell11 = row1.getCell(1);
        Assertions.assertArrayEquals(new byte[] {0, -128, 0}, StyleTestUtils.getFillForegroundColor(cell11));
        Assertions.assertArrayEquals(new byte[] {-64, -64, -64}, StyleTestUtils.getFontColor(cell11, workbook));
        Assertions.assertEquals(30, StyleTestUtils.getFontHeightInPoints(cell11, workbook));
    }

    private void readAndWrite(File file) throws Exception {
        EasyExcel.write().file(file).head(AnnotationData.class).sheet().doWrite(dataStyle());

        if (file == fileCsv) {
            return;
        }

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        Assertions.assertEquals(50 * 256, sheet.getColumnWidth(0), 0);

        Row row0 = sheet.getRow(0);
        Assertions.assertEquals(1000, row0.getHeight(), 0);

        Row row1 = sheet.getRow(1);
        Assertions.assertEquals(2000, row1.getHeight(), 0);
    }

    private List<AnnotationStyleData> dataStyle() throws Exception {
        List<AnnotationStyleData> list = new ArrayList<>();
        AnnotationStyleData data = new AnnotationStyleData();
        data.setString("string");
        data.setString1("string1");
        list.add(data);
        return list;
    }

    private List<AnnotationData> data() throws Exception {
        List<AnnotationData> list = new ArrayList<>();
        AnnotationData data = new AnnotationData();
        data.setDate(DateUtils.parseDate("2020-01-01 01:01:01"));
        data.setNumber(99.99);
        data.setIgnore("忽略");
        data.setTransientString("忽略");
        list.add(data);
        return list;
    }
}
