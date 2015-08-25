package wxz.test.thread;

class Service {
    public static final String sss="sssss";
    public static void main(String[] args) {
        Service service = new Service();
        Bean b1 = new Bean("Bean1");
        Bean b2 = new Bean("Bean2");

        new Action(service, b1).start();
        new Action(service, b1).start();

        new Action(service, b2).start();

    }

    public Bean test(Bean b) {
        System.out.println(System.currentTimeMillis() + "," + b);
        synchronized (b) {
            System.out.print("");
            System.out.println(System.currentTimeMillis() + ",in it2," + b);
        }
        return b;
    }
}

class Action extends Thread {
    private Service obj = null;
    private Bean b;

    public Action(Service obj, Bean b) {
        this.obj = obj;
        this.b = b;
    }

    @Override
    public void run() {
        obj.test(b);
    }
}

class Bean {

    public Object obj = null;

    public Bean() {

    }

    public Bean(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public String toString() {
        return super.toString() + ",Bean [obj=" + obj + "]";
    }

    @Override
    public int hashCode() {
        System.out.println("hashcode");
//        return 100000;
        
//        byte[] b = new byte[]{};
//        return b.hashCode();
        
        return Service.sss.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals");
        return true;
    }
    

}
