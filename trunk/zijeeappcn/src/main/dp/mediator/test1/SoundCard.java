package com.test.mediator.test1;

/**
 * �����࣬һ��ͬ����
 *
 */
public class SoundCard extends Colleague {

           public SoundCard(Mediator mediator){
                    super(mediator);
           }
           /**
            * ��������
            * @param data ��Ƶ����
            */
           public void soundData(String data){
                    System.out.println("��������" + data);
           }
}
