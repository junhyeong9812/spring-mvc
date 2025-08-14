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

    /**
     * HttpServletRequest역할
     * HTTP요청 메세지를 개발자가 직접 파싱해서 사용해도 되지만, 매우 불편하다.
     * 서블릿은 개발자가 HTTP 요청 메세지를 편리하게 사용할 수 있도록 개발자 대신 HTTP 요청 메세지를 파싱한다.
     * 그리고 그 결과를 HttpServelitRequest객체에 담아서 제공한다.
     *
     * 서블릿은 이런 요청 메세지를 편리하게 요청 메세지를 파싱해주고
     * 꺼내서 사용할 수 있도록 도와준다.
     * 그리고 서블릿 객체로 담아서 사용
     *
     * Start Line
     * - HTTP 메소드
     * - URL
     * - 쿼리스트링
     * - 스키마,프로토콜
     * -헤더
     * - 헤더 조회
     * - 바디
     *  - form 파라미터 형식 조회
     *  - message body 데이터 직접 조회
     *
     *  HttpServletRequest 객체는 추가로 여러가지 부가기능도 함께
     *  제공한다.
     *
     *  *임시 저장소 기능*
     *  해당 HTTP 요청이 시작부터 끝날때까지 유지되는 임시 저장소 기능
     *  - 저장 : request.setAttribute(name,value)
     *  - 조회 : request.getAttribute(name)
     *  각 요청마다 요청메세지가 메모리에서 유지가 된다.
     *  요청 생명주기에서
     *
     *  세션 관리
     *  request.getSession(create: true)
     *
     *  중요!
     *  HttpServletRequest/HttpServletResponse를 사용할 때 가장
     *  중요한 점은 이 객체들이 HTTP요청 메세지,HTTP 응답 메세지를 편리하게
     *  사용하도록 도와주는 객체라는 점이다. 따라서 이 기능에 대해서 깊이 있는 이해를 하렴녀
     *  HTTP 스펙이 제공하는 요청,응답 자체를 이해를 해야된다.
     *  
     * */
}
