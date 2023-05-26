import domain.TestShip;
import io.github.eisuto.turboexcel.reader.ExcelReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author eisuto
 */
public class TestReader {

    // 多类型导入测试文件
    // private static final String multiTypeFile = ResourceLoader.class.getClassLoader().getResource("ship.xlsx").getFile();
    private static final List<TestShip> multiTypeList = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() throws Exception {
        multiTypeList.add(new TestShip("1001", "时雨", "2000.24", "1989.2.1"));
        multiTypeList.add(new TestShip("1002", "夕立", "2100.23", "1989.2.2"));
    }

    @Test
    public void excelReader() {
//        System.out.println("【开始读取】");
//        long start = System.currentTimeMillis();
//        List<TestShip> list = ExcelReader.read(new File(multiTypeFile), TestShip.class);
//        System.out.printf("【读取结束】共：%s 条数据，耗时：%d ms%n", list.size(),System.currentTimeMillis() - start);
    }
}
