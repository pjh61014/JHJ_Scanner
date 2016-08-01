package jhj.scanner.bo;

import java.util.LinkedList;
import java.util.List;

public class Stack {
	private List<String> internalList = new LinkedList<String>();
	private String url;

	public void push(String element) {
		internalList.add(0, element);
	}

	public String pop() {
		return internalList.remove(0);

	}

	public List<String> getInternalList() {
		return internalList;
	}

	public void pushAll(List<String> elements) {
		for (String element : elements) {
			this.push(element);
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public Stack getStack(Stack stack) {
		return stack;
	}
}