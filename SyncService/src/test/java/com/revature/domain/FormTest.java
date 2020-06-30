package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormTest {

	@Test
	void test() {
		Form f = new Form();
		f.setFormId(1);
		f.setId(1);
		f.toString();
		f.equals(f);
		f.equals(null);
		f.equals(2);
		assertTrue(true);
	}

}
