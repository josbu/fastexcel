package cn.idev.excel.test.core.fill.style;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.metadata.Head;
import cn.idev.excel.test.core.fill.FillData;
import cn.idev.excel.test.util.TestFileUtil;
import cn.idev.excel.util.DateUtils;
import cn.idev.excel.util.ListUtils;
import cn.idev.excel.write.handler.context.CellWriteHandlerContext;
import cn.idev.excel.write.metadata.style.WriteCellStyle;
import cn.idev.excel.write.metadata.style.WriteFont;
import cn.idev.excel.write.style.AbstractVerticalCellStyleStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FillStyleAnnotatedTest {

    private static File FillStyleAnnotated07;
    private static File FillStyleAnnotated03;
    private static File fileStyleTemplate07;
    private static File fileStyleTemplate03;

    @BeforeAll
    public static void init() {
        FillStyleAnnotated07 = TestFileUtil.createNewFile("FillStyleAnnotated07.xlsx");
        FillStyleAnnotated03 = TestFileUtil.createNewFile("FillStyleAnnotated03.xls");
        fileStyleTemplate07 = TestFileUtil.readFile("fill" + File.separator + "style.xlsx");
        fileStyleTemplate03 = TestFileUtil.readFile("fill" + File.separator + "style.xls");
    }

    @Test
    public void t01Fill07() throws Exception {
        fill(FillStyleAnnotated07, fileStyleTemplate07);
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(FillStyleAnnotated07));
        XSSFSheet sheet = workbook.getSheetAt(0);
        t01Fill07check(sheet.getRow(1));
        t01Fill07check(sheet.getRow(2));
    }

    private void t01Fill07check(XSSFRow row) {
        XSSFCell cell0 = row.getCell(0);
        Assertions.assertEquals("Zhang San", cell0.getStringCellValue());
        Assertions.assertEquals(49, cell0.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFFFF00", cell0.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF808000", cell0.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell0.getCellStyle().getFont().getBold());

        XSSFCell cell1 = row.getCell(1);
        Assertions.assertEquals(5.2, cell1.getNumericCellValue(), 1);
        Assertions.assertEquals(0, cell1.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF0000", cell1.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF800000", cell1.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell1.getCellStyle().getFont().getBold());

        XSSFCell cell2 = row.getCell(2);
        Assertions.assertEquals(
                "2020-01-01 01:01:01", DateUtils.format(cell2.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
        Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", cell2.getCellStyle().getDataFormatString());
        Assertions.assertEquals(
                "FF008000", cell2.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF003300", cell2.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell2.getCellStyle().getFont().getBold());

        XSSFCell cell3 = row.getCell(3);
        Assertions.assertEquals("Zhang San is 5.2 years old this year", cell3.getStringCellValue());
        Assertions.assertEquals(0, cell3.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF0000", cell3.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FFEEECE1", cell3.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell3.getCellStyle().getFont().getBold());

        XSSFCell cell4 = row.getCell(4);
        Assertions.assertEquals("{.name} ignored，Zhang San", cell4.getStringCellValue());
        Assertions.assertEquals(0, cell4.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFC00000", cell4.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF000000", cell4.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertFalse(cell4.getCellStyle().getFont().getBold());

        XSSFCell cell5 = row.getCell(5);
        Assertions.assertEquals("Empty", cell5.getStringCellValue());
        Assertions.assertEquals(0, cell5.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFF79646", cell5.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF8064A2", cell5.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertFalse(cell5.getCellStyle().getFont().getBold());
    }

    @Test
    public void t02Fill03() throws Exception {
        fill(FillStyleAnnotated03, fileStyleTemplate03);
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(FillStyleAnnotated03));
        HSSFSheet sheet = workbook.getSheetAt(0);
        t02Fill03check(workbook, sheet.getRow(1));
        t02Fill03check(workbook, sheet.getRow(2));
    }

    private void t02Fill03check(HSSFWorkbook workbook, HSSFRow row) {
        HSSFCell cell0 = row.getCell(0);
        Assertions.assertEquals("Zhang San", cell0.getStringCellValue());
        Assertions.assertEquals(49, cell0.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:FFFF:0",
                cell0.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "8080:8080:0",
                cell0.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell0.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell1 = row.getCell(1);
        Assertions.assertEquals(5.2, cell1.getNumericCellValue(), 1);
        Assertions.assertEquals(0, cell1.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:0:0", cell1.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "8080:0:0",
                cell1.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell1.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell2 = row.getCell(2);
        Assertions.assertEquals(
                "2020-01-01 01:01:01", DateUtils.format(cell2.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
        Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", cell2.getCellStyle().getDataFormatString());
        Assertions.assertEquals(
                "0:8080:0", cell2.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "0:3333:0",
                cell2.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell2.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell3 = row.getCell(3);
        Assertions.assertEquals("Zhang San is 5.2 years old this year", cell3.getStringCellValue());
        Assertions.assertEquals(0, cell3.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:0:0", cell3.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "FFFF:FFFF:9999",
                cell3.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell3.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell4 = row.getCell(4);
        Assertions.assertEquals("{.name} ignored，Zhang San", cell4.getStringCellValue());
        Assertions.assertEquals(0, cell4.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "9999:3333:0",
                cell4.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "3333:3333:3333",
                cell4.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertFalse(cell4.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell5 = row.getCell(5);
        Assertions.assertEquals("Empty", cell5.getStringCellValue());
        Assertions.assertEquals(0, cell5.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "9999:3333:0",
                cell5.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "CCCC:9999:FFFF",
                cell5.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertFalse(cell5.getCellStyle().getFont(workbook).getBold());
    }

    private void fill(File file, File template) throws Exception {
        EasyExcel.write(file, FillStyleAnnotatedData.class)
                .withTemplate(template)
                .sheet()
                .doFill(data());
    }

    private void t11FillStyleHandler07check(XSSFRow row) {
        XSSFCell cell0 = row.getCell(0);
        Assertions.assertEquals("Zhang San", cell0.getStringCellValue());
        Assertions.assertEquals(49, cell0.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFFFF00", cell0.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF808000", cell0.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell0.getCellStyle().getFont().getBold());

        XSSFCell cell1 = row.getCell(1);
        Assertions.assertEquals("5", cell1.getStringCellValue());
        Assertions.assertEquals(0, cell1.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF0000", cell1.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF800000", cell1.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell1.getCellStyle().getFont().getBold());

        XSSFCell cell2 = row.getCell(2);
        Assertions.assertEquals(
                "2020-01-01 01:01:01", DateUtils.format(cell2.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
        Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", cell2.getCellStyle().getDataFormatString());
        Assertions.assertEquals(
                "FF008000", cell2.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF003300", cell2.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell2.getCellStyle().getFont().getBold());

        XSSFCell cell3 = row.getCell(3);
        Assertions.assertEquals("Zhang San is 5 years old this year", cell3.getStringCellValue());
        Assertions.assertEquals(0, cell3.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FF0000FF", cell3.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF000080", cell3.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell3.getCellStyle().getFont().getBold());

        XSSFCell cell4 = row.getCell(4);
        Assertions.assertEquals("{.name} ignored，Zhang San", cell4.getStringCellValue());
        Assertions.assertEquals(0, cell4.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFFFF00", cell4.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF808000", cell4.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell4.getCellStyle().getFont().getBold());

        XSSFCell cell5 = row.getCell(5);
        Assertions.assertEquals("Empty", cell5.getStringCellValue());
        Assertions.assertEquals(0, cell5.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FF008080", cell5.getCellStyle().getFillForegroundColorColor().getARGBHex());
        Assertions.assertEquals(
                "FF003366", cell5.getCellStyle().getFont().getXSSFColor().getARGBHex());
        Assertions.assertTrue(cell5.getCellStyle().getFont().getBold());
    }

    private void t12FillStyleHandler03check(HSSFWorkbook workbook, HSSFRow row) {
        HSSFCell cell0 = row.getCell(0);
        Assertions.assertEquals("Zhang San", cell0.getStringCellValue());
        Assertions.assertEquals(49, cell0.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:FFFF:0",
                cell0.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "8080:8080:0",
                cell0.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell0.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell1 = row.getCell(1);
        Assertions.assertEquals("5", cell1.getStringCellValue());
        Assertions.assertEquals(0, cell1.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:0:0", cell1.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "8080:0:0",
                cell1.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell1.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell2 = row.getCell(2);
        Assertions.assertEquals(
                "2020-01-01 01:01:01", DateUtils.format(cell2.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
        Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", cell2.getCellStyle().getDataFormatString());
        Assertions.assertEquals(
                "0:8080:0", cell2.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "0:3333:0",
                cell2.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell2.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell3 = row.getCell(3);
        Assertions.assertEquals("Zhang San is 5 years old this year", cell3.getStringCellValue());
        Assertions.assertEquals(0, cell3.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "0:0:FFFF", cell3.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "0:0:8080",
                cell3.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell3.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell4 = row.getCell(4);
        Assertions.assertEquals("{.name} ignored，Zhang San", cell4.getStringCellValue());
        Assertions.assertEquals(0, cell4.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "FFFF:FFFF:0",
                cell4.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "8080:8080:0",
                cell4.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell4.getCellStyle().getFont(workbook).getBold());

        HSSFCell cell5 = row.getCell(5);
        Assertions.assertEquals("Empty", cell5.getStringCellValue());
        Assertions.assertEquals(0, cell5.getCellStyle().getDataFormat());
        Assertions.assertEquals(
                "0:8080:8080",
                cell5.getCellStyle().getFillForegroundColorColor().getHexString());
        Assertions.assertEquals(
                "0:3333:6666",
                cell5.getCellStyle().getFont(workbook).getHSSFColor(workbook).getHexString());
        Assertions.assertTrue(cell5.getCellStyle().getFont(workbook).getBold());
    }

    private void fillStyleHandler(File file, File template) throws Exception {
        EasyExcel.write(file, FillData.class)
                .withTemplate(template)
                .sheet()
                .registerWriteHandler(new AbstractVerticalCellStyleStrategy() {

                    @Override
                    protected WriteCellStyle contentCellStyle(CellWriteHandlerContext context) {
                        WriteCellStyle writeCellStyle = new WriteCellStyle();
                        WriteFont writeFont = new WriteFont();
                        writeCellStyle.setWriteFont(writeFont);
                        writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
                        writeFont.setBold(true);
                        if (context.getColumnIndex() == 0) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                            writeFont.setColor(IndexedColors.DARK_YELLOW.getIndex());
                        }
                        if (context.getColumnIndex() == 1) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                            writeFont.setColor(IndexedColors.DARK_RED.getIndex());
                        }
                        if (context.getColumnIndex() == 2) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                            writeFont.setColor(IndexedColors.DARK_GREEN.getIndex());
                        }
                        if (context.getColumnIndex() == 3) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                            writeFont.setColor(IndexedColors.DARK_BLUE.getIndex());
                        }
                        if (context.getColumnIndex() == 4) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                            writeFont.setColor(IndexedColors.DARK_YELLOW.getIndex());
                        }
                        if (context.getColumnIndex() == 5) {
                            writeCellStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
                            writeFont.setColor(IndexedColors.DARK_TEAL.getIndex());
                        }
                        return writeCellStyle;
                    }

                    @Override
                    protected WriteCellStyle headCellStyle(Head head) {
                        return null;
                    }
                })
                .doFill(data());
    }

    private List<FillStyleAnnotatedData> data() throws Exception {
        List<FillStyleAnnotatedData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            FillStyleAnnotatedData fillData = new FillStyleAnnotatedData();
            list.add(fillData);
            fillData.setName("Zhang San");
            fillData.setNumber(5.2);
            fillData.setDate(DateUtils.parseDate("2020-01-01 01:01:01"));
            if (i == 5) {
                fillData.setName(null);
            }
        }
        return list;
    }
}
