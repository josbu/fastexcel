package cn.idev.excel.read.metadata;

/**
 * Read sheet
 *
 */
public class ReadSheet extends ReadBasicParameter {
    /**
     * Starting from 0
     */
    private Integer sheetNo;
    /**
     * sheet name
     */
    private String sheetName;
    /**
     * sheet hidden state
     */
    private boolean sheetHidden;
    /**
     * sheet very hidden state
     */
    private boolean sheetVeryHidden;
    /**
     * The number of rows to read, the default is all, start with 0.
     */
    public Integer numRows;

    public ReadSheet() {}

    public ReadSheet(Integer sheetNo) {
        this.sheetNo = sheetNo;
    }

    public ReadSheet(Integer sheetNo, String sheetName) {
        this.sheetNo = sheetNo;
        this.sheetName = sheetName;
    }

    public ReadSheet(Integer sheetNo, String sheetName, Integer numRows) {
        this.sheetNo = sheetNo;
        this.sheetName = sheetName;
        this.numRows = numRows;
    }

    public Integer getSheetNo() {
        return sheetNo;
    }

    public void setSheetNo(Integer sheetNo) {
        this.sheetNo = sheetNo;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getNumRows() {
        return numRows;
    }

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public boolean isHidden() {
        return sheetHidden;
    }

    public void setHidden(boolean sheetHidden) {
        this.sheetHidden = sheetHidden;
    }

    public boolean isVeryHidden() {
        return sheetVeryHidden;
    }

    public void setVeryHidden(boolean sheetVeryHidden) {
        this.sheetVeryHidden = sheetVeryHidden;
    }

    public void copyBasicParameter(ReadSheet other) {
        if (other == null) {
            return;
        }
        this.setHeadRowNumber(other.getHeadRowNumber());
        this.setCustomReadListenerList(other.getCustomReadListenerList());
        this.setHead(other.getHead());
        this.setClazz(other.getClazz());
        this.setCustomConverterList(other.getCustomConverterList());
        this.setAutoTrim(other.getAutoTrim());
        this.setUse1904windowing(other.getUse1904windowing());
        this.setNumRows(other.getNumRows());
        this.setHidden(other.isHidden());
        this.setVeryHidden(other.isVeryHidden());
    }

    @Override
    public String toString() {
        return "ReadSheet{" + "sheetNo=" + sheetNo + ", sheetName='" + sheetName + ", sheetHidden='" + sheetHidden
                + ", sheetVeryHidden='" + sheetVeryHidden + '\'' + "} " + super.toString();
    }
}
