package com.tariqthedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            physics.left();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            physics.right();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            physics.up();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            physics.down();
        }
    }

    public void dispose() {
        physics.dispose();
        animation.dispose();
    }
}
