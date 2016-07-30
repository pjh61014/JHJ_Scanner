package jhj.scanner.bo;

import org.python.core.PyInstance;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class main {
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	private static void test1(){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("import hello_world");
		PyObject func = interpreter.get("hello_world1").__getattr__("get_name");
		System.out.println(func.__call__().__tojava__(String.class));
	}
	
	private static void test2(){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("from hello_world2 import HelloWorld");
		PyObject obj = (PyInstance)interpreter.eval("HelloWorld()");
		System.out.println(obj.invoke("get_name").__tojava__(String.class));
	}

}
