import com.eisuto.turboexcel.annotation.ExcelSheet;
import com.eisuto.turboexcel.reader.ExcelReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author eisuto
 */

public class TestReader {

    @Test
    public void excelReader(){

        List<TestShip> list = ExcelReader.read(new File("C:\\Users\\eisut\\Desktop\\战舰.xlsx"), TestShip.class);

        System.out.println(list.toString());

    }
}
