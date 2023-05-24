package com.eisuto.turboexcel.annotation;

import com.eisuto.turboexcel.constant.ExcelConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eisuto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

    /**
     * sheet 索引
     *
     * @return
     */
    int index() default ExcelConstant.DEFAULT_SHEET_START_INDEX;

    /**
     * 预估行大小
     *
     * @return
     */
    int estRowSize() default ExcelConstant.DEFAULT_SHEET_ESTIMATE_ROW_SIZE;


    /**
     * 标题行 索引
     *
     * @return
     */
    int titleRowIndex() default ExcelConstant.DEFAULT_SHEET_TITLE_ROW_INDEX;

    /**
     * 数据起始行 索引
     *
     * @return
     */
    int dataRowIndex() default ExcelConstant.DEFAULT_SHEET_DATA_ROW_INDEX;

}
