package com.tariqthedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Optional;

public class Application extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Texture helicopterSpriteSheet;
	Animation<TextureRegion> helicopterAnimation;

	float helicopterAnimationStateTime;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

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
		helicopterAnimationStateTime = 0f;
	}

	@Override
	@SuppressWarnings("NewApi")
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		helicopterAnimationStateTime += Gdx.graphics.getDeltaTime();

		spriteBatch.begin();

		TextureRegion currentFrame = helicopterAnimation.getKeyFrame(helicopterAnimationStateTime, true);
		spriteBatch.draw(currentFrame, 50, 50);

		spriteBatch.end();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		helicopterSpriteSheet.dispose();
	}
}
