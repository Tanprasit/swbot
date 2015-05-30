package com.game.swsa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Game {
	private Screen screen;
	private Random random;
	private boolean inBattle = true;
	private boolean isSearching = false;
	private long start;
	private long end;
	private Pattern pattern;
	private Search search;
	private int counter;
	private int maxCount; 

	public Game() {
		this.screen = new Screen();
		this.random = new Random();
	}

	public void startHohMode(String level) throws FindFailed {
		if (null != this.screen.exists(new Pattern("images/user_panel.png")
				.similar(0.4f))) {
			System.out.println("User panel found, launching map.");

			this.isSearching = true;

			pattern = new Pattern("images/bt_battle.png");
			while (isSearching) {
				if (this.screen.exists(this.pattern.similar(0.3f)) != null) {
					this.screen.click(this.pattern.similar(0.3f).targetOffset(
							random.nextInt(20), random.nextInt(20)));
					System.out.println("Found battle icon.");
					this.isSearching = false;
				} else {
					System.out.println("Searching for Battle Icon...");
				}
			}

			pattern = new Pattern("Images/bt_cairos_dungeon.png");
			this.isSearching = true;
			while (isSearching) {
				if (this.screen.exists(this.pattern.similar(0.5f)) != null) {
					this.screen.click(this.pattern.similar(0.5f).targetOffset(
							random.nextInt(20), random.nextInt(20)));
					System.out.println("Found Caiross Dungeon icon.");
					this.isSearching = false;
				} else {
					System.out.println("Searching for Caiross Dungeon...");
				}
			}

			pattern = new Pattern("images/hoh_b" + level + "_start.png");
			this.isSearching = true;
			while (isSearching) {
				if (this.screen.exists(this.pattern.similar(0.5f)) != null) {
					this.screen.click(this.pattern.similar(0.5f).targetOffset(
							100, random.nextInt(20)));

					System.out
							.println("Found Hoh B" + level + " Dungeon icon.");
					this.isSearching = false;
				} else {
					System.out.println("Searching for Hoh Dungeon...");
				}
			}

			this.runHohDungeon(level);

		} else {
			System.out.println("Current stage is not home screen.");
			this.startHohMode(level);
		}
	}

	private void runHohDungeon(String level) throws FindFailed {
		// Dungeon restart in battle reset.
		inBattle = true;

		this.pattern = new Pattern("images/bt_energy_start_6.png");
		this.isSearching = true;
		while (isSearching) {
			if (null != this.screen.exists(this.pattern.similar(0.5f))) {
				System.out.println("Found Hoh start icon.");
				this.screen.click(new Pattern(this.pattern.similar(0.5f))
						.targetOffset(random.nextInt(20), random.nextInt(20)));
				this.isSearching = false;
			} else {
				System.out.println("Searching for hoh start icon...");
			}
		}

		System.out.println("Initiating dungeon run.");
		this.start = System.currentTimeMillis();

		this.pattern = new Pattern("images/hoh_reward_panel_1.png");
		while (inBattle) {
			this.screen.wait(20.0);

			// Every loop check for the reward panel.
			if (null != this.screen.exists(this.pattern)) {
				System.out.println("Completed hoh dungeon run.");
				this.inBattle = false;
				System.out.println("Found reward panel, accepting reward...");
				this.screen.click(this.pattern.targetOffset(random.nextInt(20),
						random.nextInt(20)));

				this.screen.wait(random.nextInt(5) + random.nextDouble());
				this.screen.click();
				System.out.println("Searching for reward panel 2...");

				this.isSearching = true;
				this.pattern = new Pattern("images/hoh_reward_panel_2.png");
				while (isSearching) {
					if (null != this.screen.exists(this.pattern)) {

						System.out.println("Found reward panel 2.");

						this.end = System.currentTimeMillis();
						SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
						Date resultdate = new Date(end - start);
						System.out.println("Time taken for run: "
								+ sdf.format(resultdate));

						this.pattern = new Pattern("images/bt_ok.png");
						while (isSearching) {
							if (null != this.screen.exists(this.pattern)) {
								this.screen
										.click(this.pattern.targetOffset(
												random.nextInt(20),
												random.nextInt(20)));
								this.isSearching = false;
							} else {
								System.out
										.println("Searching for ok button...");
							}
						}

						System.out
								.println("Reward accepted! restarting dungeon run");

						this.pattern = new Pattern("images/bt_replay_6.png");
						this.isSearching = true;
						while (isSearching) {
							if (null != this.screen.exists(this.pattern)) {
								this.screen
										.click(this.pattern.targetOffset(
												random.nextInt(20),
												random.nextInt(20)));
								this.isSearching = false;

								// Crystal refresh
								this.screen.wait(5.0);
								this.pattern = new Pattern(
										"images/refresh_panel.png");
								if (null != this.screen.exists(this.pattern)) {
									this.screen.click(this.pattern
											.targetOffset(random.nextInt(20),
													random.nextInt(20)));

									this.isSearching = true;
									this.pattern = new Pattern(
											"images/bt_yes.png");

									while (isSearching) {
										if (null != this.screen
												.exists(this.pattern)) {
											this.screen
													.click(this.pattern.targetOffset(
															random.nextInt(20),
															random.nextInt(20)));

											this.isSearching = false;
											System.out
													.println("Found yes button.");
										} else {
											System.out
													.println("Searching for yes button...");
										}
									}

									this.pattern = new Pattern(
											"images/bt_crystal.png");
									this.isSearching = true;

									while (isSearching) {
										if (null != this.screen
												.exists(this.pattern)) {
											this.screen
													.click(this.pattern.targetOffset(
															random.nextInt(50),
															random.nextInt(50)));
											System.out
													.println("Found crystal button. Refreshing energy!");
											this.isSearching = false;

										} else {
											System.out
													.println("Searching for crystal button...");
										}
									}

									this.pattern = new Pattern(
											"images/purchase_panel.png");
									this.isSearching = true;

									while (isSearching) {
										if (null != this.screen
												.exists(this.pattern)) {

											System.out
													.println("Purchase panel found");

											this.pattern = new Pattern(
													"images/bt_yes.png");
											this.isSearching = true;

											while (isSearching) {
												if (null != this.screen
														.exists(this.pattern)) {
													System.out
															.println("Confirming purchase.");
													this.screen
															.click(this.pattern
																	.targetOffset(
																			random.nextInt(20),
																			random.nextInt(20)));

													this.pattern = new Pattern(
															"bt_ok.png");
													this.isSearching = true;

													// Need to tap ok
													while (isSearching) {
														if (null != this.screen
																.exists(this.pattern)) {
															this.screen
																	.click(this.pattern);
															System.out
																	.println("Found yes button.");
															this.isSearching = false;

														} else {
															System.out
																	.println("Searching for ok button...");
														}
													}

													// Need to tap close
													this.isSearching = true;
													this.pattern = new Pattern(
															"images/bt_close.png");

													while (isSearching) {
														if (null != this.screen
																.exists(this.pattern)) {
															this.screen
																	.click(this.pattern);

															System.out
																	.println("Closing crystal panel.");
															this.isSearching = false;
														} else {
															System.out
																	.println("Searching for close button...");
														}
													}

													// Replay again
													this.pattern = new Pattern(
															"images/bt_replay_6.png");
													this.isSearching = true;
													while (isSearching) {
														if (null != this.screen
																.exists(this.pattern)) {
															System.out
																	.println("Refresh button found!!!");
															this.screen
																	.click(this.pattern
																			.targetOffset(
																					random.nextInt(20),
																					random.nextInt(20)));
															this.isSearching = false;
															System.out
																	.println("Energy refesh completed.");
														} else {
															System.out
																	.println("Searching for refresh button...");
														}
													}

												} else {
													System.out
															.println("Searching for yes button");
												}
											}

										} else {
											System.out
													.println("Waiting for confirmation panel...");
										}
									}

								}
							} else {
								System.out
										.println("Searching for replay button...");
							}
						}

						this.runHohDungeon(level);
					} else {
						System.out.println("Searching for reward panel 2...");
					}
				}

			}
		}

	}

	public void startFarmMode() throws FindFailed {
		System.out.println("Start farming mode...");
		this.start = System.currentTimeMillis();
		this.search = new Search("bt_start_5", this.screen);
		this.search.clickSearch();
		this.screen.wait(40f);
		this.acceptReward();
	}

	private void acceptReward() throws FindFailed {
		this.search = new Search("victory_north_hell", this.screen);
		this.search.setWaitTime(5f);
		this.search.clickSearch();
		this.end = System.currentTimeMillis();

		this.search = new Search("chest", this.screen);
		this.search.clickSearch();

		this.search = new Search("bt_sell", this.screen);
		this.search.clickSearch();

		this.search = new Search("bt_replay_6", this.screen);
		this.search.clickSearch();
		this.counter++;
		this.printRunTime();

		this.search = new Search("no_energy_panel", this.screen);
		this.search.quickSearch();

		if (this.counter >= this.maxCount) {
			this.counter = 0;
			System.out.println("Please change your monsters. Type (r) when ready");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			int n;
			switch (input) {
			case "r":
				System.out.println("What level would you like to max? @3600exp");
				n = scanner.nextInt();
				int noOfRuns = 0;
				switch(n) {
					case 2:
						noOfRuns = 10;
						break;
					case 3:
						noOfRuns = 23;
						break;
					case 4: 
						noOfRuns = 53;
						break;
					default:
						noOfRuns = n;
						break;
				}
				this.maxCount = noOfRuns;
				System.out.println("New batch confirmed, begin farming.");
				this.startFarmMode();
				break;
			default:
				System.out.println("Unknown command, begin farming anyway.");
				n = scanner.nextInt();
				this.maxCount = n;
				System.out.println("New batch confirmed, begin farming.");
				this.startFarmMode();
				break;
			}
			scanner.close();
		} else {
			this.startFarmMode();
		}
	}

	// Print run time in human readable format.y67yh
	private void printRunTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		Date date = new Date(end - start);
		System.err.println("Time taken for run: " + sdf.format(date) + " Runs remaining: " + (this.maxCount - this.counter));	
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public void startClick(int maxClicks) throws FindFailed {
		this.search = new Search("bt_power_up",this.screen);
		while (true) {
			this.search.clickSearch();
		}
	}
}
