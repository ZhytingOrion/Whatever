package com.nic.calculate.help;


import java.util.*;
import java.util.function.Function;


/*
 * 支持数组和List的Lambda表达是处理数据
 * */
public class ArraysLambda {


    /*Array处理*/
    public static <TParam> List<TParam> toList(TParam[] array) {
        return toList(array, null);
    }

    public static <TParam> List<TParam> toList(TParam[] array, Function<TParam, Boolean> condition) {
        List<TParam> list = new ArrayList<>();
        if (array == null || array.length == 0) {
            return list;
        }
        for (TParam t : array) {
            if (condition == null || condition.apply(t)) {
                list.add(t);
            }
        }
        return list;
    }

    public static <TParam> TParam firstOrDefault(TParam[] array) {
        return firstOrDefault(toList(array), f -> true);
    }

    public static <TParam> TParam firstOrDefault(TParam[] array, Function<TParam, Boolean> condition) {
        return firstOrDefault(toList(array), condition);
    }


    public static <TParam> List<TParam> where(TParam[] array, Function<TParam, Boolean> condition) {
        return where(toList(array), condition);
    }

    public static <TParam, TResult> List<TResult> select(TParam[] array) {
        return select(array, c -> (TResult) c);
    }

    public static <TParam, TResult> List<TResult> select(TParam[] array, Function<TParam, TResult> condition) {
        return select(toList(array), condition);
    }

    /*List处理*/
    public static <TParam> TParam firstOrDefault(List<TParam> list) {
        return firstOrDefault(list, f -> true);
    }

    public static <TParam> TParam firstOrDefault(List<TParam> list, Function<TParam, Boolean> condition) {
        TParam result = null;
        if (list == null) {
            return result;
        }

        for (TParam t : list) {
            if (condition == null || condition.apply(t)) {
                return t;
            }
        }

        return result;
    }
    public static <TParam> List<TParam> where(List<TParam> list, Function<TParam, Boolean> condition) {
        List<TParam> result = new ArrayList<>();
        if (list == null) {
            return result;
        }

        for (TParam t : list) {
            if (condition == null || condition.apply(t)) {
                result.add(t);
            }
        }

        return result;
    }

    public static <TParam, TResult> List<TResult> select(List<TParam> list, Function<TParam, TResult> transfer) {
        List<TResult> result = new ArrayList<>();
        if (list == null || transfer == null) {
            return result;
        }

        for (TParam t : list) {
            TResult meta = transfer.apply(t);
            if (meta != null) {
                result.add(meta);
            }
        }
        return result;
    }

    public static <TParam, TResult> List<TResult> select(List<TParam> list) {
        return select(list, c -> (TResult) c);
    }


}
