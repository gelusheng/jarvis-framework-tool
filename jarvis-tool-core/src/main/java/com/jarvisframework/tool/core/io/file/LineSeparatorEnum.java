package com.jarvisframework.tool.core.io.file;

/**
 * 换行符枚举<br>
 * 换行符包括：
 * <pre>
 * Mac系统换行符："\r"
 * Linux系统换行符："\n"
 * Windows系统换行符："\r\n"
 * </pre>
 *
 * @author 王涛
 * @see #MAC
 * @see #LINUX
 * @see #WINDOWS
 * @since 1.0, 2020-08-03 10:48:06
 */
public enum LineSeparatorEnum {
    /**
     * Mac系统换行符："\r"
     */
    MAC("\r"),
    /**
     * Linux系统换行符："\n"
     */
    LINUX("\n"),
    /**
     * Windows系统换行符："\r\n"
     */
    WINDOWS("\r\n");

    private final String value;

    LineSeparatorEnum(String lineSeparator) {
        this.value = lineSeparator;
    }

    public String getValue() {
        return this.value;
    }
}
