package com.tariqthedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Texture helicopterSpriteSheet;
	Animation<TextureRegion> helicopterAnimation;

	float frameTime;

	World world;

	Body helicopterBody;
	Fixture helicopterFixture;
	CircleShape circleShape;

	@Override
	public void create () {
		Box2D.init();
		spriteBatch = new SpriteBatch();
		world = new World(new Vector2(0, -10), true);

		BodyDef helicopterBodyDef = new BodyDef();
		helicopterBodyDef.type = BodyDef.BodyType.DynamicBody;
		helicopterBodyDef.position.set(200, 200);
		helicopterBody = world.createBody(helicopterBodyDef);


		circleShape = new CircleShape();
		circleShape.setRadius(6);

		FixtureDef helicopterFixtureDef = new FixtureDef();
		helicopterFixtureDef.shape = circleShape;
		helicopterFixtureDef.density = 0.5f;
		helicopterFixtureDef.friction = 0.4f;
		helicopterFixtureDef.restitution = 0.2f;
		helicopterFixture = helicopterBody.createFixture(helicopterFixtureDef);


		// Original https://opengameart.org/content/helicopter-2
		helicopterSpriteSheet = new Texture(Gdx.files.internal("helicopter/helicopter.png"));

		TextureRegion[][] helicopterFramesTmp = TextureRegion.split(helicopterSpriteSheet, helicopterSpriteSheet.getWidth() / 8, helicopterSpriteSheet.getHeight() / 1);
		TextureRegion[] helicopterFrames = new TextureRegion[8];

		int index = 0;
		for(int x=0; x < helicopterFramesTmp.length; x++) {
			for(int y=0; y < helicopterFramesTmp[x].length; y++) {
				helicopterFrames[index++] = helicopterFramesTmp[x][y];
			}
		}
		helicopterAnimation = new Animation<>(0.05f, helicopterFrames);
		frameTime = 0f;
	}

	@Override
	@SuppressWarnings("NewApi")
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		frameTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = helicopterAnimation.getKeyFrame(frameTime, true);

		spriteBatch.begin();
		spriteBatch.draw(currentFrame, helicopterBody.getPosition().x, helicopterBody.getPosition().y);
		spriteBatch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			helicopterBody.applyLinearImpulse(-300.0f, 0.0f, helicopterBody.getPosition().x, helicopterBody.getPosition().y, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			helicopterBody.applyLinearImpulse(300.0f, 0.0f, helicopterBody.getPosition().x, helicopterBody.getPosition().y, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			helicopterBody.applyLinearImpulse(0.0f, 200.0f, helicopterBody.getPosition().x, helicopterBody.getPosition().y, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			helicopterBody.applyLinearImpulse(0.0f, -200.0f, helicopterBody.getPosition().x, helicopterBody.getPosition().y, true);
		}

		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		helicopterSpriteSheet.dispose();
		circleShape.dispose();
	}
}
