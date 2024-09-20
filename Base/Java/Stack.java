class Stack{
	
	public Cell top;
	
	Stack(){
		top = null;
	}

	Stack(int x){
		top = new Cell(x);
	}

	public void push(int x){
		Cell tmp = new Cell(x);
		tmp.next = top;
		top = tmp;
	}

	public int pop() throws Exception {
		if(top == null)
			throw new Exception("ERROR: Stack is empty");
		int back = top.data;
		Cell tmp = top;
		top = top.next;
		tmp.next = null;
		return back;
	}

	public void print(){
		print(top);
		System.out.println("");
	}

	private void print(Cell current){
		if(current.next != null)
			print(current.next);
		System.out.print(current.data + " ");
	}
}
