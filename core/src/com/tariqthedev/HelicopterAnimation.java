package com.tariqthedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HelicopterAnimation {
    public static final String SPRITESHEET = "helicopter/helicopter.png";
    public static final int SPRITESHEET_TILE_WIDTH = 8;
    public static final int SPRITESHEET_TILE_HEIGHT = 1;
    public static final float FRAME_DURATION = 0.05f;
    Texture spriteSheet;
    Animation<TextureRegion> animation;

    public HelicopterAnimation() {
        animation = buildAnimation();
    }

    public void render(SpriteBatch spriteBatch, float deltaTime, float x, float y) {
        TextureRegion currentFrame = animation.getKeyFrame(deltaTime, true);
        spriteBatch.draw(currentFrame, x, y);
    }

    private Animation<TextureRegion> buildAnimation() {
        // Original https://opengameart.org/content/helicopter-2
        spriteSheet = new Texture(Gdx.files.internal(SPRITESHEET));

        TextureRegion[][] framesToFlatten = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / SPRITESHEET_TILE_WIDTH,
                spriteSheet.getHeight() / SPRITESHEET_TILE_HEIGHT);

        TextureRegion[] frames = new TextureRegion[SPRITESHEET_TILE_WIDTH * SPRITESHEET_TILE_HEIGHT];

        int index = 0;
        for(int x=0; x < framesToFlatten.length; x++) {
            for(int y=0; y < framesToFlatten[x].length; y++) {
                frames[index++] = framesToFlatten[x][y];
            }
        }
        return new Animation<>(FRAME_DURATION, frames);
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
