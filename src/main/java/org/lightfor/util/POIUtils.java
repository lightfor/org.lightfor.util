package org.lightfor.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * POI工具类
 * Created by Light on 2016/6/21.
 */
public enum  POIUtils {
    INSTANCE;

    public static void writeToFile(OutputStream os, String sheetName, String[] titles, String[] cells, List<Map<String,String>> excelList){
        SXSSFWorkbook  wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet(sheetName);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font font = wb.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
        handleData(titles, cells, excelList, sheet, style, titleRow);
        sheet.setDefaultColumnWidth((short) 20);
        try {
            wb.write(os);
            os.close();
        } catch (Exception e) {
            LogUtils.error("写入异常", e);
        }
        try {
            wb.dispose();
        } catch (Exception e) {
            LogUtils.error("清理临时文件异常",e);
        }

    }

    private static void handleData(String[] titles, String[] cells, List<Map<String, String>> excelList, Sheet sheet, CellStyle style, Row titleRow) {
        for (int i = 0; i < titles.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new XSSFRichTextString(titles[i]));
            cell.setCellStyle(style);
        }
        if(excelList != null){
            for (int i = 0; i < excelList.size(); i++) {
                int maxRow = SpreadsheetVersion.EXCEL2007.getLastRowIndex() - 1;
                if (i < 0 || i > maxRow){
                    return;
                }
                Row tmpRow = sheet.createRow(i + 1);
                for(int j = 0 ; j < cells.length ; j++){
                    tmpRow.createCell(j).setCellValue(excelList.get(i).get(cells[j]));
                }
            }
        }
    }
}
