package main;

import controller.MainController;
import view.MainTui;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("debug");
		// Insurance insurance = new Insurance();

		// init Model
		// test data로, controller 내부에서 new 로 생성 후 갖고 있음.
		// init View
		MainTui mainTui = new MainTui();
		// init Controller
		MainController mainController = new MainController();

		// associate
		mainController.associate(mainTui);
		mainTui.associate(mainController);
		
		// System run
		mainTui.displayLogin();
	}
}
