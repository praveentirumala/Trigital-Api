package com.trigital.cm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.trigital.cm.domain.MACDetails;

@Service
public class ExecuteShellComand {

	public String shellCommandExecuter(String ipAddress,String macAddress) {
		final ExecuteShellComand esc = new ExecuteShellComand();

		final PropertyManager propertyManager = new PropertyManager();

		String command = "rsh -l root "+ipAddress+" shcm "+macAddress;
		System.out.println(command);
		//"rsh -l root 125.99.127.30 shcm a089.e439.abc4"
		return esc.executeCommand(command);

	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();
		File file;
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line+System.lineSeparator());				
				
			}
			p.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
