package com.kasiprasad.contextspec;

import org.junit.Before;

public abstract class StaticContextSpecification {


	/**
	 * The context method is the first method executed before running
	 * a test and its job is to perform any setup/initialization required
	 *
	 */
	public abstract void context();


	/**
	 * The because method is the last method executed before running a test
	 * and its job is to execute something (preferably - a single method) from
	 * the System Under Test - providing any specific inputs required accordingly
	 *
	 * For example:
	 * because() { result = SUT.add(1,1) }
	 *
	 */
	public abstract void because();


	@Before
	public void before() {
		context();
		because();
	}

	/**
	 * Implementors of this class should add at least one (and arguably - at most one)
	 * @Test (Specification) method below for which the above Context has been defined
	 */
}
