package event;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiActionListener implements ActionListener{
    // �� �޼���� ������ ��� ��ư�� ������ �Ѿ�´�!
    public void actionPerformed(ActionEvent e){
        /* ������ �߻���Ų �̺�Ʈ�� ���� ��� ������ Event �ν��Ͻ��� ���޵Ǿ����Ƿ�,
        � ��ư�� ���ȴ����� �� �� �ִ�. */
        //System.out.println(e);

        // �̺�Ʈ�� ��ü �޼��� �߿�, �̺�Ʈ�� ����Ų �����͵�(�̺�Ʈ �ҽ�)�� �����ϴ� �޼���
        System.out.println(e.getSource());
        //if(e.getSource() == ){}
    }
}
