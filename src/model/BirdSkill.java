package model;

import java.util.ArrayList;

public class BirdSkill implements Skill {
	
	private boolean used;
	private GameModelInterface gameModel;
	
	public BirdSkill(GameModelInterface gameModel) {
		this.used = false;
		this.gameModel = gameModel;
	}


	@Override
	public void skill() {
		if (!used) {
            ArrayList<Pipe> pipes = gameModel.getPipes();
            ArrayList<Integer> toRemove = new ArrayList<>();
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                if (!pipe.isPassed() && i % 2 == 0) {
                    toRemove.add(i);
                    used = true;
                    break;  //chỉ xóa một pipe
                }
            }
            // Remove pipes outside the loop to avoid ConcurrentModificationException
            for (int index : toRemove) {
                pipes.remove(index);
            }
            // sử dụng lại
            used = false;
        }
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
