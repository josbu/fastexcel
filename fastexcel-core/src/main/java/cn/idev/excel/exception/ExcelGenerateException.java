package cn.idev.excel.exception;

/**
 * @author jipengfei
 */
public class ExcelGenerateException extends ExcelRuntimeException {
    
    public ExcelGenerateException(String message) {
        super(message);
    }
    
    public ExcelGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExcelGenerateException(Throwable cause) {
        super(cause);
    }
}
