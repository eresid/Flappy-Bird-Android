/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 15.02.2014
 */

package org.chaszmin.flappybird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class FlappyBirdDesktop {

	private static FlappyBirdDesktop	mApplication;

	public static void main(String[] argv) {
		if (mApplication == null)
		{
			mApplication = new FlappyBirdDesktop();
		}

		new LwjglApplication(new FlappyBird(480, 800), "Power Jump", 480, 800, true);
	}
}
