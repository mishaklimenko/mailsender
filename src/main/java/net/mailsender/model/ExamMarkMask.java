package net.mailsender.model;

import java.util.HashSet;

public class ExamMarkMask {
	public static HashSet<String> MARK;
	public static HashSet<String> BALL;
	public static HashSet<String> ETSC;
	public static HashSet<String> FORM;

	public static HashSet<String> DEBT_MARKS;
	public static HashSet<String> FREED_MARKS;

	static {
		MARK = new HashSet<String>();
		for (int i=0; i<=5; i++) {
			MARK.add(String.valueOf(i));
		}

		BALL = new HashSet<String>();
		BALL.add("A");
		BALL.add("B");
		BALL.add("C");
		BALL.add("D");
		BALL.add("E");
		BALL.add("FX");
		BALL.add("F");


		ETSC = new HashSet<String>();
		for (int i=0; i<=100; i++) {
			ETSC.add(String.valueOf(i));
		}


		FORM = new HashSet<String>();
		FORM.add("диф. залік");
		FORM.add("екзамен");
		FORM.add("залік");


		DEBT_MARKS = new HashSet<>();
		DEBT_MARKS.add("нея.");
		DEBT_MARKS.add("н.д.");


		FREED_MARKS = new HashSet<>();
		FREED_MARKS.add("зв.");
	}
}