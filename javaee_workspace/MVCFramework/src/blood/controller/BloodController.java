//기존의 jsp가 담당하고 있었던 controller로서의 업무를  현재 클래스로 분리시키자
//그래야 jsp는 순수한 디자인이 되기 때문에 유지 보수시 교체까지 가능하다

package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdvisor;

public class BloodController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String blood = request.getParameter("blood");
		
		//3단계 : 하위 컨트롤러는 알맞은 로직 객체에게 일 시킨다
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		//4단계 : 클라이언트에게 전달할 결과를 저장해놓는다
		//결과에 대한 출력은 디자인인 View가 담당하므로, 이 servlet에서 처리하면 안된다
		//결과 jsp에서 메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해놓을 필요가 있다
		//세션에 담자
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
	}
	
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}

}
