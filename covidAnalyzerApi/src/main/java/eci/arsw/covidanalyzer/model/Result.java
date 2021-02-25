package eci.arsw.covidanalyzer.model;

import java.util.UUID;

public class Result {
    private UUID id;
    private ResultType resultType;
    private String firstName;
    private String lastName;
    private String testString;
    private boolean result;
    private double testSpecifity;

    private int timesTested;

    public Result(UUID id, ResultType resultType, String firstName, String lastName, String testString, boolean result, double testSpecifity){
        this.id = id;
        this.resultType = resultType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.testString = testString;
        this.result = result;
        this.testSpecifity = testSpecifity;
        this.timesTested = 0;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public double getTestSpecifity() {
		return testSpecifity;
	}

	public void setTestSpecifity(double testSpecifity) {
		this.testSpecifity = testSpecifity;
	}

	public int getTimesTested() {
		return timesTested;
	}

	public void setTimesTested(int timesTested) {
		this.timesTested = timesTested;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", resultType=" + resultType + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", testString=" + testString + ", result=" + result + ", testSpecifity=" + testSpecifity
				+ ", timesTested=" + timesTested + ", toString()=" + super.toString() + "]";
	}
	
    @Override
    public boolean equals(Object o) {
        return ((Result) o).getId().equals(this.id);
    }
    
    public void newTestTaken(){
        this.timesTested++;
    }


}
