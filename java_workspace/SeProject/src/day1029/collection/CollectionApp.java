/*Collection Framework (전제조건: 객체만을 대상으로 한다.)
	- 자바 언어에서는 객체를 모아서 처리할 때 유용한 api를 지원하는데, 이 api를 가리켜 Collection Framework이라 한다.
	    이 api는 java.util에서 지원한다.
	- Collection Framework에서 지원하는 객체의 수는 상당히 많기는 하지만, 모여진 모습에 따라서 이렇게 나뉜다.
	  (1)순서있는 유형 List형 : 배열과 거의 같다. [][][][]
	  	  자바의 배열과 차이
	  		배열)
	  			1. 반드시 생성할 때 크기를 명시해야 한다. 따라서 동적으로 늘어날 수 없다.(고정적)
	  			2. 자료형을 섞어 사용할 수 없다.
	  				ex) int[] arr = new int[5];  //오직 int형만 넣을 수 있다.
	  		리스트)
	  			1. 크기가 자유롭다.
	  			2. 객체 자료형을 섞어서 넣을 수 있다.
	  (2)순서없는 유형 Set형
	  (3)key-value의 유혛 Map형*/

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
		//List형의 최상위 객체인 List는 인터페이스이며, List로써 기본적으로 가져야할 추상메서드가 명시되어 있다. 
		//Generic Type으로 선언하면, Collection Framework에 넣을 수 있는 자료형을 제한할 수 있다!!
		List<JButton> list = new ArrayList<JButton>(3);  //리스트 구조들은 배열과 거의 같다. [][][]
		//js의 배열과 동작 방식이 동일하다.(가변적)
		
		list.add(new JButton("첫버튼"));
		//list.add("사과");
		//list.add("복숭아");
		//list.add("멜론");
		list.add(new JButton("마지막버튼"));
		//System.out.println("리스트의 데이터 수는 "+list.size());  //배열은 length지만, Collection Framework는 모두 size()이용.
		for(int i=0; i<list.size(); i++) {			
			//JButton bt1 = (JButton)list.get(i);  //섞어서 들어가므로, 반환형은 예측할 수 없기 때문에 Object형이 반환된다. 따라서 형변환 해야함.
			JButton bt1 = list.get(i);  //Generic으로 자료형을 제한했으므로, 형변환이 필요 없어졌다!
			System.out.println(bt1.getText());
		}
		
		/*리스트는 중복된 데이터를 허용할까?
			결론) 중복 여부는 따지지 않고 무조건 넣는다.*/
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println("list2의 크기는 " + list2.size());
	}
	public void showSet() {
		//부모의 자료형으로 자식을 선언하는 것도 물론 가능. 하지만 자식의 재산은 가리킬 수 없다. 그러나 "코드가 유연"해진다.
		//Set<String> set = new HashSet<String>();
		
		HashSet<String> set = new HashSet<String>();
		
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		
		System.out.println("HashSet의 크기는 " + set.size());
		//결론) 중복 여부를 따지고 중복을 제거하고 넣는다.
		
		HashSet<String> set2 = new HashSet<String>();
		set2.add("바나나");
		set2.add("포도");
		set2.add("블루베리");
		
		//반복문으로 set2의 모든 데이터를 출력.
		Iterator<String> it = set2.iterator();
		
		while(it.hasNext()) {  //다음 요소가 존재하는지 판단. true인 경우에만  while문 동작.
			String e = it.next();  //다음 요소를 반환.
			System.out.println(e);
		}
	}
	public void showMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("k1", "장미");
		map.put("k2", "튤립");
		map.put("k3", "안개꽃");
		map.put("k3", "할미꽃");
		System.out.println("map의 길이는 " + map.size());
		//결론) key값은 중복을 허용하지 않는다. 따라서 마지막에 넣은 value로 대체되어버린다.
		
		//반복문을 이용해서 모든 꽃을 출력하기.
		Set<String> keySet = map.keySet();  //key만 따로 모으자.
		//Set은 Iterator를 지원하므로, key를 일렬로 늘어뜨리자.
		Iterator keyIter = keySet.iterator();
		
		while(keyIter.hasNext()) {  //true인 동안 key 값에 접근.
			String key = (String)keyIter.next();
			String value = map.get(key);  //맵의 데이터 중 해당 키와 일치하는 데이터 반환.
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
