package exercise402;

public class Snake {
	private int lenght;
	private int posX;
	private int posY;
	private char going;
	
	public Snake() {
		reset();
	}
	
	public void reset() {
		lenght = 25;
		posX = 0;
		posY = 200;
		going = 'r';
	}
	
	public int getLenght() {
		return lenght;
	}
	
	public void setLenght(int lenght) {
		this.lenght = lenght;
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

	public char getGoing() {
		return going;
	}

	public void setGoing(char going) {
		this.going = going;
	}
	
	public void crawl() {
		switch(getGoing()) {
		case 'u':
			moveUp();
			break;
		case 'd':
			moveDown();
			break;
		case 'l':
			moveLeft();
			break;
		case 'r':
			moveRight();
			break;			
		}	
	}
	
	public void grow() {
		lenght += 25;
	}
	
	public void moveUp() {
		setPosY(getPosY() - 10);
	}
	
	public void moveDown() {
		setPosY(getPosY() + 10);
	}
	
	public void moveLeft() {
		setPosX(getPosX() - 10);
	}
	
	public void moveRight() {
		setPosX(getPosX() + 10);
	}
}