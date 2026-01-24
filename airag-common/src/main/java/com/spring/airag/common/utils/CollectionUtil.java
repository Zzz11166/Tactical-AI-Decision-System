package com.spring.airag.common.utils;

import java.util.*;

/**
 * 集合工具类
 */
public class CollectionUtil {

    /**
     * 检查集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 检查集合是否非空
     *
     * @param collection 集合
     * @return 是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 检查Map是否为空
     *
     * @param map Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 检查Map是否非空
     *
     * @param map Map
     * @return 是否非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 创建ArrayList
     *
     * @param elements 元素数组
     * @param <T>      泛型类型
     * @return ArrayList
     */
    @SafeVarargs
    public static <T> List<T> newArrayList(T... elements) {
        List<T> list = new ArrayList<>();
        if (elements != null) {
            Collections.addAll(list, elements);
        }
        return list;
    }

    /**
     * 创建LinkedList
     *
     * @param elements 元素数组
     * @param <T>      泛型类型
     * @return LinkedList
     */
    @SafeVarargs
    public static <T> List<T> newLinkedList(T... elements) {
        List<T> list = new LinkedList<>();
        if (elements != null) {
            Collections.addAll(list, elements);
        }
        return list;
    }

    /**
     * 创建HashSet
     *
     * @param elements 元素数组
     * @param <T>      泛型类型
     * @return HashSet
     */
    @SafeVarargs
    public static <T> Set<T> newHashSet(T... elements) {
        Set<T> set = new HashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    /**
     * 创建LinkedHashSet
     *
     * @param elements 元素数组
     * @param <T>      泛型类型
     * @return LinkedHashSet
     */
    @SafeVarargs
    public static <T> Set<T> newLinkedHashSet(T... elements) {
        Set<T> set = new LinkedHashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    /**
     * 创建TreeSet
     *
     * @param elements 元素数组
     * @param <T>      泛型类型
     * @return TreeSet
     */
    @SafeVarargs
    public static <T extends Comparable<T>> Set<T> newTreeSet(T... elements) {
        Set<T> set = new TreeSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    /**
     * 创建HashMap
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap
     */
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建LinkedHashMap
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return LinkedHashMap
     */
    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建TreeMap
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return TreeMap
     */
    public static <K extends Comparable<K>, V> Map<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    /**
     * 安全获取集合大小
     *
     * @param collection 集合
     * @return 集合大小
     */
    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 安全获取Map大小
     *
     * @param map Map
     * @return Map大小
     */
    public static int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    /**
     * 将数组转换为List
     *
     * @param array 数组
     * @param <T>   泛型类型
     * @return List
     */
    public static <T> List<T> asList(T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(array);
    }

    /**
     * 过滤集合中的元素
     *
     * @param collection 集合
     * @param predicate  过滤条件
     * @param <T>        泛型类型
     * @return 过滤后的List
     */
    public static <T> List<T> filter(Collection<T> collection, java.util.function.Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        if (collection != null) {
            for (T item : collection) {
                if (predicate.test(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * 转换集合中的元素
     *
     * @param collection 集合
     * @param mapper     转换函数
     * @param <T>        输入类型
     * @param <R>        输出类型
     * @return 转换后的List
     */
    public static <T, R> List<R> map(Collection<T> collection, java.util.function.Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        if (collection != null) {
            for (T item : collection) {
                result.add(mapper.apply(item));
            }
        }
        return result;
    }

    /**
     * 查找集合中的第一个满足条件的元素
     *
     * @param collection 集合
     * @param predicate  查找条件
     * @param <T>        泛型类型
     * @return Optional包装的元素
     */
    public static <T> Optional<T> findFirst(Collection<T> collection, java.util.function.Predicate<T> predicate) {
        if (collection != null) {
            for (T item : collection) {
                if (predicate.test(item)) {
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }
}