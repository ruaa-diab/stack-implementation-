
public class ArrayStack<T> {
	private Object[] s;
	private int n = -1;

	public ArrayStack(int capacity) {
		s = new Object[capacity];
	}

	public boolean isEmpty() {
		return n == -1;
	}

	public int getN() {
		return n;
	}

	public void push(T data) {
		s[++n] = data;
	}

	public Object pop() {
		if (!isEmpty())
			return s[n--];
		return null;
	}

	public String toString() {
		String res = "Top-->";
		for (int i = n; i >= 0; i--)
			res += "[" + s[i] + "]-->";
		return res + "Null";
	}

	public static String revers(String g) {

		if (g.isEmpty()) {
			return null;
		}
		ArrayStack<Character> arr = new ArrayStack<>(g.length());
		for (int i = 0; i < g.length(); i++) {
			arr.push(g.charAt(i));
		}
		String res = " ";
		while (!arr.isEmpty()) {
			res += arr.pop();
		}
		return res;

	}

	public static void main(String[] args) {

		System.out.println(revers("ruaa"));

	}

}
