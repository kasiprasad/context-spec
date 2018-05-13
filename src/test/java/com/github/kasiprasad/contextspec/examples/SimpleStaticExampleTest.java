package com.github.kasiprasad.contextspec.examples;

import com.github.kasiprasad.contextspec.StaticContextSpecification;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNotNull;

@RunWith(Enclosed.class)
public class SimpleStaticExampleTest {

	public static class When_testing_some_emthod_on_my_simple_static_type extends StaticContextSpecification {
		private String result;

		@Override
		public void context() {
			//no setup needed in this case so no-op
		}

		@Override
		public void because() {
			result = MySimpleStaticType.someMethod();
		}

		@Test
		public void Should_return_something_other_than_null() {
			assertNotNull(result);
		}
	}

}
