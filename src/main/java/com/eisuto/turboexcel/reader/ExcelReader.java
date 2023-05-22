package com.eisuto.turboexcel.reader;

import cn.hutool.poi.excel.ExcelUtil;
import com.eisuto.turboexcel.handler.BaseExcelRowHandler;
import com.eisuto.turboexcel.model.ExcelSheetOption;

import java.io.File;
import java.util.List;

/**
 * Excel 读取器
 * @author eisuto
 */
public class ExcelReader {

    private ExcelReader(){}

    /**
     * 读取 Excel 数据并将其转换为对象列表
     * @param file Excel 文件
     * @param clazz 映射类型
     * @return 对象列表
     */
    public static <T> List<T> read(File file, Class<T> clazz) {
        ExcelSheetOption sheetOption = ExcelSheetOption.valueOfClass(clazz);
        BaseExcelRowHandler<T> rowHandler = BaseExcelRowHandler.valueOfOption(sheetOption, clazz);
        ExcelUtil.readBySax(file, sheetOption.getIndex(), rowHandler);
        return rowHandler.getSuccessList();
    }

}
