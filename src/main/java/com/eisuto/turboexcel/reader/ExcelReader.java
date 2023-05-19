package com.eisuto.turboexcel.reader;

import cn.hutool.poi.excel.ExcelUtil;
import com.eisuto.turboexcel.handler.BaseExcelRowHandler;
import com.eisuto.turboexcel.model.ExcelSheetOption;

import java.io.File;
import java.util.List;

/**
 * @author eisuto
 */
public class ExcelReader {

    public static <T> List<T> read(File file, Class<T> clazz) {
        ExcelSheetOption sheetOption = ExcelSheetOption.valueOfClass(clazz);
        BaseExcelRowHandler<T> rowHandler = BaseExcelRowHandler.valueOfOption(sheetOption, clazz);
        ExcelUtil.readBySax(file, sheetOption.getIndex(), rowHandler);
        return rowHandler.getSuccessList();
    }

}
