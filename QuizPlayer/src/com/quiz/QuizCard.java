package com.quiz;

public class QuizCard {

	private String answer;
	private String question;
	
	
	public QuizCard(String answer, String question) {
		this.answer = answer;
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuizCard [answer=" + answer + ", question=" + question + "]";
	}
	
	
}
