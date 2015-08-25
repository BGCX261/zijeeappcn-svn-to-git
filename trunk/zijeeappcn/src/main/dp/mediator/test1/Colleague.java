package com.test.mediator.test1;

public abstract class Colleague {
    /**
     * �����н��߶���ÿһ��ͬ���඼֪�������н��߶���
     */
    private final Mediator mediator;
    /**
     * ���췽���������н��߶���
     * @param mediator �н��߶���
     */
   public Colleague(Mediator mediator){
             this.mediator = mediator;
   }
   /**
    * ��õ�ǰͬ�����Ӧ���н��߶���
    * @return ��Ӧ���н��߶���
    */
   public Mediator getMediator(){
            return mediator;
   }
}