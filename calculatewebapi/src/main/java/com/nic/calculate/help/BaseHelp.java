package com.nic.calculate.help;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BaseHelp {

    private static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));


    public static String padLeft(Object source, String c, int totalLength) {
        String data = parse(String.class, source);
        if (data == null) {
            data = "";
        }

        if (data.length() < totalLength) {
            for (int i = 0; i < totalLength - data.length(); i++) {
                data = c + data;
            }
        }

        return data;
    }

    public static <T> T parse(Class<T> clazz, Object obj) {
        try {
            return parse(clazz, obj, null);
        } catch (Exception ex) {
        }
        return null;
    }

    public static <T> T parse(Class<T> clazz, Object obj, Object defaultValue) {
        if (obj == null) {
            obj = "";
        } else {
            if (obj.getClass() == clazz) {
                return (T) obj;
            }
        }

        T result = null;
        String strData = obj.toString();
        if (obj.getClass() == Date.class) {
            strData = formatter.get().format(obj);
        }

        try {
            switch (clazz.getName()) {
                case "java.lang.Integer":
                case "int":
                    if (defaultValue == null) {
                        defaultValue = 0;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    int index = strData.indexOf(".");
                    if (index > 0) {
                        strData = strData.substring(0, index);
                    }

                    result = (T) (Object) Integer.parseInt(strData);
                    break;
                case "java.lang.Long":
                case "long":
                    if (defaultValue == null) {
                        defaultValue = 0L;
                    }

                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    int index1 = strData.indexOf(".");
                    if (index1 > 0) {
                        strData = strData.substring(0, index1);
                    }

                    result = (T) (Object) Long.parseLong(strData);
                    break;
                case "java.lang.Short":
                case "short":
                    if (defaultValue == null) {
                        defaultValue = 0;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    result = (T) (Object) Short.parseShort(strData);
                    break;
                case "java.lang.Float":
                case "float":
                    if (defaultValue == null) {
                        defaultValue = 0f;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    result = (T) (Object) Float.parseFloat(strData);
                    break;
                case "java.lang.Double":
                case "double":
                    if (defaultValue == null) {
                        defaultValue = 0d;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    result = (T) (Object) Double.parseDouble(strData);
                    break;
                case "java.lang.Byte":
                case "byte":
                    if (defaultValue == null) {
                        defaultValue = 0;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    result = (T) (Object) Byte.parseByte(strData);
                    break;
                case "java.lang.Boolean":
                case "boolean":
                    if (defaultValue == null) {
                        defaultValue = false;
                    }
                    if (StringUtils.isEmpty(strData)) {
                        result = (T) (Object) defaultValue;
                        break;
                    }

                    result = (T) (Object) Boolean.parseBoolean(strData);
                    break;
                case "java.util.Date":
                    if (defaultValue == null) {
                        defaultValue = new Date();
                    }
                    if (!StringUtils.isEmpty(strData) && strData.length() >= 10) {
                        if (strData.indexOf("T") > 0) {
                            strData = strData.replace("T", " ");
                        }

                        if (strData.length() == 10) {
                            strData = strData + " 00:00:00.000";
                        } else {
                            if (strData.length() > 24) {
                                strData = strData.substring(0, 23);
                            }
                        }
                        if (strData.indexOf(".") < 0) {
                            strData = strData + ".000";
                        }
                        result = (T) (Object) formatter.get().parse(strData);
                    }
                    break;
                case "java.lang.Char":
                    if (defaultValue == null) {
                        defaultValue = 0;
                    }
                    result = (T) (Object) strData.charAt(0);
                    break;
                case "java.util.UUID":
                    if (defaultValue == null) {
                        defaultValue = UUID.randomUUID();
                    }
                    if (!StringUtils.isEmpty(strData)) {
                        result = (T) (Object) UUID.fromString(strData);
                    }
                    break;
                case "java.lang.String":
                    if (obj.getClass().isLocalClass() || obj.getClass().getName().contains("ArrayList") || obj.getClass().getName().contains("[")) {
                        result = (T) JSON.toJSONString(obj);
                    } else {
                        result = (T) strData;
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
        } finally {
            if (result == null) {
                result = (T) defaultValue;
            }
        }
        return result;
    }


}
