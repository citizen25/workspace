/*Collection Framework (��������: ��ü���� ������� �Ѵ�.)
	- �ڹ� ������ ��ü�� ��Ƽ� ó���� �� ������ api�� �����ϴµ�, �� api�� ������ Collection Framework�̶� �Ѵ�.
	    �� api�� java.util���� �����Ѵ�.
	- Collection Framework���� �����ϴ� ��ü�� ���� ����� ����� ������, ���� ����� ���� �̷��� ������.
	  (1)�����ִ� ���� List�� : �迭�� ���� ����. [][][][]
	  	  �ڹ��� �迭�� ����
	  		�迭)
	  			1. �ݵ�� ������ �� ũ�⸦ ����ؾ� �Ѵ�. ���� �������� �þ �� ����.(������)
	  			2. �ڷ����� ���� ����� �� ����.
	  				ex) int[] arr = new int[5];  //���� int���� ���� �� �ִ�.
	  		����Ʈ)
	  			1. ũ�Ⱑ �����Ӵ�.
	  			2. ��ü �ڷ����� ��� ���� �� �ִ�.
	  (2)�������� ���� Set��
	  (3)key-value�� �� Map��*/

package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;

public class CollectionApp {

	public void showList() {
		//List���� �ֻ��� ��ü�� List�� �������̽��̸�, List�ν� �⺻������ �������� �߻�޼��尡 ��õǾ� �ִ�. 
		//Generic Type���� �����ϸ�, Collection Framework�� ���� �� �ִ� �ڷ����� ������ �� �ִ�!!
		List<JButton> list = new ArrayList<JButton>(3);  //����Ʈ �������� �迭�� ���� ����. [][][]
		//js�� �迭�� ���� ����� �����ϴ�.(������)
		
		list.add(new JButton("ù��ư"));
		//list.add("���");
		//list.add("������");
		//list.add("���");
		list.add(new JButton("��������ư"));
		//System.out.println("����Ʈ�� ������ ���� "+list.size());  //�迭�� length����, Collection Framework�� ��� size()�̿�.
		for(int i=0; i<list.size(); i++) {			
			//JButton bt1 = (JButton)list.get(i);  //��� ���Ƿ�, ��ȯ���� ������ �� ���� ������ Object���� ��ȯ�ȴ�. ���� ����ȯ �ؾ���.
			JButton bt1 = list.get(i);  //Generic���� �ڷ����� ���������Ƿ�, ����ȯ�� �ʿ� ��������!
			System.out.println(bt1.getText());
		}
		
		/*����Ʈ�� �ߺ��� �����͸� ����ұ�?
			���) �ߺ� ���δ� ������ �ʰ� ������ �ִ´�.*/
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println("list2�� ũ��� " + list2.size());
	}
	public void showSet() {
		//�θ��� �ڷ������� �ڽ��� �����ϴ� �͵� ���� ����. ������ �ڽ��� ����� ����ų �� ����. �׷��� "�ڵ尡 ����"������.
		//Set<String> set = new HashSet<String>();
		
		HashSet<String> set = new HashSet<String>();
		
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		
		System.out.println("HashSet�� ũ��� " + set.size());
		//���) �ߺ� ���θ� ������ �ߺ��� �����ϰ� �ִ´�.
		
		HashSet<String> set2 = new HashSet<String>();
		set2.add("�ٳ���");
		set2.add("����");
		set2.add("��纣��");
		
		//�ݺ������� set2�� ��� �����͸� ���.
		Iterator<String> it = set2.iterator();
		
		while(it.hasNext()) {  //���� ��Ұ� �����ϴ��� �Ǵ�. true�� ��쿡��  while�� ����.
			String e = it.next();  //���� ��Ҹ� ��ȯ.
			System.out.println(e);
		}
	}
	public void showMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("k1", "���");
		map.put("k2", "ƫ��");
		map.put("k3", "�Ȱ���");
		map.put("k3", "�ҹ̲�");
		System.out.println("map�� ���̴� " + map.size());
		//���) key���� �ߺ��� ������� �ʴ´�. ���� �������� ���� value�� ��ü�Ǿ������.
		
		//�ݺ����� �̿��ؼ� ��� ���� ����ϱ�.
		Set<String> keySet = map.keySet();  //key�� ���� ������.
		//Set�� Iterator�� �����ϹǷ�, key�� �Ϸķ� �þ�߸���.
		Iterator keyIter = keySet.iterator();
		
		while(keyIter.hasNext()) {  //true�� ���� key ���� ����.
			String key = (String)keyIter.next();
			String value = map.get(key);  //���� ������ �� �ش� Ű�� ��ġ�ϴ� ������ ��ȯ.
			System.out.println(value);
		}
	}
	
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		app.showList();
		app.showSet();
		app.showMap();
	}

}
