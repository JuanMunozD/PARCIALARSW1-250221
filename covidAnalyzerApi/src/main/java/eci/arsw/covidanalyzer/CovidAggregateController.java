package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/covid/result")
public class CovidAggregateController {
	@Autowired
	@Qualifier("service-Covid")
	ICovidAggregateService covidService;

	//POST
	@RequestMapping(value = "/true-positive", method = RequestMethod.POST)
	public ResponseEntity<?> addTruePositive(@RequestBody Result result) {
		try {
			covidService.aggregateResult(result, ResultType.TRUE_POSITIVE);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 409: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/true-negative", method = RequestMethod.POST)
	public ResponseEntity<?> addTrueNegative(@RequestBody Result result) {
		try {
			covidService.aggregateResult(result, ResultType.TRUE_NEGATIVE);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 409: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/false-positive", method = RequestMethod.POST)
	public ResponseEntity<?> addFalsePositive(@RequestBody Result result) {
		try {
			covidService.aggregateResult(result, ResultType.FALSE_POSITIVE);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 409: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/false-negative", method = RequestMethod.POST)
	public ResponseEntity<?> addFalseNegative(@RequestBody Result result) {
		try {
			covidService.aggregateResult(result, ResultType.FALSE_NEGATIVE);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 409: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	//GET
	@RequestMapping(value = "/true-positive", method = RequestMethod.GET)
	public ResponseEntity<?> getTruePositive() {
		try {
			return new ResponseEntity<>(covidService.getResult(ResultType.TRUE_POSITIVE), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 404: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/true-negative", method = RequestMethod.GET)
	public ResponseEntity<?> getTrueNegative() {
		try {
			return new ResponseEntity<>(covidService.getResult(ResultType.TRUE_NEGATIVE), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 404: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/false-positive", method = RequestMethod.GET)
	public ResponseEntity<?> getFalsePositive() {
		try {
			return new ResponseEntity<>(covidService.getResult(ResultType.FALSE_POSITIVE),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 404: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/false-negative", method = RequestMethod.GET)
	public ResponseEntity<?> getFalseNegative() {
		try {
			return new ResponseEntity<>(covidService.getResult(ResultType.FALSE_NEGATIVE),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 404: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	//PUT
	@RequestMapping(value = "/persona/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> savePersonaWithMultipleTests(@PathVariable("id") UUID id) {
		try {
			covidService.upsertPersonWithMultipleTests(id, ResultType.FALSE_NEGATIVE);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR 409: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}