package cn.idev.excel.test.core.simple;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * mock data format for simple read/write
 * <p>
 * Use ExcelProperty {@link ExcelProperty} to mark headers
 * </p>
 *
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class SimpleData {
    @ExcelProperty("姓名")
    private String name;
}
