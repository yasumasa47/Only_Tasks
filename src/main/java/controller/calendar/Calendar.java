package controller.calendar;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalendarDto;
import model.calendar.CalendarBL;

@WebServlet("/Calendar")
public class Calendar extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String s_year=request.getParameter("year");
		String s_month=request.getParameter("month");
		
		CalendarBL logic=new CalendarBL();
		CalendarDto mc=null;
		
		if(s_year != null && s_month != null) {
			int year =Integer.parseInt(s_year);
			int month=Integer.parseInt(s_month);
			if(month==0) {
				month=12;
				year--;
			}
			if(month==13) {
				month=1;
				year++;
			}
			//年と月のクエリパラメーターが来ている場合にはその年月でカレンダーを生成する
			mc=logic.createMyCalendar(year,month);
		}else {
			//クエリパラメータが来ていないときは実行日時のカレンダーを生成する。
			mc=logic.createMyCalendar();
		}
		//リクエストスコープに格納
		request.setAttribute("mc", mc);
		//viewにフォワード
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/Calendar.jsp");
		rd.forward(request, response);
	}
}