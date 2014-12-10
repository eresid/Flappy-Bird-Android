/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird.objects;

import org.chaszmin.flappybird.engine.ContactObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class GameObject {
	protected static final int	TYPE_BODY_STATIC	= 1;
	protected static final int	TYPE_BODY_DYNAMIC	= 2;
	protected static final int	TYPE_BODY_KINEMATIC	= 3;

	protected Body				mBody;
	protected Vector2			mVelocity;
	protected Vector2			mAcceleration;
	protected float				mWidth;
	protected float				mHeight;
	protected boolean			mActive;

	public GameObject(float _width, float _height, Vector2 _velocity, Vector2 _acceleration, Boolean _active) {
		this.mWidth = _width;
		this.mHeight = _height;
		this.mVelocity = _velocity;
		this.mAcceleration = _acceleration;
		this.mActive = _active;
	}

	public void createBody(World _world, ContactObject _bodyObject, float _positionX, float _positionY, int _bodyType, FixtureDef _fixtureDef, boolean _rotation) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(_positionX, _positionY);

		switch (_bodyType)
			{
				case TYPE_BODY_STATIC:
					bodyDef.type = BodyType.StaticBody;
					break;
				case TYPE_BODY_DYNAMIC:
					bodyDef.type = BodyType.DynamicBody;
					break;
				case TYPE_BODY_KINEMATIC:
					bodyDef.type = BodyType.KinematicBody;
					break;
			}

		mBody = _world.createBody(bodyDef);
		if (_fixtureDef != null)
		{
			mBody.createFixture(_fixtureDef);
		}
		mBody.setUserData(_bodyObject);
		mBody.setFixedRotation(_rotation);
		mBody.setActive(mActive);
	}

	public void destroyBody(World _world) {
		if (mBody != null)
		{
			_world.destroyBody(mBody);
			mBody.setUserData(null);
			mBody = null;
		}
	}

	public Body getBody() {
		return mBody;
	}

	public void setBody(Body _body) {
		this.mBody = _body;
	}

	public Vector2 getVelocity() {
		return mVelocity;
	}

	public void setVelocity(Vector2 _velocity) {
		this.mVelocity = _velocity;
	}

	public Vector2 getAccel() {
		return mAcceleration;
	}

	public void setAccel(Vector2 _acceleration) {
		this.mAcceleration = _acceleration;
	}

	public float getWidth() {
		return mWidth;
	}

	public void setWidth(float _width) {
		this.mWidth = _width;
	}

	public float getHeight() {
		return mHeight;
	}

	public void setHeight(float _height) {
		this.mHeight = _height;
	}

	public Vector2 getPosition() {
		return mBody.getPosition();
	}

	public void setPosition(float _positionX, float _positionY) {
		mBody.setTransform(new Vector2(_positionX, _positionY), 0);
	}

	public void setPosition(Vector2 _vector) {
		mBody.setTransform(_vector, 0);
	}

	public Vector2 getImpulse() {
		return mBody.getLinearVelocity();
	}

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean _active) {
		this.mActive = _active;
	}
}
