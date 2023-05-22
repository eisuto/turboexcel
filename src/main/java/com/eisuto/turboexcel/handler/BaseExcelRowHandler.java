package com.eisuto.turboexcel.handler;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.CharUtil;
import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.model.ExcelColOption;
import com.eisuto.turboexcel.model.ExcelSheetOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基础处理器
 *
 * @author eisuto
 */
@Data
public class BaseExcelRowHandler<T> implements ExcelRowHandler {


    /**
     * 字段缓存信息
     */
    private Map<Field, ExcelCol> fieldCacheMap;


    /**
     * 泛型类
     */
    private Class<T> clazz;

    /**
     * 列选项
     */
    private Map<Integer, ExcelColOption> colOptionMap;

    /**
     * 成功数据
     */
    private List<T> successList;


    /**
     * 根据 sheet 选项创建基础 Excel 行处理器实例
     *
     * @param sheetOption sheet 选项
     * @param clazz       行对应的类
     * @return 行处理器实例
     */
    public static <T> BaseExcelRowHandler<T> valueOfOption(ExcelSheetOption sheetOption, Class<T> clazz) {
        BaseExcelRowHandler<T> handler = new BaseExcelRowHandler<>();
        handler.setFieldCacheMap(new HashMap<>(clazz.getFields().length));
        handler.setClazz(clazz);
        handler.setSuccessList(new ArrayList<>(sheetOption.getEstRowSize()));
        handler.setColOptionMap(ExcelColOption.valueOfClass(clazz).stream().collect(Collectors.toMap(ExcelColOption::getIndex, e -> e)));
        // 字段缓存
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelCol.class)) {
                ExcelCol excelCol = field.getDeclaredAnnotation(ExcelCol.class);
                field.setAccessible(true);
                handler.getFieldCacheMap().put(field, excelCol);
            }
        }
        return handler;
    }

    /**
     * 行处理，将列数据映射到对应的对象属性中
     *
     * @param sheetIndex sheet索引
     * @param rowIndex   行索引
     * @param colValues  列值
     */
    @Override
    public void handle(int sheetIndex, long rowIndex, List<Object> colValues) {
        try {
            T data = clazz.newInstance();
            for (Map.Entry<Field, ExcelCol> entry : this.getFieldCacheMap().entrySet()) {
                Field field = entry.getKey();
                ExcelCol excelCol = entry.getValue();
                field.set(data, String.valueOf(colValues.get(excelCol.index())));
            }
            successList.add(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
