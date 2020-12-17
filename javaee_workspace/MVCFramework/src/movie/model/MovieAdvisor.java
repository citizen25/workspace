package movie.model;

public class MovieAdvisor {
	
	public String getAdvice(String movie) {
		
		String msg = null;
		
		if(movie.equals("미션임파서블5")) {
			msg = "톰크루즈 주연의 대작";
		} else if(movie.equals("스타워즈")) {
			msg = "갈수록 폭망";
		} else if(movie.equals("존윅3")) {
			msg = "1, 2편 모두 흥행 성공";
		} else if(movie.equals("분노의질주")) {		
			msg = "익스트림 액션";
		}
		
		return msg;
		
	}


}
