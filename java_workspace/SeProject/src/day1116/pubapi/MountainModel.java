package day1116.pubapi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{

	//2���� �迭�� �ƴ϶�, Vector�� �����ؼ� �����Ϳ� �÷����� ó���Ѵ�
	//���ʹ� �÷��� �����ӿ��̴� �迭ó�� ������ ũ�⸦ ������� �ʾƵ� �ȴ�. ����~
	
	Vector<Mountain> data = new Vector<Mountain>();
		//Generic type�Ƿ� �˸´� �ڷ�����?
		//���� �����ͼ� ���̺� ����� ����. ���� ���� ���;� �Ѵ�
	
	//�÷� ���� ������ ��� ����
	Vector<String> columnName = new Vector<String>();
	
	//�÷� ������ ������ ���� ��Ҵ� �����ڿ��� ä���
	public MountainModel() {
		//�÷� ����
		columnName.add("ID");  //�� ���̵�
		columnName.add("�� �̸�");  //�� �̸�
		columnName.add("������");  //�� ������
		columnName.add("����");  //�� ����
		
		//������ �������� �־��(�׽�Ʈ��)
		Mountain mt = new Mountain();
		mt.setMntnid(1);
		mt.setMntnnm("���ǻ�");
		mt.setMntninfopoflc("������");
		mt.setMntninfohght(1000);
	}
	
	//���ڵ� ��(���� ��)�� ������ ���̿��� �������� ��
	public int getRowCount() {
		return data.size();
	}

	//�÷� ���� ������ ���̿��� �������� �ȴ�
	public int getColumnCount() {
		return columnName.size();
	}

	//�÷� ������ ����ϱ� ���� �޼���
	public String getColumnName(int col) {
		return columnName.get(col);  //�迭�� �ƴϹǷ� get(index)�� �����;� �Ѵ�
	}	
	
	public Object getValueAt(int row, int col) {
		//2���� �迭�̳� ���ʹ� ��� �迭�����̹Ƿ� ���ݱ����� [row][col] ���·� �����͸� �����Ͽ���
		//������, ���� ���Ϳ� ����ִ� ������(VO)�� [row]�� ���� ������ ����������
		//[col]�� ���� ������ �Ұ��ϴ�
		//�ذ�å) ���ǹ��� ����Ѵ�
		//��, col�� 0�϶��� ���� ���̵�, 1�϶� �̸�, 2�� ��.... ��� ������ �޾ƾ� �Ѵ�
		//System.out.println("row:"+row+"  col:"+col);  //ȣ��� �÷��� Ȯ���ϱ�
		
		String obj = null;  //�� ���ǿ� ���� ��ȯ�� ������
		Mountain mt = data.get(row);  //row��°���� ���� �ϳ� ������ ����
		if(col == 0) {  //���� ���̵�
			obj = Integer.toString(mt.getMntnid());
		}else if(col == 1) {  //���� �̸�
			obj = mt.getMntnnm();
		}else if(col == 2) {  //���� ��ġ
			obj = mt.getMntninfopoflc();
		}else if(col == 3) {  //���� ����
			obj = Integer.toString(mt.getMntninfohght());
		}
		//�� �޼����� ��ȯ���� ������Ʈ��. ���� ��ü��(String, Integer��)���� ��ȯ�ؾ� �Ѵ�
		//�ٵ� JTable�� ���� ��� �����ʹ� String ����� �� �����Ƿ�, String���� ��ȯ�Ѵ�
		
		return obj;
	}

}
