package jhj.scanner.bo;
import java.io.StringWriter;

import org.python.util.PythonInterpreter;

public class ScanningProcess {
	
	public void scan(String url)  {
		
		System.out.println("비지니스로직 진입!!"+url);
    	
    	//String[] arguments = {"iotest.py", url};
    	String[] arguments = {"iotest.py", url};
    	PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
    	org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
    	StringWriter out = new StringWriter();
    	python.setOut(out);
    	python.execfile("main.py");
    	String outputStr = out.toString();
    	System.out.println(outputStr);
	}

}