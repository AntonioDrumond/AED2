class Cell{

	public int data;
	public Cell next;

	Cell(){
		this.next = null;
		this.data = 0;
	}

	Cell(int x){
		this.next = null;
		this.data = x;
	}
}
