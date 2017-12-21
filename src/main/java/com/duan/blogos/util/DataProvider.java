package com.duan.blogos.util;

import java.util.Random;

/**
 * Created on 2017/12/21.
 *
 * @author DuanJiaNing
 */
public class DataProvider {

    public char[] data = (" 【参考】枚举类名建议带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。\n" +
            "说明：枚举其实就是特殊的常量类，且构造方法被默认强制是私有。\n" +
            "正例：枚举名字为 ProcessStatusEnum 的成员名称：SUCCESS / UNKOWN_REASON。\n" +
            "16. 【参考】各层命名规约：\n" +
            "A) Service/DAO 层方法命名规约\n" +
            "1） 获取单个对象的方法用 get 做前缀。\n" +
            "2） 获取多个对象的方法用 list 做前缀。\n" +
            "3） 获取统计值的方法用 count 做前缀。\n" +
            "4） 插入的方法用 save/insert 做前缀。\n" +
            "5） 删除的方法用 remove/delete 做前缀。\n" +
            "6） 修改的方法用 update 做前缀。\n" +
            "B) 领域模型命名规约\n" +
            "1） 数据对象：xxxDO，xxx 即为数据表名。\n" +
            "2） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。\n" +
            "3） 展示对象：xxxVO，xxx 一般为网页名称。\n" +
            "4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。\n" +
            "(二)常量定义\n" +
            "1. 【强制】不允许任何魔法值（即未经定义的常量）直接出现在代码中。\n" +
            "反例：String key = \"Id#taobao_\" + tradeId;\n" +
            " cache.put(key, value);\n" +
            "2. 【强制】long 或者 Long 初始赋值时，使用大写的 L，不能是小写的 l，小写容易跟数字 1 混\n" +
            "淆，造成误解。\n" +
            "说明：Long a = 2l; 写的是数字的 21，还是 Long 型的 2?\n" +
            "3. 【推荐】不要使用一个常量类维护所有常量，按常量功能进行归类，分开维护。\n" +
            "说明：大而全的常量类，非得使用查找功能才能定位到修改的常量，不利于理解和维护。\n" +
            "正例：缓存相关常量放在类 CacheConsts 下；系统配置相关常量放在类 ConfigConsts 下。\n" +
            "4. 【推荐】常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、包\n" +
            "内共享常量、类内共享常量。\n" +
            "1） 跨应用共享常量：放置在二方库中，通常是 client.jar 中的 constant 目录下。\n" +
            "2） 应用内共享常量：放置在一方库中，通常是 modules 中的 constant 目录下。\n" +
            "反例：易懂变量也要统一定义成应用内共享常量，两位攻城师在两个类中分别定义了表示\n" +
            "“是”的变量：\n" +
            "阿里巴巴 Java 开发手册\n" +
            " ——禁止用于商业用途，违者必究—— 4 /35\n" +
            "类 A 中：public static final String YES = \"yes\";\n" +
            "类 B 中：public static final String YES = \"y\";\n" +
            "A.YES.equals(B.YES)，预期是 true，但实际返回为 false，导致线上问题。\n" +
            "3） 子工程内部共享常量：即在当前子工程的 constant 目录下。\n" +
            "4） 包内共享常量：即在当前包下单独的 constant 目录下。\n" +
            "5） 类内共享常量：直接在类内部 private static final 定义。\n" +
            "5. 【推荐】如果变量值仅在一个范围内变化，且带有名称之外的延伸属性，定义为枚举类。下面\n" +
            "正例中的数字就是延伸信息，表示星期几。\n" +
            "正例：public Enum { MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6),\n" +
            "SUNDAY(7);}\n" +
            "(三)代码格式\n" +
            "1. 【强制】大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果\n" +
            "是非空代码块则：\n" +
            "1） 左大括号前不换行。\n" +
            "2） 左大括号后换行。\n" +
            "3） 右大括号前换行。\n" +
            "4） 右大括号后还有 else 等代码则不换行；表示终止的右大括号后必须换行。\n" +
            "2. 【强制】 左小括号和字符之间不出现空格；同样，右小括号和字符之间也不出现空格。详见\n" +
            "第 5 条下方正例提示。\n" +
            "反例：if (空格 a == b 空格)\n" +
            "3. 【强制】if/for/while/switch/do 等保留字与括号之间都必须加空格。\n" +
            "4. 【强制】任何二目、三目运算符的左右两边都需要加一个空格。\n" +
            "说明：运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号等。\n" +
            "5. 【强制】采用 4 个空格缩进，禁止使用 tab 字符。\n" +
            "说明：如果使用 tab 缩进，必须设置 1 个 tab 为 4 个空格。IDEA 设置 tab 为 4 个空格时，\n" +
            "请勿勾选 Use tab character；而在 eclipse 中，必须勾选 insert spaces for tabs。\n" +
            "正例： （涉及 1-5 点）\n" +
            "public static void main(String[] args) {\n" +
            "// 缩进 4 个空格\n" +
            "String say = \"hello\";\n" +
            "// 运算符的左右必须有一个空格\n" +
            "int flag = 0; \n" +
            "阿里巴巴 Java 开发手册\n" +
            " ——禁止用于商业用途，违者必究—— 5 /35\n" +
            "// 关键词 if 与括号之间必须有一个空格，括号内的 f 与左括号，0 与右括号不需要空格\n" +
            "if (flag == 0) {\n" +
            "System.out.println(say);\n" +
            "}\n" +
            "\n" +
            "// 左大括号前加空格且不换行；左大括号后换行\n" +
            "if (flag == 1) {\n" +
            "System.out.println(\"world\");\n" +
            "// 右大括号前换行，右大括号后有 else，不用换行\n" +
            "} else {\n" +
            "System.out.println(\"ok\");\n" +
            "// 在右大括号后直接结束，则必须换行\n" +
            "}\n" +
            "}\n" +
            "6. 【强制】注释的双斜线与注释内容之间有且仅有一个空格。\n" +
            "正例：// 注释内容，注意在//和注释内容之间有一个空格。\n" +
            "7. 【强制】单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则：\n" +
            "1） 第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进，参考示例。\n" +
            "2） 运算符与下文一起换行。\n" +
            "3） 方法调用的点符号与下文一起换行。\n" +
            "4） 方法调用时，多个参数，需要换行时，在逗号后进行。\n" +
            "5） 在括号前不要换行，见反例。\n" +
            "正例：\n" +
            "StringBuffer sb = new StringBuffer();\n" +
            "// 超过 120 个字符的情况下，换行缩进 4 个空格，点号和方法名称一起换行\n" +
            "sb.append(\"zi\").append(\"xin\")...\n" +
            ".append(\"huang\")...\n" +
            ".append(\"huang\")...\n" +
            ".append(\"huang\");\n" +
            "反例：\n" +
            "StringBuffer sb = new StringBuffer();\n" +
            "// 超过 120 个字符的情况下，不要在括号前换行\n" +
            "sb.append(\"zi\").append(\"xin\")...append\n" +
            "(\"huang\");\n" +
            "// 参数很多的方法调用可能超过 120 个字符，不要在逗号前换行\n" +
            "method(args1, args2, args3, ...\n" +
            ", argsX);\n" +
            "8. 【强制】方法参数在定义和传入时，多个参数逗号后边必须加空格。\n" +
            "正例：下例中实参的\"a\",后边必须要有一个空格。\n" +
            "method(\"a\", \"b\", \"c\");\n" +
            "9. 【强制】IDE 的 text file encoding 设置为 UTF-8; IDE 中文件的换行符使用 Unix 格式，\n" +
            "不要使用 Windows 格式。\n" +
            "阿里巴巴 Java 开发手册\n" +
            " ——禁止用于商业用途，违者必究—— 6 /35\n" +
            "10. 【推荐】没有必要增加若干空格来使某一行的字符与上一行对应位置的字符对齐。\n" +
            "正例：\n" +
            "int a = 3;\n" +
            "long b = 4L;\n" +
            "float c = 5F;\n" +
            "StringBuffer sb = new StringBuffer();\n" +
            "说明：增加 sb 这个变量，如果需要对齐，则给 a、b、c 都要增加几个空格，在变量比较多的\n" +
            "情况下，是一种累赘的事情。\n" +
            "11. 【推荐】方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义\n" +
            "之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。\n" +
            "说明：没有必要插入多个空行进行隔开。\n" +
            "(四)OOP 规约\n" +
            "1. 【强制】避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成\n" +
            "本，直接用类名来访问即可。\n" +
            "2. 【强制】所有的覆写方法，必须加@Override 注解。\n" +
            "说明：getObject()与 get0bject()的问题。一个是字母的 O，一个是数字的 0，加@Override\n" +
            "可以准确判断是否覆盖成功。另外，如果在抽象类中对方法签名进行修改，其实现类会马上编\n" +
            "译报错。\n" +
            "3. 【强制】相同参数类型，相同业务含义，才可以使用 Java 的可变参数，避免使用 Object。\n" +
            "说明：可变参数必须放置在参数列表的最后。（提倡同学们尽量不用可变参数编程）\n" +
            "正例：public User getUsers(String type, Integer... ids) {...}\n" +
            "4. 【强制】外部正在调用或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生\n" +
            "影响。接口过时必须加@Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么。\n" +
            "5. 【强制】不能使用过时的类或方法。\n" +
            "说明：java.net.URLDecoder 中的方法 decode(String encodeStr) 这个方法已经过时，应\n" +
            "该使用双参数 decode(String source, String encode)。接口提供方既然明确是过时接口，\n" +
            "那么有义务同时提供新的接口；作为调用方来说，有义务去考证过时方法的新实现是什么。\n" +
            "6. 【强制】Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用\n" +
            "equals。\n" +
            "正例：\"test\".equals(object);\n" +
            "反例：object.equals(\"test\");\n" +
            "说明：推荐使用 java.util.Objects#equals（JDK7 引入的工具类）\n" +
            "7. 【强制】所有的相同类型的包装类对象之间值的比较，全部使用 equals 方法比较。\n" +
            "说明：对于 Integer var = ? 在-128 至 127 范围内的赋值，Integer 对象是在\n" +
            "阿里巴巴 Java 开发手册\n" +
            " ——禁止用于商业用途，违者必究—— 7 /35\n" +
            "IntegerCache.cache 产生，会复用已有对象，这个区间内的 Integer 值可以直接使用==进行\n" +
            "判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，\n" +
            "推荐使用 equals 方法进行判断。\n" +
            "8. 关于基本数据类型与包装数据类型的使用标准如下：\n" +
            "1） 【强制】所有的 POJO 类属性必须使用包装数据类型。\n" +
            "2） 【强制】RPC 方法的返回值和参数必须使用包装数据类型。\n" +
            "3） 【推荐】所有的局部变量使用基本数据类型。\n" +
            "说明：POJO 类属性没有初值是提醒使用者在需要使用时，必须自己显式地进行赋值，任何\n" +
            "NPE 问题，或者入库检查，都由使用者来保证。\n" +
            "正例：数据库的查询结果可能是 null，因为自动拆箱，用基本数据类型接收有 NPE 风险。\n" +
            "反例：比如显示成交总额涨跌情况，即正负 x%，x 为基本数据类型，调用的 RPC 服务，调用\n" +
            "不成功时，返回的是默认值，页面显示为 0%，这是不合理的，应该显示成中划线。所以包装\n" +
            "数据类型的 null 值，能够表示额外的信息，如：远程调用失败，异常退出。\n" +
            "9. 【强制】定义 DO/DTO/VO 等 POJO 类时，不要设定任何属性默认值。\n" +
            "反例：POJO 类的 gmtCreate 默认值为 new Date();但是这个属性在数据提取时并没有置入具\n" +
            "体值，在更新其它字段时又附带更新了此字段，导致创建时间被修改成当前时间。\n" +
            "10. 【强制】序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败；如\n" +
            "果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值。\n" +
            "说明：注意 serialVersionUID 不一致会抛出序列化运行时异常。\n" +
            "11. 【强制】构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在 init 方法中。\n" +
            "12. 【强制】POJO 类必须写 toString 方法。使用 IDE 的中工具：source> generate toString\n" +
            "时，如果继承了另一个 POJO 类，注意在前面加一下 super.toString。\n" +
            "说明：在方法执行抛出异常时，可以直接调用 POJO 的 toString()方法打印其属性值，便于排\n" +
            "查问题。\n" +
            "13. 【推荐】使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无\n" +
            "内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。\n" +
            "说明：\n" +
            "String str = \"a,b,c,,\";\n" +
            "String[] ary = str.split(\",\");\n" +
            "// 预期大于 3，结果是 3\n" +
            "System.out.println(ary.length);\n" +
            "14. 【推荐】当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，\n" +
            "便于阅读，此条规则优先于第 15 条规则。\n" +
            "15. 【推荐】 类内方法定义顺序依次是：公有方法或保护方法 > 私有方法 > get").toCharArray();

    private final int size = data.length;

    private final Random random = new Random();

    public String title() {
        return new String(getChars(15));
    }

    public String content() {
        return new String(getChars(100));
    }

    public String summary() {
        return new String(getChars(23));
    }

    public String[] keyWords() {
        int count = random.nextInt(5) + 3;
        String[] ss = new String[count];
        for (int i = 0; i < count; i++) {
            ss[i] = new String(getChars(5));
        }
        return ss;
    }

    private char[] getChars(int count) {
        char[] rs = new char[count];

        for (int i = 0; i < count; i++) {
            rs[i] = data[random.nextInt(size)];
        }

        return rs;
    }


}
