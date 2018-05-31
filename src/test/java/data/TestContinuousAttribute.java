/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Cristea Gheorghita
 *
 */
class TestContinuousAttribute {

	private static final Logger log = Logger.getLogger(DiscreteAttribute.class.getSimpleName());
	private ContinuousAttribute testContinuousAttribute;
	
	@BeforeAll
	public void init() {
	log.info("Class instantiation");
	testContinuousAttribute = new ContinuousAttribute("name", 0, 1, 10);
			}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.ContinuousAttribute#ContinuousAttribute(java.lang.String, int, double, double)}.
	 */
	@Test
	void testContinuousAttribute() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link data.ContinuousAttribute#getScaledValue(double)}.
	 */
	@Test
	void testGetScaledValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link data.Attribute#Attribute(java.lang.String, int)}.
	 */
	@Test
	void testAttribute() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link data.Attribute#getIndex()}.
	 */
	@Test
	void testGetIndex() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link data.Attribute#getName()}.
	 */
	@Test
	void testGetName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link data.Attribute#toString()}.
	 */
	@Test
	void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
