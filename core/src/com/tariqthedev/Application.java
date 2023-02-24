package com.tariqthedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {
	public static final int GRAVITY_X = 0;
	public static final int GRAVITY_Y = -10;
	SpriteBatch spriteBatch;
	float deltaTime;
	World world;

	Helicopter helicopter;

	@Override
	public void create () {
		Box2D.init();
		spriteBatch = new SpriteBatch();
		world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
		deltaTime = 0f;

		helicopter = new Helicopter(world, (float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 2);
	}

	@Override
	@SuppressWarnings("NewApi")
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		deltaTime += Gdx.graphics.getDeltaTime();

		spriteBatch.begin();
		helicopter.render(spriteBatch, deltaTime);
		spriteBatch.end();

		helicopter.handleInput();
		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		helicopter.dispose();
	}
}
