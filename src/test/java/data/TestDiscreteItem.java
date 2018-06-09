/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Cristea Gheorghita
 *
 */
class TestDiscreteItem {
	Item item;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Set<String> inputValues = new TreeSet<String>();
		inputValues.add("firstValue");
		inputValues.add("secondValue");
		inputValues.add("secondValue");
		Attribute attribute = new DiscreteAttribute("Name", 0, (TreeSet<String>) inputValues);
		item = new DiscreteItem((DiscreteAttribute) attribute, "firstValue");
	}

	/**
	 * Test method for {@link data.DiscreteItem#distance(java.lang.Object)}.
	 */
	@Test
	void testDistance() {
		assertEquals(0.0, item.distance("firstValue"));
		assertEquals(1.0, item.distance("secondValue"));
	}

	/**
	 * Test method for {@link data.DiscreteItem#DiscreteItem(data.DiscreteAttribute, java.lang.String)}.
	 */
	@Test
	void testDiscreteItem() {
		assertNotNull(item);
	}

}
