package com.test.mediator.test1;

/**
 * �н��ߣ��������ͬ�¶����ͨ�Žӿ�
 *
 */
public interface Mediator {

          /**
           * ͬ�¶����������ı��ʱ����֪ͨ�н��ߵķ���
           * ���н���ȥ������Ӧ��������ͬ�¶���Ľ���
           * @param colleague ͬ�¶��������������н��߶���ͨ������ʵ��
           * ȥ��ȡͬ�¶����״̬
           */
         public void changed(Colleague colleague);
}