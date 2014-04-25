package net.mailsender.model;

import java.util.HashSet;

public class ExamMarkMask {
	public static HashSet<String> BALL;

	public static HashSet<String> MARK;
	public static HashSet<String> ETSC;
	public static HashSet<String> FORM;

	public static HashSet<String> DEBT_MARKS;
	public static HashSet<String> FREED_MARKS;

	static {
		BALL = new HashSet<String>();
		for (int i=0; i<=100; i++) {
			BALL.add(String.valueOf(i));
		}

		ETSC = new HashSet<String>();
		ETSC.add("A");
		ETSC.add("B");
		ETSC.add("C");
		ETSC.add("D");
		ETSC.add("E");
		ETSC.add("FX");
		ETSC.add("F");

		MARK = new HashSet<String>();
		for (int i=0; i<=5; i++) {
			MARK.add(String.valueOf(i));
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