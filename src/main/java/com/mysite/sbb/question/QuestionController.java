package com.mysite.sbb.question;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserRepository;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드의 생성자를 자동으로 만들어서 생성자를
@Controller // bean 등록
public class QuestionController {
	/*
	 * 클래스를 객체로 생성 어노테이션 (빈(객체)등록),Spring Framework)
	 * 
	 * @component : 일반적인 클래스를 객체화
	 * 
	 * @controller : 클라이언트 요청을 받아서 처리 , controller 1. 클라이언트 요청을
	 * 받는다. @GetMapping, @PostMapping 2. 비즈니스 로직 처리, Service의 메소드 호출, 3. View 페이지로
	 * 응답
	 * 
	 * @service : DAO의 메소드를 인터페이스로 생성 후 인터페이스의 메소드를 구현한 클래스 - 유지보수를 쉽게 하기 위해서 (약결함)
	 * 
	 * @repository : DAO 클래스를 빈등록
	 * 
	 */
	/*
	 * DI(의존성 주입) 1. @Autowired : spring Framework 의 생성된 빈(객체)을 주입, 타입을 찾아서 주입 같은
	 * 타입의 객체가 존재할 경루 문제가 발생될수 있다. 2.생성자를 통한 의존성 주입(권장방식) 3.Setter 를 사용한 의존성 주입
	 */

	// 생성자를 통한 의존성 주입 <== 권장하는 방식
	/*
	 * controller 에서 직접 Repository를 접근하지 않고 service 를 접근 하도록 함. 보안상에도 좋고 cotroller에서
	 * 여러번 선언하면 복잡해져서 유지보수가 않좋다
	 * 
	 * @Autowired private final QuestionRepository questionRepository;
	 */
	private final QuestionService questionService;

	private final UserService userService;

	/*
	 * @GetMapping("/question/list") //http://localhost:9292/question/list
	 * 
	 * @PostMapping("/question/list")//from 태그의 method=post action =
	 * "/question/list" //@ResponseBody //요청을 브라우져에 찍어라 public String list(Model
	 * model) {//view 로 던져줄 타입 선언 String
	 * 
	 * //1. 클라이언트 요청 정보 : http://localhost:9292/question/list
	 * 
	 * //2. 비즈니스 로직 처리 List<Question> questionList =
	 * //this.questionRepository.findAll(); //직접 repository
	 * this.questionService.getList(10); //service를 거쳐서 repository 접근
	 * 
	 * //3. 뷰(view) 페이지로 전송 //model : 뷰페이지로 서버의 데이터를 담아서 전송 객체 (Session,Application)
	 * model.addAttribute("questionList", questionList);
	 * 
	 * return "question_list"; }
	 */

	// 2월 14일 페이징 처리를 위해 수정됨
	// http://localhost/qustion/list/?page=1
	@GetMapping("/question/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,@RequestParam(value="kw",defaultValue="") String kw) {

		// 비즈니스 로직 처리
		Page<Question> paging = this.questionService.getList(page,kw);

		// model 객체의 결과롤 받은 paging 객체를 client로 전송
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "question_list";
	}

	// 상세 페이지를 처리하는 메소드 : /question/detail/1

	@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) { // @PathVariable{변수명}
																								// url의 중괄호 안에 있는변수명을
																								// 잘라와서 변수타입하고 변수명 정함

		// 서비스 클래스의 메소드 호출 : 상세페이지 보여 달라
		Question getQuestion = this.questionService.getQuestion(id);

		model.addAttribute("question", getQuestion);

		return "question_detail"; // template : question_detail.html
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/question/create")
	public String create(// @RequestParam String subject,@RequestParam String content
			@Valid QuestionFrom questionFrom, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "question_from";
		}

		// 로직을 작성부분
		// Question q = new Question();
		// q.setSubject(subject);
		// q.setContent(content);
		// q.setCreateDate(LocalDateTime.now());
		// this.questionService.save(q);

		// 로직을 작성부분

		SiteUser siteUser = this.userService.getUser(principal.getName());

		this.questionService.save(questionFrom.getSubject(), questionFrom.getContent(), siteUser);

		// 값을 DB에 저장후 List페이지로 리다이렉트 (질문 목록으로 이동)

		return "redirect:/question/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/question/create")
	public String create(QuestionFrom questionFrom) {
		return "question_from";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/question/modify/{id}")
	public String questionModify(QuestionFrom questionFrom, @PathVariable("id") Integer id, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		if (!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		questionFrom.setSubject(question.getSubject());
		questionFrom.setContent(question.getContent());
		return "question_from";
	}
	
	//현제 로그인한 사용자 정보를 확인해보기
	
	@PreAuthorize("isAuthenticated()")
	 @PostMapping("/question/modify/{id}")
	 public String questionModify(@Valid QuestionFrom questionFrom, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
	 if (bindingResult.hasErrors()) {
	 return "question_from";
	 }
	 Question question = this.questionService.getQuestion(id);
	 if (!question.getAuthor().getUsername().equals(principal.getName())) {
	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	 }
	 this.questionService.modify(question, questionFrom.getSubject(), questionFrom.getContent());
	 return String.format("redirect:/question/detail/%s", id);
	 }
	
	@PreAuthorize("isAuthenticated()")
	 @GetMapping("/question/delete/{id}")
	 public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
	 Question question = this.questionService.getQuestion(id);
	 if (!question.getAuthor().getUsername().equals(principal.getName())) {
	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	 }
	 this.questionService.delete(question);
	 return "redirect:/";
	 }
	
	//id: question 객체
	//principal : 현제 투표하는 객체를 가지고 옴
	
	//2월 17일 : 질문의 추천 기능 추가
	@PreAuthorize("isAuthenticated()")
	@GetMapping("question/vote/{id}")
	public String questionVote(Principal principal, @PathVariable("id") Integer id) {
		
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		this.questionService.vote(question, siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
		
	}
	
}
