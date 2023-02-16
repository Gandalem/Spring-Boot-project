package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DateNotFoundExceptoin;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자를 통한 객체 주입 : DI
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;

	// 답변글을 저장하는 메소드
	public void create(Question question, String content, SiteUser author) {
		// Answer 객체를 생성후 아규먼트로 넘어노는 값을 setter 주입
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);

		this.answerRepository.save(answer);
	}

	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DateNotFoundExceptoin("answer not found");
		}

	}

	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}

}
