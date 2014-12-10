/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird.screens;

import java.util.ArrayList;
import java.util.Random;

import org.chaszmin.flappybird.FlappyBird;
import org.chaszmin.flappybird.engine.Const;
import org.chaszmin.flappybird.engine.GameContactListener;
import org.chaszmin.flappybird.objects.Player;
import org.chaszmin.flappybird.objects.Tree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MainScreen implements Screen {
	private float				mScreenWidthMeter;
	private float				mScreenHeightMeter;

	private OrthographicCamera	mCamera;
	private SpriteBatch			mBatch;

	private Box2DDebugRenderer	mDebugRenderer;
	public World				mWorld;
	public Vector2				mGravity;

	public Player				mPlayer;
	public ArrayList<Tree>		mTrees;

	private Random				mRand;
	private int					mLastTreePosition;

	public MainScreen() {
		mScreenWidthMeter = FlappyBird.SCREEN_WIDTH * Const.PIX_TO_METER;
		float proportion = (float) (FlappyBird.SCREEN_HEIGHT) / (float) (FlappyBird.SCREEN_WIDTH);
		mScreenHeightMeter = proportion * mScreenWidthMeter;

		this.mCamera = new OrthographicCamera(mScreenWidthMeter, mScreenHeightMeter);
		this.mCamera.position.set(mScreenWidthMeter / 2, mScreenHeightMeter / 2, 0);
		this.mBatch = new SpriteBatch();

		this.mDebugRenderer = new Box2DDebugRenderer();
		this.mGravity = new Vector2(0, -15);
		this.mWorld = new World(mGravity, true);
		this.mWorld.setContactListener(new GameContactListener(MainScreen.this));

		mRand = new Random();
		generateTrees();

		this.mPlayer = new Player(mWorld, 1, mScreenHeightMeter / 2, new Vector2(Player.PLAYER_VELOCITY, Player.PLAYER_VELOCITY));

	}

	private void generateTrees() {
		this.mTrees = new ArrayList<Tree>();
		this.mLastTreePosition = 10;

		for (int i = 0; i < 3; i++)
		{
			float randNumber = mScreenHeightMeter * mRand.nextFloat();
			if (randNumber > mScreenHeightMeter * 0.7f)
			{
				randNumber = mScreenHeightMeter * 0.7f;
			}
			if (mScreenHeightMeter < mScreenHeightMeter * 0.3)
			{
				randNumber = mScreenHeightMeter * 0.3f;
			}

			Tree topTree = new Tree(mWorld, mLastTreePosition, randNumber + 4 + mScreenHeightMeter, mScreenHeightMeter);
			mTrees.add(topTree);

			Tree bottomTree = new Tree(mWorld, mLastTreePosition, randNumber - mScreenHeightMeter, mScreenHeightMeter);
			mTrees.add(bottomTree);

			mLastTreePosition += 8;
		}
	}

	private void updatePlayer(float _deltaTime) {
		if (mPlayer.getPosition().x > mCamera.position.x)
		{
			mCamera.position.x = mPlayer.getPosition().x;
		}

		mPlayer.update(_deltaTime);

		if (mPlayer.getState() == Player.PLAYER_STATE_ALIVE)
		{
			if (mPlayer.getPosition().y < -Player.PLAYER_SIZE || mPlayer.getPosition().y > mScreenHeightMeter + Player.PLAYER_SIZE)
			{
				mPlayer.setState(Player.PLAYER_STATE_HIT);
			}

			if (Gdx.input.justTouched())
			{
				if (!mPlayer.isJump())
				{
					mPlayer.jump();
				}
			}
		}
	}

	private void updateTrees(float _deltaTime) {

		int i = 0;
		while (i < mTrees.size())
		{
			if (mTrees.get(i).getPosition().x < mCamera.position.x - mScreenWidthMeter)
			{
				float randNumber = mScreenHeightMeter * mRand.nextFloat();
				if (randNumber > mScreenHeightMeter * 0.7f)
				{
					randNumber = mScreenHeightMeter * 0.7f;
				}
				if (mScreenHeightMeter < mScreenHeightMeter * 0.3)
				{
					randNumber = mScreenHeightMeter * 0.3f;
				}

				mTrees.get(i).setPosition(mLastTreePosition, randNumber + 4 + mScreenHeightMeter);
				i++;
				mTrees.get(i).setPosition(mLastTreePosition, randNumber - mScreenHeightMeter);
				i++;

				mLastTreePosition += 8;

				continue;
			}

			i += 2;
		}
	}

	private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		mCamera.update();
		mBatch.setProjectionMatrix(mCamera.combined);

		mDebugRenderer.render(mWorld, mCamera.combined);
	}

	@Override
	public void render(float _deltaTime) {
		updatePlayer(_deltaTime);
		updateTrees(_deltaTime);

		mWorld.step(_deltaTime * 1.2f, 6, 5);

		draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
