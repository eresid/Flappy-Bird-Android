/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybirdandroid;

import org.chaszmin.flappybird.FlappyBird;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;

public class MainActivity extends AndroidApplication {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		int mScreenWidth = 480;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int mScreenHeight = getGameHeight(metrics.widthPixels, metrics.heightPixels, mScreenWidth);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = false;
		if (mScreenHeight > 1024)
		{
			config.resolutionStrategy = new RatioResolutionStrategy(10, 16);
			mScreenHeight = 864;
		}

		RelativeLayout layout = new RelativeLayout(MainActivity.this);
		View gameView = initializeForView(new FlappyBird(mScreenWidth, mScreenHeight), config);

		layout.addView(gameView);

		setContentView(layout);
	}

	private int getGameHeight(int _width, int _height, int _screenWidth) {
		float proportion = (float) _height / _width;
		return (int) (_screenWidth * proportion);
	}

}
