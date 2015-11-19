package main;

/**
 * Models a force that acts in one dimensional space
 */
public abstract class OneSpaceForce {
    public float force_;

    public abstract void step();
}
