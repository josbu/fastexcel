package cn.idev.excel.converters.localdatetime;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import cn.idev.excel.util.DateUtils;
import cn.idev.excel.util.WorkBookUtil;
import java.time.LocalDateTime;

/**
 * LocalDateTime and date converter
 *
 *
 */
public class LocalDateTimeDateConverter implements Converter<LocalDateTime> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(
            LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration)
            throws Exception {
        WriteCellData<?> cellData = new WriteCellData<>(value);
        String format = null;
        if (contentProperty != null && contentProperty.getDateTimeFormatProperty() != null) {
            format = contentProperty.getDateTimeFormatProperty().getFormat();
        }
        WorkBookUtil.fillDataFormat(cellData, format, DateUtils.defaultDateFormat);
        return cellData;
    }
}
