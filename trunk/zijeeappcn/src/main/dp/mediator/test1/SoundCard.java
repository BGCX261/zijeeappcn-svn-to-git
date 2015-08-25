package com.test.mediator.test1;

/**
 * 声卡类，一个同事类
 *
 */
public class SoundCard extends Colleague {

           public SoundCard(Mediator mediator){
                    super(mediator);
           }
           /**
            * 发出声音
            * @param data 音频数据
            */
           public void soundData(String data){
                    System.out.println("画外音：" + data);
           }
}
