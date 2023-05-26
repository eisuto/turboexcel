package io.github.eisuto.turboexcel.handler;

import cn.hutool.poi.excel.sax.handler.RowHandler;

import java.util.List;

/**
 * @author eisuto
 */
public interface ExcelRowHandler extends RowHandler {


    /**
     * 标题行处理
     * @param colValues 列值
     */
    void titleRowHandler(List<Object> colValues);
}
