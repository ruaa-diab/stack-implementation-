

public class CursorStack<T extends Comparable<T>> implements Stackable<T> {

	private CursorArray<T> cursorArray;
	private int listIdx;
	
	
public CursorStack(int size) {
	
	cursorArray =new CursorArray<>(size+2);
	listIdx=cursorArray.createList();
}



@Override
public void push(T data) {
	if(!cursorArray.isEmpty(0)) {
		cursorArray.insertAtHead(data, listIdx);
		
		
	}else {
		System.out.println("stack out of space");
	}
}

@Override
public T pop() {
	if(cursorArray.isEmpty(0)) {
       return null;
	}else {
		
	
	return cursorArray.deleteFromHead(listIdx).getData();
}}


@Override
public T peek() {
	if(!cursorArray.isEmpty(0)) {
		T data=cursorArray.deleteFromHead(listIdx).getData();
		cursorArray.insertAtHead(data, listIdx);
		return data;
	}else {
		return null;
	}
}

	@Override
	public boolean isEmpty() {
		return cursorArray.isEmpty(listIdx);
	}

	@Override
	public void clear() {
		cursorArray.clear();
	}

}
