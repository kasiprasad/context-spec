package com.kasiprasad.contextspec.examples;

import com.kasiprasad.contextspec.ContextSpecification;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * When using Context Specification - you can group tests around a
 * specific Context within a single container class.
 * <p>
 * In JUnit, you MUST use the @RunWith(Enclosed.class)
 * runner on your exterior/container class to allow the framework
 * to find tests defined on inner classes
 */
@RunWith(Enclosed.class)
public class SimpleExampleTest {

	/**
	 * When writing tests using Context Specification, you should name
	 * your classes specific to the test case/scenario (Context) you
	 * are testing (Specifying)
	 * <p>
	 * Its typical in Context Specification to have test cases written as:
	 * <p>
	 * When_[doing something]
	 * <p>
	 * optionally you may also add some detail around the setup (Context) as:
	 * <p>
	 * When_[doing something]_[and the Context is setup as...]
	 */


	public static class When_getting_an_item_from_the_repository_provided_with_a_known_id extends ContextSpecification<MyRepository> {

		/**
		 * Define any test data variables as needed
		 */
		private int id;

		private String result;


		/**
		 * The context method is the first method executed before running
		 * a test and its job is to perform any setup/initialization required
		 */
		@Override
		public void context() {
			/**
			 * For illustrative purposes, we will initialize the input
			 * here but for a simple value type, we could have just initialized
			 * the variable in line (i.e. - id = 1)
			 *
			 * More commonly, we would initialize any Mocks needed here
			 */

			id = 1;
		}

		/**
		 * The createSUT method is the second method executed before running
		 * a test and its job is to Create the System Under Test (SUT)
		 *
		 * @return The system under test
		 */
		@Override
		public MyRepository createSUT() {
			//The createSUT method Creates the System Under Test (T)
			System.out.println("The createSUT method is run after the context method and " +
					"should return an instance of the System Under Test (SUT)");
			return new MyRepository();
		}

		/**
		 * The because method is the last method executed before running a test
		 * and its job is to execute something (preferably - a single method) from
		 * the System Under Test - providing any specific inputs required accordingly
		 * <p>
		 * For example:
		 * because() { result = SUT.add(1,1) }
		 */
		@Override
		public void because() {
			try {
				result = SUT.get(id);
			} catch (IllegalArgumentException e) {
				fail(e.getMessage());
			}
		}

		/**
		 * Test (Specification) method below for which the above Context has been defined
		 */
		@Test
		public void Should_return_a_message_containing_the_given_id() {
			assertTrue(result.contains(Integer.toString(id)));
		}
	}


	/**
	 * An example where the test/specification expects that the behavior under test will cause
	 * an exception to be thrown
	 */


	public static class When_attempting_to_get_an_item_from_the_repository_provided_with_an_invalid_id extends ContextSpecification<MyRepository> {

		private IllegalArgumentException exception;

		@Override
		public void context() {
			//no-op as needed
		}

		@Override
		public MyRepository createSUT() {
			//The createSUT method Creates the System Under Test (T)
			return new MyRepository();
		}

		@Override
		public void because() {
			try {
				SUT.get(100);
			} catch (IllegalArgumentException e) {
				exception = e;
			}
		}

		@Test
		public void Should_throw_an_illegal_argument_exception() {
			assertNotNull(exception);
		}
	}


	/**
	 * An example of sharing Context/SUT creation across test cases
	 */

	public abstract static class When_getting_an_item_from_the_repository extends ContextSpecification<MyRepository> {


		@Override
		public void context() {
			/**
			 * for this simple example, there is no context which needs to be shared
			 */
		}

		@Override
		public MyRepository createSUT() {
			return new MyRepository();
		}
	}

	public static class When_getting_an_item_from_the_repository_provided_with_a_known_id_second_example extends  When_getting_an_item_from_the_repository {

		private int id = 1;
		private String result;

		@Override
		public void because() {
			result = SUT.get(id);
		}

		@Test
		public void Should_return_a_message_containing_the_given_id() {
			assertTrue(result.contains(Integer.toString(id)));
		}
	}

	public static class When_attempting_to_get_an_item_from_the_repository_provided_with_an_invalid_id_second_example extends When_getting_an_item_from_the_repository {

		private IllegalArgumentException exception;

		@Override
		public void because() {
			try {
				SUT.get(100);
			} catch (IllegalArgumentException e) {
				exception = e;
			}
		}

		@Test
		public void Should_throw_an_illegal_argument_exception() {
			assertNotNull(exception);
		}
	}
}
