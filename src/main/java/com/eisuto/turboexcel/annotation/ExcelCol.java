package com.eisuto.turboexcel.annotation;

import com.eisuto.turboexcel.constant.ExcelConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel 列
 *
 * @author eisuto
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCol {

    /**
     * 列名称
     *
     * @return
     */
    String name();

    /**
     * 列索引
     *
     * @return
     */
    int index() default ExcelConstant.DEFAULT_COL_INDEX;
}
