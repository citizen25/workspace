//������ jsp�� ����ϰ� �־��� controller�μ��� ������  ���� Ŭ������ �и���Ű��
//�׷��� jsp�� ������ �������� �Ǳ� ������ ���� ������ ��ü���� �����ϴ�

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
		//�Ķ���� �ޱ�
		String blood = request.getParameter("blood");
		
		//3�ܰ� : ���� ��Ʈ�ѷ��� �˸��� ���� ��ü���� �� ��Ų��
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		//4�ܰ� : Ŭ���̾�Ʈ���� ������ ����� �����س��´�
		//����� ���� ����� �������� View�� ����ϹǷ�, �� servlet���� ó���ϸ� �ȵȴ�
		//��� jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��� �ʿ䰡 �ִ�
		//���ǿ� ����
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
	}
	
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}

}
