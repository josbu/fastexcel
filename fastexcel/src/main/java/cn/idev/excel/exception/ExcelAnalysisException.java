package cn.idev.excel.exception;

/**
 *
 */
public class ExcelAnalysisException extends ExcelRuntimeException {

    public ExcelAnalysisException() {}

    public ExcelAnalysisException(String message) {
        super(message);
    }

    public ExcelAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelAnalysisException(Throwable cause) {
        super(cause);
    }
}
