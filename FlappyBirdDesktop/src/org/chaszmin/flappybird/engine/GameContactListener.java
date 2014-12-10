/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird.engine;

import org.chaszmin.flappybird.objects.Player;
import org.chaszmin.flappybird.screens.MainScreen;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener {
	private MainScreen	mGameWorld;

	public GameContactListener(MainScreen _gameWorld) {
		this.mGameWorld = _gameWorld;
	}

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		if (mGameWorld.mPlayer.getState() == Player.PLAYER_STATE_HIT)
		{
			contact.setEnabled(false);
			return;
		}

		ContactObject fA = (ContactObject) contact.getFixtureA().getBody().getUserData();
		ContactObject fB = (ContactObject) contact.getFixtureB().getBody().getUserData();

		ContactObject playerObj;
		ContactObject otherObj;

		if (fA.getType().equals(Const.BODY_PLAYER))
		{
			playerObj = fA;
			otherObj = fB;
		}
		else
		{
			playerObj = fB;
			otherObj = fA;
		}

		// Contact with Tree
		if (playerObj.getType().equals(Const.BODY_PLAYER) && otherObj.getType().equals(Const.BODY_TREE))
		{
			mGameWorld.mPlayer.setState(Player.PLAYER_STATE_HIT);

			return;
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
