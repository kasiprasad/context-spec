package com.github.kasiprasad.contextspec;


import org.junit.Before;


/**
 * Context Specification
 * <p>
 * Provides the basic structure for authoring/executing Context Specification
 * style tests in Java using JUnit
 * <p>
 * Context Specification is a lot like a traditional BDD style testing framework
 * except that it is more code/dev friendly. No external editors/files/etc
 * just plain old code (Java in this case)
 *
 *
 * A Context Specification must target some Instance type (the System Under Test)
 * <p>
 * If you would like to write a Context Specification style test against a Static Type/Method
 * use the {@link StaticContextSpecification} class instead.
 *
 * @param <T> The Type of System Under Test (SUT)
 */
public abstract class ContextSpecification<T> extends StaticContextSpecification {

	/**
	 * The context method is the first method executed before running
	 * a test and its job is to perform any setup/initialization required
	 */
	public abstract void context();


	/**
	 * The createSUT method is the second method executed before running
	 * a test and its job is to Create the System Under Test (SUT)
	 *
	 * @return The system under test
	 */
	public abstract T createSUT();


	/**
	 * The System Under Test (SUT)
	 * <p>
	 * This variable will only be available after the 'createSUT' method is executed
	 * Its intended for usage within the because() method
	 */
	protected T SUT;


	/**
	 * The because method is the last method executed before running a test
	 * and its job is to execute something (preferably - a single method) from
	 * the System Under Test - providing any specific inputs required accordingly
	 * <p>
	 * For example:
	 * because() { result = SUT.add(1,1) }
	 */
	public abstract void because();


	@Before
	public void before() {
		context();
		SUT = createSUT();
		because();
	}

	/**
	 * Implementors of this class should add at least one (and arguably - at most one)
	 * @Test (Specification) method below for which the above Context has been defined
	 */
}
