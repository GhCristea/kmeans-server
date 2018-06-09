/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Cristea Gheorghita
 *
 */
class TestDiscreteAttribute {

	Attribute attribute;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		TreeSet<String> inputValues = new TreeSet<>();
		inputValues.add("firstValue");
		inputValues.add("secondValue");
		inputValues.add("secondValue");
		attribute = new DiscreteAttribute("", 1, inputValues);
	}

	/**
	 * Test method for {@link data.DiscreteAttribute#DiscreteAttribute(java.lang.String, int, java.util.TreeSet)}.
	 */
	@Test
	void testDiscreteAttribute() {
		assertNotNull(attribute);
	}

	/**
	 * Test method for {@link data.DiscreteAttribute#getNumberOfDistinctValues()}.
	 */
	@Test
	void testGetNumberOfDistinctValues() {
		assertEquals(2, ((DiscreteAttribute)attribute).getNumberOfDistinctValues());
	}

	/**
	 * Test method for {@link data.DiscreteAttribute#frequency(data.Data, java.util.Set, java.lang.String)}.
	 */
	@Disabled
	void testFrequency() {
		
	}

	/**
	 * Test method for {@link data.DiscreteAttribute#iterator()}.
	 */
	@Test
	void testIterator() {
		assertTrue(((DiscreteAttribute)attribute).iterator() instanceof Iterator<?>);
	}

}
