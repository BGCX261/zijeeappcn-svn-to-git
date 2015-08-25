package org.bestupon.pd.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsractFilterChian implements Filter {
    public List<Filter> filters = new ArrayList<Filter>();

    public abstract String doFilter(String msg);

    public abstract AbsractFilterChian addAbsractFilterChian(Filter filter);
}
