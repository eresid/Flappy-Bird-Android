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

public class Tree extends GameObject {
	public static final int	TREE_WIDTH	= 1;

	public Tree(World _world, float _positionX, float _positionY, float _height) {
		super(TREE_WIDTH, _height, new Vector2(), new Vector2(), true);
		createGround(_world, _positionX, _positionY, _height);
	}

	private void createGround(World _world, float _positionX, float _positionY, float _height) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(TREE_WIDTH, _height);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 1f;
		fixtureDef.friction = 1f;
		fixtureDef.restitution = 0f;
		fixtureDef.shape = shape;

		ContactObject mObj = new ContactObject(Const.BODY_TREE);
		createBody(_world, mObj, _positionX, _positionY, TYPE_BODY_STATIC, fixtureDef, true);
		shape.dispose();
	}

	public void update(float _deltaTime) {

	}
}
