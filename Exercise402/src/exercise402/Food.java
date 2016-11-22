package exercise402;

public class Food {

	private int posX;
	private int posY;
	
	public Food() {
		posX = (int) Math.ceil(Math.random() * 400);
		posY = (int) Math.ceil(Math.random() * 400);
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
