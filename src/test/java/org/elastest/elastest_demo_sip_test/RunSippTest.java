package org.elastest.elastest_demo_sip_test;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class RunSippTest {

	@Test
	public void simplePlainSeleniumTest() {
		String ip = System.getenv("IP");
		if (ip == null || ip == "") {
			ip = "172.16.1.11";
		}
		Process p = this.exec("sipp -sn uac -m 1 " + ip);

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
}
