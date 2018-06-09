/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Cristea Gheorghita
 *
 */
class TestContinuousItem {

	Item item;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Attribute attribute = new ContinuousAttribute("continuous attribute", 0, 0, 1);
		item = new ContinuousItem(attribute, 2);
	}

	/**
	 * Test method for {@link data.ContinuousItem#distance(java.lang.Object)}.
	 */
	@Disabled
	void testDistance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.ContinuousItem#ContinuousItem(data.Attribute, java.lang.Object)}.
	 */
	@Disabled
	void testContinuousItem() {
		fail("Not yet implemented");
	}

}
