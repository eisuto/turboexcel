package domain;

import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.annotation.ExcelSheet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author eisuto
 */
@ExcelSheet
@Data
@ToString
@NoArgsConstructor
public class TestShip {
    /**
     * id
     */
    @ExcelCol(index = 0)
    private String id;

    /**
     * 名称
     */
    @ExcelCol(index = 1)
    private String name;


    /**
     * 吨位
     */
    @ExcelCol(index = 2)
    private String tonnage;

    /**
     * 下水日期
     */
    @ExcelCol(index = 3)
    private String launchDate;


    public TestShip(String id, String name, String tonnage, String launchDate) {
        this.id = id;
        this.name = name;
        this.tonnage = tonnage;
        this.launchDate = launchDate;
    }
}
