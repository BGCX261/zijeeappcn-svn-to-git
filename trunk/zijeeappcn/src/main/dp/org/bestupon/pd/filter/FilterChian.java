package org.bestupon.pd.filter;

public class FilterChian extends AbsractFilterChian {
    /**
     * 要实现在一个链条中加入另一个链条，的一个好办法就是使用者一个链条也去实现那个对象的接口
     * 
     * 前面使用的接口编程让 AbsractFilterChian extends Filter 这个个接口，系统会报错。是堆栈溢出问题
     * 也没有细究是什么原因，会报这个异常，就直接更改成为了抽象类，使用 implements 去实现 filter 并且实现 doFilter
     * 方法，省得有那么多的麻烦。
     */
    @Override
    public AbsractFilterChian addAbsractFilterChian(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public String doFilter(String msg) {
        for (Filter filter : filters) {
            msg = filter.doFilter(msg);
        }
        return msg;
    }

}
