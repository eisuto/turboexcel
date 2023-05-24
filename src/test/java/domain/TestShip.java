package domain;

import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.annotation.ExcelSheet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author eisuto
 */
@ExcelSheet(titleRowIndex = 0, dataRowIndex = 1)
@Data
@ToString
@NoArgsConstructor
public class TestShip {
    /**
     * id
     */
    @ExcelCol(name = "编号")
    private String id;

    /**
     * 名称
     */
    @ExcelCol(name = "名称")
    private String name;


    /**
     * 吨位
     */
    @ExcelCol(name = "吨位")
    private String tonnage;

    /**
     * 下水日期
     */
    @ExcelCol(name = "下水日期")
    private String launchDate;


    public TestShip(String id, String name, String tonnage, String launchDate) {
        this.id = id;
        this.name = name;
        this.tonnage = tonnage;
        this.launchDate = launchDate;
    }
}
