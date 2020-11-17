/*외부의 데이터를 제공받을 경우, 대부분 XML, JSON 형태의 데이터이다
	따라서 자바개발자는 자바 언어에서 xml, json등의 데이터를 해석, 분석(parsing)할 수 있는 능력이 필요하다*/

package day1111.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONControl {

	public static void main(String[] args) {
		//자바 언어 내부적으로는 json 표기법을 이해할 수 없다.잘못된 문장으로 이해한다
		//문자열로 처리해야한다
		
		//StringBuffer를 쓴 이유: String은 불변의 특징이 있으므로, 너무 많은 문자열 상수를 만들어내지 않기 위해서
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"name\":\"simin\"");
		sb.append("}");
		
		//sb에 담겨진 표기는, 실제json객체는 아니므로, 파싱단계를 거쳐 json 객체로 전환해야 한다
		//JSON 파서는 자바 자체적으로 지원하지 않으므로 외부 라이브러리를 이용하여 파싱업무를 시도하자
		//자바 개발 분야는 주로 무료 기반(오픈소스 진영)의 외부 라이브러리는 아파치재단에서 운영되는 maven 사이트를 이용한다
		JSONParser jsonParser = new JSONParser();  //구문을 분석하는 파서 객체 생성
		
		try {
			JSONObject obj = (JSONObject)jsonParser.parse(sb.toString());//파싱 시작
			//파싱이 완료된 이후부터는 더 이상 문자열이 아닌, json 객체로 사용하면 된다
			System.out.println(obj.get("name"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
