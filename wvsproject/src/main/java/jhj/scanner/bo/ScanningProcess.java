package jhj.scanner.bo;

import java.io.IOException;
import java.io.StringWriter;

import org.python.core.*;
import org.python.util.PythonInterpreter;

public class ScanningProcess {

	public void init() {
		PythonInterpreter interp = new PythonInterpreter(null, new PySystemState());

		PySystemState sys = Py.getSystemState();
		sys.path.append(new PyString("c:/Python27/lib"));
		// sys.path.append(new
		// PyString("C:/Users/Administrator/Downloads/spring-tool-suite-3.7.3.RELEASE-e4.5.2-win32-x86_64/sts-bundle/sts-3.7.3.RELEASE/"));
		sys.path.append(new PyString("C:/Python27/Lib/site-packages"));
		sys.path.append(new PyString("C:/Users/Administrator/git/JHJ_Scanner/wvsproject"));
	}

	public void Scan(String url) {
		init();
		// url = "abcdd";

		System.out.println("비지니스로직 진입!!" + url);

		String[] arguments = { url };
		// String[] arguments = {"main.py", url};
		// PythonInterpreter.initialize(System.getProperties(),
		// System.getProperties(), arguments);

		PythonInterpreter python = new PythonInterpreter();
		python.initialize(System.getProperties(), System.getProperties(), arguments);
		StringWriter out = new StringWriter();
		python.setOut(out);
		python.execfile("main.py");
		String outputStr = out.toString();
		System.out.println(outputStr);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		ScanningProcess sp = new ScanningProcess();
		sp.Scan("aaabc");
	}
}