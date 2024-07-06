package model;

public class PlaneSkill implements Skill {
    
    private boolean active;
    private long startTime;
    private static final int DURATION = 3000; 
    private final Plane plane;

    public PlaneSkill(Plane plane) {
        this.plane = plane;
        this.active = false;
    }

    @Override
    public void skill() {
        long currentTime = System.currentTimeMillis();

        if (active) {
            if (currentTime - startTime < DURATION) {
                plane.setVelocityY(0); // Plane flies straight
                plane.setGravity(0);
            } else {
                active = false;
                plane.setGravity(plane.getDefaultGravity()); // Restore default gravity effect
            }
        }
    }

    public void activate() {
        if (!active) {
            active = true;
            startTime = System.currentTimeMillis();
        }
    }

    public boolean isActive() {
        return active;
    }
}
