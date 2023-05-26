package io.github.eisuto.turboexcel.handler;

import io.github.eisuto.turboexcel.annotation.ExcelCol;
import io.github.eisuto.turboexcel.model.ExcelColOption;
import io.github.eisuto.turboexcel.model.ExcelSheetOption;
import lombok.Data;

import java.lang.reflect.Field;
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
    private Map<Field, ExcelCol> fieldToColMap;


    /**
     * 泛型类
     */
    private Class<T> clazz;

    /**
     * 列选项
     */
    private Map<String, ExcelColOption> colNameToOptionMap;

    /**
     * sheet 选项
     */
    private ExcelSheetOption sheetOption;

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
        handler.setFieldToColMap(new HashMap<>(clazz.getFields().length));
        handler.setClazz(clazz);
        handler.setSuccessList(new ArrayList<>(sheetOption.getEstRowSize()));
        handler.setSheetOption(sheetOption);
        handler.setColNameToOptionMap(ExcelColOption.valueOfClass(clazz)
                .stream().collect(Collectors.toMap(ExcelColOption::getName, e -> e)));

        // 字段缓存
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelCol.class)) {
                ExcelCol excelCol = field.getDeclaredAnnotation(ExcelCol.class);
                field.setAccessible(true);
                handler.getFieldToColMap().put(field, excelCol);
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
        // 跳过标题行之前的数据
        if (rowIndex < this.sheetOption.getTitleRowIndex()) {
            return;
        }
        // 标题行处理
        else if (rowIndex == this.sheetOption.getTitleRowIndex()) {
            titleRowHandler(colValues);
            return;
        }
        try {
            T data = clazz.newInstance();
            for (Map.Entry<Field, ExcelCol> entry : this.getFieldToColMap().entrySet()) {
                Field field = entry.getKey();
                ExcelCol excelCol = entry.getValue();
                int colIndex = colNameToOptionMap.get(excelCol.name()).getIndex();
                field.set(data, String.valueOf(colValues.get(colIndex)));
            }
            successList.add(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 标题行处理
     * 匹配列选项（colNameToOptionMap）中每个name对应的col索引
     */
    @Override
    public void titleRowHandler(List<Object> colValues) {
        colNameToOptionMap.forEach((name, option) -> {
            option.setIndex(colValues.indexOf(name));
        });
    }
}
