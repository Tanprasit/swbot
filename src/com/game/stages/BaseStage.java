package com.game.stages;

import java.util.List;

public class BaseStage {
	private String name;
	private List<StageInferface> nextStages;

	public BaseStage(String name, List<StageInferface> nextStages) {
		this.name = name;
		this.nextStages = nextStages;
	}

	public String getName() {
		return name;
	}

	public String getImagePath() {
		return "images/" + this.name + ".png";
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StageInferface> getNextStages() {
		return nextStages;
	}
}
