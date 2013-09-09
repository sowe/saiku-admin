package org.saiku.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.saiku.admin.model.QueryBBDD;
import org.saiku.admin.model.mysql.ExplainData;
import org.saiku.admin.repository.ConfRepository;
import org.saiku.admin.repository.MysqlRepository;
import org.saiku.admin.utils.ParserMysqlLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@Service
public class PerformController {

    @Inject
    private MysqlRepository mysqlRepository;
    @Inject
    private ConfRepository confRepository;

    static final Logger logger = LoggerFactory.getLogger(PerformController.class);


    @RequestMapping(value="/api/perform", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public List<QueryBBDD> findAll() {

    	ParserMysqlLog parser = new ParserMysqlLog();

    	return parser.findQueries(confRepository.findFirst().getSaikuLogSql());
    }


    @RequestMapping(value = "/api/perform/explain", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ExplainData> explainQuery(@RequestParam(value="query") String query) {
    	return mysqlRepository.getExplainData(query);
    }
}
