package com.eisuto.turboexcel.model;

import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.annotation.ExcelSheet;
import com.eisuto.turboexcel.constant.ExcelConstant;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eisuto
 */
@Data
public class ExcelColOption {


    /**
     * 列索引
     */
    private int index;

    /**
     * 列名称
     */
    private String name;

    /**
     * 根据类构建
     *
     * @param clazz
     * @return
     */
    public static List<ExcelColOption> valueOfClass(Class<?> clazz) {
        List<ExcelColOption> options = new ArrayList<>();

        // 根据类属性反射建立 name -> index 的关系
        for (Field field : clazz.getFields()) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ExcelCol) {
                    ExcelCol colAnno = (ExcelCol) annotation;
                    ExcelColOption option = new ExcelColOption();
                    option.setName(colAnno.name());
                    option.setIndex(colAnno.index());
                    options.add(option);
                }
            }
        }

        return options;
    }

}
