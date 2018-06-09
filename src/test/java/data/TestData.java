/**
 * 
 */
package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;

//import data.Data.Example;

/**
 * @author Cristea Gheorghita
 *
 */
class TestData {
	Data dataClass;
	//private List<String[]> data;
	private List<Attribute> explanatorySet = new LinkedList<>();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Set<String> values = new TreeSet<>();
		Attribute discreteAttribute = new DiscreteAttribute("discreteAttribute", 0, (TreeSet<String>) values);
		Attribute continuousAttribute = new ContinuousAttribute("continuousAttribute", 1, 0, 1);
		explanatorySet.add(discreteAttribute);
		explanatorySet.add(continuousAttribute);
		
		dataClass = new Data() {
			
		};
	}

	/**
	 * Test method for {@link data.Data#Data()}.
	 */
	@Disabled
	void testData() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getNumberOfExamples()}.
	 */
	@Disabled
	void testGetNumberOfExamples() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getNumberOfExplanatoryAttributes()}.
	 */
	@Disabled
	void testGetNumberOfExplanatoryAttributes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getAttributeValue(int, int)}.
	 */
	@Disabled
	void testGetAttributeValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getAttribute(int)}.
	 */
	@Disabled
	void testGetAttribute() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getAttributeSchema()}.
	 */
	@Disabled
	void testGetAttributeSchema() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#toString()}.
	 */
	@Disabled
	void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#main(java.lang.String[])}.
	 */
	@Disabled
	void testMain() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#computePrototype(java.util.Set, data.Attribute)}.
	 */
	@Disabled
	void testComputePrototype() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#getItemSet(int)}.
	 */
	@Disabled
	void testGetItemSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.Data#sampling(int)}.
	 */
	@Disabled
	void testSampling() {
		fail("Not yet implemented");
	}

}
