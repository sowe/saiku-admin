package org.saiku.admin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JavaRunCommand {
//	private String command_ = "mysqladmin -h ${ip} -u ${root} -p${tmppassword} status";

	static final Logger logger = LoggerFactory.getLogger(JavaRunCommand.class);

	public static String execute(String command) {

		String commandr = null;

		logger.info(command);

		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			Process process = Runtime.getRuntime().exec(command);
//			Process process = Runtime.getRuntime().exec("/Users/javi/Desktop/explain_mysql.sh");
//			Process process = Runtime.getRuntime().exec("mysqladmin -h localhost -u root status");

			try {
				process.waitFor();
			} catch (InterruptedException e) {
				logger.error("", e);
			}

			InputStream inputstream = process.getInputStream();

			commandr = stream2String(inputstream);


			logger.info(commandr);
		}
		catch (IOException e) {
			logger.error("", e);
		}
		return commandr;
	}
	
	
    public static String stream2String(InputStream is) throws IOException {

        if (is != null) {
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                        Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        int n;
                        while ((n = reader.read(buffer)) != -1) {
                                writer.write(buffer, 0, n);  
                        }
                } finally {
                        is.close();
                }
                return writer.toString();
        }else{
        	return "";
        }
    }
}
