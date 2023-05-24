package com.eisuto.turboexcel.model;

import com.eisuto.turboexcel.annotation.ExcelSheet;
import com.eisuto.turboexcel.constant.ExcelConstant;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author eisuto
 */
@Data
public class ExcelSheetOption {


    /**
     * sheet 索引
     */
    private int index;

    /**
     * 预估行大小
     */
    private int estRowSize;

    /**
     * 标题行索引
     */
    private int titleRowIndex;

    /**
     * 数据起始行索引
     */
    private int dataRowIndex;

    public ExcelSheetOption() {
        this.index = ExcelConstant.DEFAULT_SHEET_START_INDEX;
        this.estRowSize = ExcelConstant.DEFAULT_SHEET_ESTIMATE_ROW_SIZE;
    }

    /**
     * 根据类构建
     *
     * @param clazz
     * @return
     */
    public static ExcelSheetOption valueOfClass(Class<?> clazz) {
        ExcelSheetOption option = new ExcelSheetOption();
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ExcelSheet) {
                ExcelSheet sheetAnno = (ExcelSheet) annotation;
                option.setEstRowSize(sheetAnno.estRowSize());
                option.setIndex(sheetAnno.index());
                option.setTitleRowIndex(sheetAnno.titleRowIndex());
                option.setDataRowIndex(sheetAnno.dataRowIndex());
            }
        }
        return option;
    }

}
