package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * /hello로 요청이 들어오면 해당 서블릿에서 처리한다.
 * 서블릿 이름과 url패턴은 겹치면 안된다.
 * 요청이 오면 자동으로 서비스 메서드를 실행.
 * */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //ctrl o로 서비스 호출을 오버라이드를 가져온다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        //호출하면 HTTP요청을 웹 브라우저가 만들어서 던져주면 응답 객체를 사용
        System.out.println("request = " + request);
        System.out.println("response = " + response);
//        request = org.apache.catalina.connector.RequestFacade@2161f22d
//        response = org.apache.catalina.connector.ResponseFacade@723aa10
        //httpSelevet인터페이스에서 여러 was서버가 서블릿 표준 스팩을 구현한 것이며
        //이걸 구현한 구현체가 로그에 찍히는 것
        /**
         * ?뒤에 쿼리 파라미터를 서블릿이 지원해준다.
         * */
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        /**
         * HelloServlet.service
         * request = org.apache.catalina.connector.RequestFacade@3f80db08
         * response = org.apache.catalina.connector.ResponseFacade@183eb82c
         * username = kim
         * 이렇게 조회되는 것을 볼 수있다.
         * 이걸 직접 http스팩을 전부 파싱해서 읽으면 힘든데 이걸
         * 서블릿이 해결해준다.
         * */

        /**
         * 응답은 response에 넣어줘야한다.
         * */
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //위는 헤더정보에 들어간다.
        response.getWriter().write("hello "+username);

    }
    /*요청정보를 운영서버에 남기면 성능저하가 날 수 있다.
    * */
}
