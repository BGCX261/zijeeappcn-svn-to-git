package com.test.mediator.test1;

    public class CDDriver extends Colleague {

        /**
         * ��������
         */
       private String data;

       public CDDriver(Mediator mediator) {
                 super(mediator);
       }
       /**
        * ��ȡ���̶�ȡ����������
        * @return ��������
        */
       public String getData() {
                return data;
       }
       /**
        * ��ȡ����
        */
       public void readCD(){
                 //����ǰ����Ƶ���ݣ����ź�����Ƶ����
                this.data = "Video Data,Sound Data";
               //֪ͨ���壬�Լ���״̬�����˱仯
                this.getMediator().changed(this);
       }
}

