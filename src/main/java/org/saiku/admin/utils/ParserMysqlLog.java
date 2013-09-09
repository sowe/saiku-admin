package org.saiku.admin.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.saiku.admin.model.QueryBBDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParserMysqlLog {

	static final Logger logger = LoggerFactory.getLogger(ParserMysqlLog.class);


	public List<QueryBBDD> findQueries(String pathLog) {

		String contentLog = this.readFile(pathLog);

		List<QueryBBDD> retval = this.parseLog(contentLog);

		Collections.reverse(retval);

		return retval;
	}


	private List<QueryBBDD> parseLog (String contentLog) {

		Pattern p = Pattern.compile("sql \\[(.*?)\\]");
		Matcher m = p.matcher(contentLog);

		List<QueryBBDD> retval = new ArrayList<QueryBBDD>();

		while (m.find()) {
			retval.add(new QueryBBDD(m.group(1)));
		}

		return retval;
	}


	private String readFile(String filename) {
	   String content = null;
	   File file = new File(filename);
	   try {
	       FileReader reader = new FileReader(file);
	       char[] chars = new char[(int) file.length()];
	       reader.read(chars);
	       content = new String(chars);
	       reader.close();
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	   return content;
	}
}
