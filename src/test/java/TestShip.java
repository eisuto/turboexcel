import com.eisuto.turboexcel.annotation.ExcelCol;
import com.eisuto.turboexcel.annotation.ExcelSheet;
import lombok.Data;
import lombok.ToString;

/**
 * @author eisuto
 */
@ExcelSheet
@Data
@ToString
public class TestShip {
    @ExcelCol(index = 0)
    private Long id;

    @ExcelCol(index = 1)
    private String name;
}
