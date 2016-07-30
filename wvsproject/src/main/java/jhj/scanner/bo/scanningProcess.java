package jhj.scanner.bo;

import java.io.InputStream;
import java.io.StringWriter;

import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class scanningProcess {

	/*
	 * public void init() { PythonInterpreter interp = new
	 * PythonInterpreter(null, new PySystemState());
	 * 
	 * PySystemState sys = Py.getSystemState(); sys.path.append(new
	 * PyString("c:/Python27/lib")); //sys.path.append(new PyString(
	 * "C:/Users/Administrator/Downloads/spring-tool-suite-3.7.3.RELEASE-e4.5.2-win32-x86_64/sts-bundle/sts-3.7.3.RELEASE/"
	 * )); sys.path.append(new PyString("C:/Python27/Lib/site-packages"));
	 * sys.path.append(new
	 * PyString("C:/Users/Administrator/git/JHJ_Scanner/wvsproject")); }
	 */
	public void Scan(String url) {
		// init();
		// url = "abcdd";

		System.out.println("비지니스로직 진입!!" + url);

		// String[] arguments = {url};
		String[] args = { url };

		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), args);
		PySystemState sys = Py.getSystemState();
		// sys.path.append(new
		// PyString("C:/Users/Administrator/git/JHJ_Scanner/wvsproject/src/main/java/jhj/scanner/bo/"));
		// sys.path.append(new
		// PyString("C:/Users/Administrator/git/JHJ_Scanner/wvsproject"));
		sys.path.append(new PyString("C:/Python27/Lib/"));
		sys.path.append(new PyString("C:/Python27/Lib/site-packages/mechanize-0.2.5-py2.7.egg"));
		// sys.path.append(new PyString("C:/Python27/Lib/**"));

		// sys.path.append(new PyString("C:/Python27/lib"));
		// sys.path.append(new
		// PyString("C:/Users/Administrator/git/JHJ_Scanner/wvsproject/src/main/java/scanner/"));
		PythonInterpreter python = new PythonInterpreter();
		StringWriter out = new StringWriter();
		python.setOut(out);
		python.execfile("C:/Users/Administrator/git/JHJ_Scanner/wvsproject/src/main/java/scanner/scanall.py");
		String outputStr = out.toString();
		System.out.println(outputStr);
	}

	public void test(String url){
		PySystemState state = new PySystemState();
		state.argv.append(new PyString(url));
		state.path.append(new PyString("C:/Python27/Lib/"));
		state.path.append(new PyString("C:/Python27/Lib/site-packages/"));
		state.path.append(new PyString("C:/Python27/Lib/site-packages/mechanize-0.2.5-py2.7.egg"));
		PythonInterpreter interpreter = new PythonInterpreter(null,state);
		StringWriter out = new StringWriter();
		interpreter.setOut(out);
		interpreter.execfile("C:/Users/Administrator/git/JHJ_Scanner/wvsproject/src/main/java/scanner/scanall.py");
		String outputStr = out.toString();
		System.out.println(outputStr);
	}
	
	

}