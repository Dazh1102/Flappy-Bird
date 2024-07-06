package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public abstract class Character {
    
    protected int x, y, width, height;
    protected int velocityX, velocityY;
    protected int gravity;
    protected Image image;
    protected Skill skill;
    private boolean skillUsed;
    private boolean skillOnCooldown;
    private Timer cooldownTimer;
    private int cooldownTime;
    private int remainingCooldownTime;
    protected int speed;

    public Character(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityX = -4;
        this.velocityY = 0;
        this.gravity = 1;
        
        this.skillUsed = false;
        this.skillOnCooldown = false;
        this.cooldownTime = 30000; // 30 seconds cooldown
        this.remainingCooldownTime = 0;

        // Initialize cooldown timer
        cooldownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingCooldownTime > 0) {
                    remainingCooldownTime -= 1000;
                    if (remainingCooldownTime <= 0) {
                        skillOnCooldown = false;
                        cooldownTimer.stop();
                    }
                }
            }
        });
    }
    
    public void useSkill(GameModelInterface gameModel) {
        if (!skillUsed && !skillOnCooldown) {
            ArrayList<Pipe> pipes = gameModel.getPipes();
            ArrayList<Integer> toRemove = new ArrayList<>();
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                if (!pipe.isPassed() && i % 2 == 0) {
                    toRemove.add(i);
                    skillUsed = true;
                    break;  // Ensure we only remove one pipe
                }
            }
            for (int index : toRemove) {
                pipes.remove(index);
            }
            startCooldown();
        }
    }

    private void startCooldown() {
        skillOnCooldown = true;
        skillUsed = false;
        remainingCooldownTime = cooldownTime;
        cooldownTimer.start();
    }

    public boolean isSkillOnCooldown() {
        return skillOnCooldown;
    }

    public int getRemainingCooldownTime() {
        return remainingCooldownTime;
    }
    
    public void resetSkill() {
        this.skillUsed = false;
        this.skillOnCooldown = false;
        this.remainingCooldownTime = 0;
        if (cooldownTimer.isRunning()) {
            cooldownTimer.stop();
        }
    }
    
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocityX = -4;
        this.velocityY = 0;
        this.skillUsed = false;
    }

    public void display(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void move();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public boolean isSkillUsed() {
		return skillUsed;
	}

	public void setSkillUsed(boolean skillUsed) {
		this.skillUsed = skillUsed;
	}

	public Timer getCooldownTimer() {
		return cooldownTimer;
	}

	public void setCooldownTimer(Timer cooldownTimer) {
		this.cooldownTimer = cooldownTimer;
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	public void setSkillOnCooldown(boolean skillOnCooldown) {
		this.skillOnCooldown = skillOnCooldown;
	}

	public void setRemainingCooldownTime(int remainingCooldownTime) {
		this.remainingCooldownTime = remainingCooldownTime;
	}

    // Getters and setters...
	public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
