package com.tariqthedev;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tariqthedev.script.PlayerScript;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class Application extends ApplicationAdapter {

	private Viewport viewport;
	private OrthographicCamera camera;

	private SceneLoader sceneLoader;

	private ItemWrapper root;
	private World world;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(19, 10, camera);

		SceneConfiguration config = new SceneConfiguration();
		sceneLoader = new SceneLoader(config);
		sceneLoader.loadScene("MainScene", viewport);

		world = sceneLoader.getEngine();
		root = new ItemWrapper(sceneLoader.getRoot(), world);

		ItemWrapper player = root.getChild("helicopter");
		player.addScript(new PlayerScript());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		viewport.apply();
		sceneLoader.getEngine().process();
	}

	@Override
	public void dispose () {
	}
}
