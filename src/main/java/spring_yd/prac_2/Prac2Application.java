package spring_yd.prac_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Prac2Application {

	public static void main(String[] args) {
		SpringApplication.run(Prac2Application.class, args);
	}

	//Cannot call sendError() after the response has been committed => 순환반복, @JsonIgnore로 해결
	//No serializer found for class =>
	//(no Creators, like default constructor, exist): cannot deserialize from Object value
	// => default Constructor 생성으로 해결
//	database queries may be performed during view rendering.
//	Explicitly configure spring.jpa.open-in-view to disable this warning
//	=>

	//jpa 기초 연습
	//fetch join + pagenation
	//성공 or 에러 data mapping
	//테스트코드...?
	//로깅

}
