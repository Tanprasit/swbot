package com.game.swsa;

import java.util.Random;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Search {
	private String imageName;
	private boolean isSearching;
	private Pattern pattern;
	private Screen screen;
	private float waitTime;
	private Random random = new Random();

	public Search(String imageName, Screen screen) {
		this.imageName = imageName;
		this.screen = screen;
	}

	// Starts image search on screen.
	public void clickSearch() throws FindFailed {
		this.isSearching = true;
		this.pattern = new Pattern("images/" + this.imageName + ".png");
		while (isSearching) {
			this.screen.wait(this.waitTime);
			if (this.screen.exists(this.pattern) != null) {
				this.screen.click(this.pattern.targetOffset(
						random.nextInt(15),
						random.nextInt(15)));
				System.out.println("Found " + this.imageName.replace("_", " "));
				this.isSearching = false;
			} else {
				System.out.println("Searching for "+ this.imageName.replace("_"," ") + "...");
			}
		}
	}

	public void setWaitTime(float waitTime) {
		this.waitTime = waitTime;
	}

	public void quickSearch() throws FindFailed {
		this.pattern = new Pattern("images/" + this.imageName + ".png");
		if (this.screen.exists(this.pattern) != null) {
			System.out.println("Energy depleted recharge required.");
			
			Search search = new Search("bt_yes", this.screen);
			search.clickSearch();
			
			search = new Search("bt_crystal", this.screen);
			search.clickSearch();
			
			search = new Search("bt_yes", this.screen);
			search.clickSearch();
			
			search = new Search("bt_ok", this.screen);
			search.clickSearch();
			
			search = new Search("bt_close", this.screen);
			search.clickSearch();
			
			search = new Search("bt_replay_6", this.screen);
			search.clickSearch();

		} else {
			System.out.println("Energy recharge not required.");
		}
	}
}
