package model;

public class HeroSkill implements Skill {
    private boolean active;
    private final Hero hero;
    private static final int TELEPORT_DISTANCE = 10; // Distance to move past pipes

    public HeroSkill(Hero hero) {
        this.hero = hero;
        this.active = false;
    }

    @Override
    public void skill() {
        if (active) {
            hero.setX(hero.getX() + TELEPORT_DISTANCE); 
            active = false; 
        }
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public Hero getHero() {
        return hero;
    }
}
