/* 
 * Author: Eugene Sakara (vzov@yandex.ua)
 * URL: https://bitbucket.org/vzov/flappy-bird-android
 * License: MIT (http://opensource.org/licenses/MIT)
 * Start: 12.02.2014
 */

package org.chaszmin.flappybird.engine;

public class ContactObject {
	private String	mType;

	public ContactObject(String _type) {
		this.mType = _type;
	}

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		this.mType = type;
	}
}
