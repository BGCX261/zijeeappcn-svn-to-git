package wxz.test.annotations;

import java.util.Date;

public interface Data {
    public abstract Object getData();
}

@ClazzAnno(key="data2")
class Data2 implements Data {
    @Override
    @MethodAnno
    public Object getData() {
        return 100;
    }
}
@ClazzAnno(key="data1")
class Data1 implements Data {
    @Override
    @MethodAnno
    public Object getData() {
        return new Date();
    }
}
@ClazzAnno(key="data3")
class Data3 implements Data {
    @Override
    @MethodAnno
    public Object getData() {
        return new Date();
    }
}
@ClazzAnno(key="datano")
class DataNo {
    public Object getData() {
        return new Date();
    }
}