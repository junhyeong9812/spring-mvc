package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;


@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(RequestHeaderServlet.class);
    /**
     * protected로 된 서비스를 만들어야 된다.
     * public X
     *
     * */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);


    }

    private static void printStartLine(HttpServletRequest request) {
        /**
         * 요청 메세지의 첫줄 파싱
         * */
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET System.out.println("request.getProtocol() = " + request.getProtocol()); //
        //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL()); // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); //username=hi
        System.out.println("request.getQueryString() = " +
                request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---"); System.out.println();
    }
    /**
     * 결과
     * --- REQUEST-LINE - start ---
     * request.getMethod() = GET
     * request.getScheme() = http
     * request.getRequestURL() = http://localhost:8080/request-header
     * request.getRequestURI() = /request-header
     * request.getQueryString() = null
     * request.isSecure() = false
     * --- REQUEST-LINE - end ---
     *
     * */

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            System.out.println("headerName = " + headerName);
//        }
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println("headerName = " + headerName));
        /**
         * asInterator()
         * Enumeration을 Iterator로 변환한다.
         * Interator는 반복자라는 뜻을 가진 인터페이스로
         * 컬렉션의 요소를 하나씩 순회(반복)하기 위한 표준 방식
         * 데이터 집합의 요소를 순차적으로 읽어오는 방법을 제공
         * 한줄평 : 컬렉션의 요소를 순서대로 꺼내기 위한 표준 도구
         *
         * forEachRemaining(Consumer<? super T> action)
         * Iterator의 남아있는 모든 요소를 순회하며 주어진 람다 또는
         * Consumer 구현체를 실행
         *
         * 흐름을 보면 Interator가 1개의 요소를 꺼내면
         * forEachRemaining에서 해당 데이터를 출력 후
         * Interator가 다름 요소로 이동
         * 동작 흐름
         *
         * Iterator 상태 확인
         *
         * hasNext()로 남은 요소가 있는지 체크
         *
         * 다음 요소 꺼내기
         *
         * next() 호출 → 현재 포인터가 가리키는 요소를 반환
         *
         * 반환한 후, 포인터를 다음 요소로 이동
         *
         * Consumer 실행
         *
         * 꺼낸 요소를 Consumer.accept(요소)에 전달
         *
         * 전달된 람다나 메서드 참조에서 출력·가공·저장 등 원하는 작업 수행
         *
         * 반복
         *
         * 남은 요소가 없을 때까지 1~3 반복
         * */

        /**
         * 헤더를 단일로 조회하고 싶을 경우
         * getHeader함수를 활용
         * */
        request.getHeader("host");
        

        System.out.println("--- Headers - end ---");
        System.out.println();
        /**
         * request.getHeaderNames()를 통해 모든 헤더 정보를 꺼내올 수 잇다.
         * --- Headers - start ---
         * headerName = host
         * headerName = connection
         * headerName = sec-ch-ua
         * headerName = sec-ch-ua-mobile
         * headerName = sec-ch-ua-platform
         * headerName = upgrade-insecure-requests
         * headerName = user-agent
         * headerName = accept
         * headerName = sec-fetch-site
         * headerName = sec-fetch-mode
         * headerName = sec-fetch-user
         * headerName = sec-fetch-dest
         * headerName = accept-encoding
         * headerName = accept-language
         * --- Headers - end ---
         * 이렇게 헤더의 데이터가 존재하는 것을 확인할 수 있다.
         * 단순 요청인데 웹브라우저가 기본적으로 만들어주는 데이터
         * */
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        /**
         * getLocales는 내부에 잇는 업세트 언어 정보를 보낸다.
         * 웹브라우저가 언어 순서도를 보내주는데 이걸 웹브라우저가 해결해준다.
         * 가장 높은 걸 자동으로 꺼내고 싶으면
         * request.getLocale() 이걸 활용
         * */
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());         }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---"); System.out.println();
        /**
         * get은 바디에 별도로 데이터를 받지 않기 때문에 별도의 contentType이 없다.
         * */
        /**
     * --- Header 편의 조회 start ---
     * [Host 편의 조회]
     * request.getServerName() = localhost
     * request.getServerPort() = 8080
     *
     * [Accept-Language 편의 조회]
     * locale = ko_KR
     * locale = ko
     * locale = ja_JP
     * locale = ja
     * locale = en_US
     * locale = en
     * request.getLocale() = ko_KR
     *
     * [cookie 편의 조회]
     *
     * [Content 편의 조회]
     * request.getContentType() = null
     * request.getContentLength() = -1
     * request.getCharacterEncoding() = UTF-8
     * --- Header 편의 조회 end ---
     * */
    }

    private void printEtc(HttpServletRequest request) { System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();

        /**
         * Remote 해당정보는 네트워크 커넥션이 이뤄진 정보를 기반으로 만들어내는 것
         * 기본저긍로 로컬에서 IPv6가 나오는데
         * VM options를 넣어주면 IPv4정보를 가져올 수 있다.
         * */

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); // System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---"); System.out.println();

    }

}
