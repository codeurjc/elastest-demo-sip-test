package org.elastest.elastest_demo_sip_test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class RunSippTest {

	@Test
	public void simplePlainSeleniumTest() {
		String ip = System.getenv("IP");
		if (ip == null || ip == "") {
			ip = "172.16.1.11";
		}
		Process p = this.exec("sipp -nostdin -sn uac -m 1 " + ip);

		try {
			p.waitFor();

			if (p.exitValue() == 0) {
				assertTrue("OK", true);
			} else {
				assertFalse("ERROR", false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		p.destroy();
	}

	public Process exec(String command) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
			this.printOut(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void printOut(Process proc) {
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// read any errors from the attempted command
		try {
			while ((s = stdError.readLine()) != null) {
				System.err.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
