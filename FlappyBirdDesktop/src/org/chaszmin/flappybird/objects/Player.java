/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird.objects;

import org.chaszmin.flappybird.engine.Const;
import org.chaszmin.flappybird.engine.ContactObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends GameObject {
	public static final float	PLAYER_SIZE				= 0.5f;

	public static final int		PLAYER_STATE_ALIVE		= 0;
	public static final int		PLAYER_STATE_HIT		= 1;

	public static final float	PLAYER_VELOCITY			= 2.5f;
	public static final float	PLAYER_STANDARD_IMPULSE	= 10;

	private int					mState;
	private boolean				mJump;
	private float				mCurrentImpulse;

	public Player(World _world, float _positionX, float _positionY, Vector2 _velocity) {
		super(PLAYER_SIZE, PLAYER_SIZE, _velocity, new Vector2(), true);
		createPlayer(_world, _positionX, _positionY);
		setState(PLAYER_STATE_ALIVE);
	}

	private void createPlayer(World _world, float _positionX, float _positionY) {
		PolygonShape shape = new PolygonShape();
		Vector2 center = new Vector2();
		center.x = 0;
		center.y = PLAYER_SIZE;
		shape.setAsBox(PLAYER_SIZE, PLAYER_SIZE, center, 0);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 1.27f;
		fixtureDef.friction = 1f;
		fixtureDef.restitution = 0f;
		fixtureDef.shape = shape;

		ContactObject mObj = new ContactObject(Const.BODY_PLAYER);
		createBody(_world, mObj, _positionX, _positionY, TYPE_BODY_DYNAMIC, fixtureDef, true);
		shape.dispose();
	}

	public void update(float _deltaTime) {
		Vector2 vel = mBody.getLinearVelocity();

		mVelocity.y = vel.y;
		mBody.setLinearVelocity(mVelocity);
		if (mJump)
		{
			mVelocity.y = 0;
			mBody.setLinearVelocity(mVelocity);

			mBody.applyLinearImpulse(0, mCurrentImpulse, mBody.getPosition().x, mBody.getPosition().y, false);
			mJump = false;
		}
	}

	public void jump() {
		mCurrentImpulse = PLAYER_STANDARD_IMPULSE;
		mJump = true;
	}

	public int getState() {
		return mState;
	}

	public void setState(int _state) {
		this.mState = _state;
	}

	public boolean isJump() {
		return mJump;
	}
}
