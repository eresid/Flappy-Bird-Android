/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird;

import org.chaszmin.flappybird.screens.MainScreen;

import com.badlogic.gdx.Game;

public class FlappyBird extends Game {
	public static int	SCREEN_WIDTH;
	public static int	SCREEN_HEIGHT;

	public FlappyBird(int _width, int _height) {
		SCREEN_WIDTH = _width;
		SCREEN_HEIGHT = _height;
	}

	@Override
	public void create() {
		setScreen(new MainScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();

		getScreen().dispose();
	}
}
