package com.nic.calculate.help.sensitive;

import com.nic.calculate.help.BaseHelp;
import org.springframework.util.StringUtils;

import java.util.function.Function;


public enum SensitiveType {
    DEFAULT(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        if (c.length() < 2) {
            return "***";
        }
        if (c.length() < 4) {
            return c.substring(0, 1) + BaseHelp.padLeft("", "*", 3) + c.substring(c.length() - 1);
        }

        return c.substring(0, 2) + BaseHelp.padLeft("", "*", 3) + c.substring(c.length() - 2);
    }),

    /**
     * 320************211
     */
    ID_CARD(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        if (c.length() < 6) {
            return c;
        }

        return c.substring(0, 3) + BaseHelp.padLeft("", "*", c.length() - 6) + c.substring(c.length() - 3);
    }),

    /**
     * 张*珊 李*
     */
    NAME(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        if (c.length() <= 2) {
            return c.substring(0, 1) + "*";
        }

        return c.substring(0, 1) + BaseHelp.padLeft("", "*", c.length() - 2) + c.substring(c.length() - 1);
    }),

    /**
     * 136****1004
     */
    MOBILE(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        if (c.length() < 7) {
            return c;
        }

        return c.substring(0, 3) + BaseHelp.padLeft("", "*", 4) + c.substring(c.length() - 4);
    }),

    /**
     * jin***ng@cyclone-robotics.com
     */
    EMAIL(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        int index = 0;
        if (c.length() < 6 && (index = c.indexOf("@")) < 0) {
            return c;
        }

        return c.substring(0, 3) + BaseHelp.padLeft("", "*", 3) + c.substring(c.length() - index - 3);
    }),

    /**
     * 上海市****室
     */
    ADDRESS(c -> {
        if (StringUtils.isEmpty(c)) {
            return c;
        }
        if (c.length() < 5) {
            return c;
        }

        return c.substring(0, 3) + BaseHelp.padLeft("", "*", 3) + c.substring(c.length() - 1);
    }),
    ;

    private Function<String, String> insensitiveMethod;

    public Function<String, String> insensitive() {
        return insensitiveMethod;
    }

    SensitiveType(Function<String, String> insensitiveMethod) {
        this.insensitiveMethod = insensitiveMethod;
    }
}
