package cn.idev.excel.test.core.fill.annotation;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.test.util.TestFileUtil;
import cn.idev.excel.util.DateUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

/**
 *
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FillAnnotationDataTest {

    private static File file07;
    private static File file03;
    private static File fileTemplate07;
    private static File fileTemplate03;

    @BeforeAll
    public static void init() {
        file07 = TestFileUtil.createNewFile("fillAnnotation07.xlsx");
        file03 = TestFileUtil.createNewFile("fillAnnotation03.xls");
        fileTemplate07 = TestFileUtil.readFile("fill" + File.separator + "annotation.xlsx");
        fileTemplate03 = TestFileUtil.readFile("fill" + File.separator + "annotation.xls");
    }

    @Test
    public void t01ReadAndWrite07() throws Exception {
        readAndWrite(file07, fileTemplate07);
    }

    @Test
    public void t02ReadAndWrite03() throws Exception {
        readAndWrite(file03, fileTemplate03);
    }

    private void readAndWrite(File file, File fileTemplate) throws Exception {
        EasyExcel.write()
                .file(file)
                .head(FillAnnotationData.class)
                .withTemplate(fileTemplate)
                .sheet()
                .doFill(data());

        try (Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            Row row1 = sheet.getRow(1);
            Assertions.assertEquals(2000, row1.getHeight(), 0);
            Cell cell10 = row1.getCell(0);
            Date date = cell10.getDateCellValue();
            Assertions.assertEquals(DateUtils.parseDate("2020-01-01 01:01:01").getTime(), date.getTime());
            String dataFormatString = cell10.getCellStyle().getDataFormatString();
            Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", dataFormatString);
            Cell cell11 = row1.getCell(1);
            Assertions.assertEquals(99.99, cell11.getNumericCellValue(), 2);
            boolean hasMerge = false;
            for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
                if (mergedRegion.getFirstRow() == 1
                        && mergedRegion.getLastRow() == 1
                        && mergedRegion.getFirstColumn() == 2
                        && mergedRegion.getLastColumn() == 3) {
                    hasMerge = true;
                    break;
                }
            }
            Assertions.assertTrue(hasMerge);
            if (sheet instanceof XSSFSheet) {
                XSSFSheet xssfSheet = (XSSFSheet) sheet;
                List<XSSFShape> shapeList = xssfSheet.getDrawingPatriarch().getShapes();
                XSSFShape shape0 = shapeList.get(0);
                Assertions.assertTrue(shape0 instanceof XSSFPicture);
                XSSFPicture picture0 = (XSSFPicture) shape0;
                CTMarker ctMarker0 = picture0.getPreferredSize().getFrom();
                Assertions.assertEquals(1, ctMarker0.getRow());
                Assertions.assertEquals(4, ctMarker0.getCol());
            } else {
                HSSFSheet hssfSheet = (HSSFSheet) sheet;
                List<HSSFShape> shapeList = hssfSheet.getDrawingPatriarch().getChildren();
                HSSFShape shape0 = shapeList.get(0);
                Assertions.assertTrue(shape0 instanceof HSSFPicture);
                HSSFPicture picture0 = (HSSFPicture) shape0;
                HSSFClientAnchor anchor = (HSSFClientAnchor) picture0.getAnchor();
                Assertions.assertEquals(1, anchor.getRow1());
                Assertions.assertEquals(4, anchor.getCol1());
            }
        }
    }

    private List<FillAnnotationData> data() throws Exception {
        List<FillAnnotationData> list = new ArrayList<>();
        FillAnnotationData data = new FillAnnotationData();
        data.setDate(DateUtils.parseDate("2020-01-01 01:01:01"));
        data.setNumber(99.99);
        data.setString1("string1");
        data.setString2("string2");
        data.setImage(TestFileUtil.getPath() + "converter" + File.separator + "img.jpg");
        list.add(data);
        list.add(data);
        list.add(data);
        list.add(data);
        list.add(data);
        return list;
    }
}
