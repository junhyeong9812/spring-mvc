package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 서블릿은 톰캣 같은 웹 애플리케이션 서버를 직접 설치하고 그 위에 서블릿 코드를
 * 클래스 파일로 빌드해서 올린다음 톰캣 서버를 실행하면 된다.
 * */
@ServletComponentScan //서블릿 자동 등록
/*스프링에서 서블릿 컴포넌트 스캔 어노테이션을 제공
* 패키지에서 하위 패키지에 서블릿을 찾아서 등록할 수 있게 도와준다.*/
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
