package github.tsffish.bedwarskit.util;
/**
 * @author tsffish
 */
public class ConfigErrorHandler {
    public static String cr(String from, String text){
        return "载配置文件时出现错误：" + from + "的配置文件 -> 无法从路径" + text + "找到内容!";
    }
}
