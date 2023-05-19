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
    int estRowSize() default ExcelConstant.ESTIMATE_SHEET_ROW_SIZE;
}
