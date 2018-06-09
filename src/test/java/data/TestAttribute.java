/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Cristea Gheorghita
 *
 */
class TestAttribute {
	Attribute attribute;
	@BeforeEach
	void init() {
		attribute = new Attribute("Nome", 0) {
		};
	}
	/**
	 * Test method for {@link data.Attribute#Attribute(java.lang.String, int)}.
	 */
	@Test
	void testAttribute() {
		assertNotNull(attribute);
	}

	/**
	 * Test method for {@link data.Attribute#getIndex()}.
	 */
	@Test
	void testGetIndex() {
		assertEquals(0, attribute.getIndex());
	}

	/**
	 * Test method for {@link data.Attribute#getName()}.
	 */
	@Test
	void testGetName() {
		assertEquals("Nome", attribute.getName());
	}

	/**
	 * Test method for {@link data.Attribute#toString()}.
	 */
	@Test
	void testToString() {
	assertEquals("Nome", attribute.toString());
	}

}
