package com.tariqthedev.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Helicopter {
    HelicopterPhysics physics;
    HelicopterAnimation animation;

    public Helicopter(World world, float x, float y) {
        physics = new HelicopterPhysics(world, x, y);
        animation = new HelicopterAnimation();
    }

    public void render(SpriteBatch spriteBatch, float deltaTime) {
        animation.render(spriteBatch, deltaTime, physics.getX(), physics.getY());
    }

    public void left() {
        physics.left();
    }

    public void right() {
        physics.right();
    }

    public void up() {
        physics.up();
    }

    public void down() {
        physics.down();
    }

    public void dispose() {
        physics.dispose();
        animation.dispose();
    }
}
