# TurboExcel
[![version](https://img.shields.io/badge/Version-0.0.1-blueviolet)]()

TurboExcel 是一个解析 Excel 的 Java 框架，以 [SAX 模式](https://en.wikipedia.org/wiki/Simple_API_for_XML) 工作，旨在为您的数据处理提供💪强大的动力和🚀极速的效率，允许您在处理大量数据时保持出色的性能⚡️。


## 快速开始
### 1. 依赖  

~~不引入也可以，但那样就不能用了哦~~ 
```xml
<dependency>
    <groupId>io.github.eisuto</groupId>
    <artifactId>turbo-excel</artifactId>
    <version>0.0.1</version>
</dependency>
```
### 2. 添加注解

~~不加也行，但那样就读不了啦~~ 
```java
@Data
@ExcelSheet
public class Ship {

    @ExcelCol(name = "编号")
    private String id;

    @ExcelCol(name = "名称")
    private String name;

    @ExcelCol(name = "吨位")
    private String tonnage;

    @ExcelCol(name = "下水日期")
    private String launchDate;
}
```
### 3. 读取
~~不读也行，但那样就没有数据啦~~  
```java
List<Ship> ships = ExcelReader.read(youExcelFile, Ship.class);
```
## 贡献
欢迎提出 Issue 或 PR，我们期待你的参与！🎉


## Todo
- [x] 指定列索引读取
- [x] 使用 `String`  作为所有值的数据类型进行读取
- [x] 通过选择标题行，匹配列读取
- [ ] 读取校验
- [ ] 读取和导出一对多关系数据
- [ ] 读取、导出图片
- [ ] 导出

## License
TurboExcel 使用 [MIT License](LICENSE) 发布。
