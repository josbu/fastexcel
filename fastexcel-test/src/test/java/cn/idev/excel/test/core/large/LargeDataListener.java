package cn.idev.excel.test.core.large;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LargeDataListener extends AnalysisEventListener<LargeData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LargeDataListener.class);
    private int count = 0;

    @Override
    public void invoke(LargeData data, AnalysisContext context) {
        if (count == 0) {
            LOGGER.info("First row:{}", JSON.toJSONString(data));
        }
        count++;
        if (count % 100000 == 0) {
            LOGGER.info("Already read:{},{}", count, JSON.toJSONString(data));
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info("Large row count:{}", count);
        if (context.readWorkbookHolder().getExcelType() != ExcelTypeEnum.CSV) {
            Assertions.assertEquals(count, 464509);
        } else {
            Assertions.assertEquals(count, 499999);
        }
    }
}
