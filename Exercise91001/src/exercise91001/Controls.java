package exercise91001;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3f;

public class Controls extends Behavior {

	private TransformGroup tg = null;
	private Transform3D t3d = null;
	private WakeupCondition userAction = null;

	private Node collider;
	private int lastKeyPressed;

	public Controls(TransformGroup tg, Node collider) {
		this.tg = tg;
		this.t3d = new Transform3D();
		this.collider = collider;
	}

	@Override
	public void initialize() {
		WakeupCriterion[] keyEvents = new WakeupCriterion[4];
		keyEvents[0] = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
		keyEvents[1] = new WakeupOnAWTEvent(KeyEvent.KEY_RELEASED);
		keyEvents[2] = new WakeupOnCollisionEntry(collider);
		keyEvents[3] = new WakeupOnCollisionExit(collider);

		userAction = new WakeupOr(keyEvents);
		wakeupOn(userAction);
	}

	@Override
	public void processStimulus(Enumeration criteria) {

		WakeupCriterion wakeup;
		AWTEvent[] events;

		while (criteria.hasMoreElements()) {
			wakeup = (WakeupCriterion) criteria.nextElement();

			if (wakeup instanceof WakeupOnCollisionEntry) {
				//Debug
				//System.out.println("Pressed key was: "+ lastKeyPress);
				reverseMove();
			} else if (wakeup instanceof WakeupOnAWTEvent) {
				events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
				for (int i = 0; i < events.length; ++i) {
					if (events[i].getID() == KeyEvent.KEY_PRESSED) {
						keyPressed((KeyEvent) events[i]);
					}
				}
			}
		}
		wakeupOn(userAction);
	}

	private void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		lastKeyPressed = keyCode;

		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			turn(Math.toRadians(2.0));
			break;
		case KeyEvent.VK_RIGHT:
			turn(Math.toRadians(-2.0));
			break;
		case KeyEvent.VK_UP:
			step(new Vector3f(0.2f, 0, 0));
			break;
		case KeyEvent.VK_DOWN:
			step(new Vector3f(-0.2f, 0, 0));
			break;
		}
	}

	private void reverseMove() {
		switch (lastKeyPressed) {
		case KeyEvent.VK_LEFT:
			turn(Math.toRadians(-2.0));
			break;
		case KeyEvent.VK_RIGHT:
			turn(Math.toRadians(2.0));
			break;
		case KeyEvent.VK_UP:
			step(new Vector3f(-0.2f, 0.0f, 0f));
			break;
		case KeyEvent.VK_DOWN:
			step(new Vector3f(0.2f, 0.0f, 0));
			break;
		}
	}
	
	private void step(Vector3f vector) {
		tg.getTransform(t3d);

		Transform3D doMove = new Transform3D();
		doMove.setTranslation(vector);
		t3d.mul(doMove);

		tg.setTransform(t3d);
	}

	private void turn(double radians) {
		// Get current transformation
		tg.getTransform(t3d);

		// Add new transformation
		Transform3D toRot = new Transform3D();
		toRot.rotY(radians);
		t3d.mul(toRot);

		// Set new transformation
		tg.setTransform(t3d);
	}


}