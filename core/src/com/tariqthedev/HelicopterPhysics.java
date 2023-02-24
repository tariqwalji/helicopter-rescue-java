package com.tariqthedev;

import com.badlogic.gdx.physics.box2d.*;

public class HelicopterPhysics {
    private static final int RADIUS = 6;
    private static final float DENSITY = 0.5f;
    private static final float FRICTION = 0.4f;
    private static final float RESTITUTION = 0.2f;
    private static final float HORIZONTAL_INPUT_FORCE = 300;
    private static final float VERTICAL_INPUT_FORCE = 200;
    private static final float ZERO_FORCE = 0.0f;
    CircleShape circleShape;
    Body body;
    Fixture fixture;

    public HelicopterPhysics(World world, float x, float y) {
        circleShape = new CircleShape();
        circleShape.setRadius(RADIUS);

        body = createBody(world, x, y);
        fixture = createFixture();
    }

    private Fixture createFixture() {
        FixtureDef helicopterFixtureDef = new FixtureDef();
        helicopterFixtureDef.shape = circleShape;
        helicopterFixtureDef.density = DENSITY;
        helicopterFixtureDef.friction = FRICTION;
        helicopterFixtureDef.restitution = RESTITUTION;

        return body.createFixture(helicopterFixtureDef);
    }

    private Body createBody(World world, float x, float y) {
        BodyDef helicopterBodyDef = new BodyDef();
        helicopterBodyDef.type = BodyDef.BodyType.DynamicBody;
        helicopterBodyDef.position.set(x, y);
        return world.createBody(helicopterBodyDef);
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void left() {
        body.applyLinearImpulse(-HORIZONTAL_INPUT_FORCE, ZERO_FORCE, body.getPosition().x, body.getPosition().y, true);
    }

    public void right() {
        body.applyLinearImpulse(HORIZONTAL_INPUT_FORCE, ZERO_FORCE, body.getPosition().x, body.getPosition().y, true);
    }

    public void up() {
        body.applyLinearImpulse(ZERO_FORCE, VERTICAL_INPUT_FORCE, body.getPosition().x, body.getPosition().y, true);
    }

    public void down() {
        body.applyLinearImpulse(ZERO_FORCE, -VERTICAL_INPUT_FORCE, body.getPosition().x, body.getPosition().y, true);
    }

    public void dispose() {
        circleShape.dispose();
    }

}
