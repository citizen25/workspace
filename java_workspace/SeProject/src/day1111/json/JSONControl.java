/*�ܺ��� �����͸� �������� ���, ��κ� XML, JSON ������ �������̴�
	���� �ڹٰ����ڴ� �ڹ� ���� xml, json���� �����͸� �ؼ�, �м�(parsing)�� �� �ִ� �ɷ��� �ʿ��ϴ�*/

package day1111.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONControl {

	public static void main(String[] args) {
		//�ڹ� ��� ���������δ� json ǥ����� ������ �� ����.�߸��� �������� �����Ѵ�
		//���ڿ��� ó���ؾ��Ѵ�
		
		//StringBuffer�� �� ����: String�� �Һ��� Ư¡�� �����Ƿ�, �ʹ� ���� ���ڿ� ����� ������ �ʱ� ���ؼ�
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"name\":\"simin\"");
		sb.append("}");
		
		//sb�� ����� ǥ���, ����json��ü�� �ƴϹǷ�, �Ľ̴ܰ踦 ���� json ��ü�� ��ȯ�ؾ� �Ѵ�
		//JSON �ļ��� �ڹ� ��ü������ �������� �����Ƿ� �ܺ� ���̺귯���� �̿��Ͽ� �Ľ̾����� �õ�����
		//�ڹ� ���� �оߴ� �ַ� ���� ���(���¼ҽ� ����)�� �ܺ� ���̺귯���� ����ġ��ܿ��� ��Ǵ� maven ����Ʈ�� �̿��Ѵ�
		JSONParser jsonParser = new JSONParser();  //������ �м��ϴ� �ļ� ��ü ����
		
		try {
			JSONObject obj = (JSONObject)jsonParser.parse(sb.toString());//�Ľ� ����
			//�Ľ��� �Ϸ�� ���ĺ��ʹ� �� �̻� ���ڿ��� �ƴ�, json ��ü�� ����ϸ� �ȴ�
			System.out.println(obj.get("name"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
