package eci.arsw.covidanalyzer.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;

@Service("service-Covid")
public class ICovidAggregateServiceImpl implements ICovidAggregateService {
	
    private List<Result> resultList = new CopyOnWriteArrayList<>();

    public ICovidAggregateServiceImpl() {
        resultList.add(new Result(UUID.randomUUID(), ResultType.FALSE_POSITIVE,"Pepito","Perez","2019-05-22T15:65:45Z",false,0.98));
        resultList.add(new Result(UUID.randomUUID(),ResultType.TRUE_POSITIVE, "Juan","Munoz","2021-01-10T09:13:44Z",false,0.61));
        resultList.add(new Result(UUID.randomUUID(), ResultType.FALSE_NEGATIVE, "Alejandro","Toro","2020-04-15T22:12:32Z",false,0.51));
    }

    @Override
    public void aggregateResult(Result result, ResultType type) throws Exception {
    	try {
            if(result.getResultType() == type){
                resultList.add(result);
            }else{
                throw new Exception("Result doesnt match.");
            }
    	}
        catch(Exception e) {
        	throw new Exception("Server error");
        }

    }

    @Override
    public List<Result> getResult(ResultType type) throws Exception {
        List<Result> Tests = new CopyOnWriteArrayList<>();
        for(Result result : resultList){
            if(result.getResultType() == type){
            	Tests.add(result);
            }
        }
        if(Tests.size() != 0){
            return Tests;
        }else{
            throw new Exception("It doesnt exists any result.");
        }
    }

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) throws Exception {
		boolean flag = false;
		for (Result result : resultList) {
			if (result.getId() == id) {
				flag = true;
				result.setResultType(type);
			}
		}
		if (!flag) {
			throw new Exception("It doesnt exist any person in the list.");
		}
	}

}
