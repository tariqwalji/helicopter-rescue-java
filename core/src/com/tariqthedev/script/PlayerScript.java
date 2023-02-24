package com.tariqthedev.script;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.physics.PhysicsContact;
import games.rednblack.editor.renderer.scripts.BasicScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class PlayerScript extends BasicScript implements PhysicsContact {
    protected World world;
    protected ComponentMapper<PhysicsBodyComponent> physicsMapper;

    private PhysicsBodyComponent physics;

    private final Vector2 impulse = new Vector2(0, 0);
    private final Vector2 speed = new Vector2(0, 0);

    @Override
    public void init(int item) {
        super.init(item);

        ItemWrapper itemWrapper = new ItemWrapper(item, world);
        physics = physicsMapper.get(itemWrapper.getEntity());
    }

    @Override
    public void beginContact(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void endContact(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void preSolve(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void postSolve(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void act(float delta) {
        Body body = physics.body;

        if (body != null) {
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                impulse.set(-5.0f, speed.y);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                impulse.set(0.05f, speed.y);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                impulse.set(speed.x, 5.0f);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                impulse.set(speed.x, -5.0f);
            }

            body.applyLinearImpulse(impulse.sub(speed), body.getWorldCenter(), true);
        }

    }

    @Override
    public void dispose() {

    }
}
