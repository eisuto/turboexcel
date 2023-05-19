package com.eisuto.turboexcel.handler;

import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.model.ExcelColOption;
import com.eisuto.turboexcel.model.ExcelSheetOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
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


    public static <T> BaseExcelRowHandler<T> valueOfOption(ExcelSheetOption sheetOption, Class<T> clazz) {
        BaseExcelRowHandler<T> handler = new BaseExcelRowHandler<>();
        handler.setClazz(clazz);
        // sheet 信息
        handler.setSuccessList(new ArrayList<>(sheetOption.getEstRowSize()));
        // col 信息
        handler.setColOptionMap(ExcelColOption.valueOfClass(clazz).stream().collect(Collectors.toMap(ExcelColOption::getIndex, e -> e)));

        return handler;
    }

    /**
     * 行处理
     *
     * @param sheetIndex
     * @param rowIndex
     * @param colValues
     */
    @Override
    public void handle(int sheetIndex, long rowIndex, List<Object> colValues) {
        try {
            T data = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ExcelCol.class)) {
                    ExcelCol excelCol = field.getDeclaredAnnotation(ExcelCol.class);
                    field.setAccessible(true);
                    field.set(data, colValues.get(excelCol.index()));
                }
            }
            successList.add(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
