package com.iekakmi.eshop_api;

import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

//Without @SpringBootTest annotation, a class won't be considered as a Testing class
@SpringBootTest
class EshopApiApplicationTests
{
	// A Test example [Right Click => Run As => JUnit Test]
	@Test
	void contextLoads()
	{
		boolean isTrue = true;

		// Ways one can test a case
		// Assertions.assertThat(OBJECT).isSOMETHING();
		Assertions.assertThat(isTrue).isTrue();
		Assertions.assertThat(isTrue).isEqualTo(true);
		Assertions.assertThat(isTrue).isNotEqualTo(false);
	}
}
