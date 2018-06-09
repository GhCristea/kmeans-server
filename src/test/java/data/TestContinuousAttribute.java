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
class TestContinuousAttribute {
Attribute attribute;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		attribute = new ContinuousAttribute("Nome", 0, 0, 1);
	}

	/**
	 * Test method for {@link data.ContinuousAttribute#ContinuousAttribute(java.lang.String, int, double, double)}.
	 */
	@Test
	void testContinuousAttribute() {
		assertNotNull(attribute);
	}

	/**
	 * Test method for {@link data.ContinuousAttribute#getScaledValue(double)}.
	 */
	@Test
	void testGetScaledValue() {
		assertEquals(1.0,((ContinuousAttribute)attribute).getScaledValue(new Double(1)));
	}

}
