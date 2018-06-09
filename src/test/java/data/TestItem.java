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
class TestItem {

	Item item;
	Attribute attribute = new Attribute("Name", 1) {
	};
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	
		item = new Item(attribute, "value") {

			@Override
			public double distance(Object a) {
				return 0;
			}
			
		};
	}

	/**
	 * Test method for {@link data.Item#Item(data.Attribute, java.lang.Object)}.
	 */
	@Test
	void testItem() {
		assertNotNull(item);
	}

	/**
	 * Test method for {@link data.Item#getAttribute()}.
	 */
	@Test
	void testGetAttribute() {
		Attribute localAttribute = new Attribute("Name", 1) {
		};
		
		assertEquals(localAttribute.getName(), item.getAttribute().getName());
		assertEquals(localAttribute.getIndex(), item.getAttribute().getIndex());
	}

	/**
	 * Test method for {@link data.Item#getValue()}.
	 */
	@Test
	void testGetValue() {
		assertEquals("value", item.getValue());
	}

	/**
	 * Test method for {@link data.Item#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("value", item.toString());
	}

	/**
	 * Test method for {@link data.Item#distance(java.lang.Object)}.
	 */
	@Disabled
	void testDistance() {
	}

	/**
	 * Test method for {@link data.Item#update(data.Data, java.util.Set)}.
	 */
	@Disabled
	void testUpdate() {
		fail("Not yet implemented");
	}

}
