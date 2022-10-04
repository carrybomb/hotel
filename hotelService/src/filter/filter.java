package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName="filter", urlPatterns="*")
public class filter implements Filter {
	String[] memberList;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("---");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		System.out.println("ServletPath : " + req.getServletPath());
		System.out.println("Object.isNull : " + Objects.isNull(session.getAttribute("memberId")));
		// 리스트에 포함되있다면 true && 세션이 없으면 true
		if(!Arrays.asList(memberList).contains(req.getServletPath()) && Objects.isNull(session.getAttribute("memberId"))) {
			res.sendRedirect("Login.jsp");
			return;
		}
//		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		memberList = new String[]{"/Login.jsp", "/sign_up.jsp", "/find_pw.jsp", "/hotel", "/login", "/index.html", "/admin", "/memberPage.jsp",
								  "/Login.css" , "/find_pw.css", "/listAll.css", "/Header.css", "/find_pw.css", "/Menu.css", "/modify.css", "/Mypage.css", "/sign_up.css", "/roomList.css", "/bookingForm.css", "/bookingComplete.css"};
	}

}
