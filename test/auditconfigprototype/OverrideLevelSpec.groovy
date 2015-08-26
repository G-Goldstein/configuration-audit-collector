package auditconfigprototype;

import static org.junit.Assert.*;

import org.junit.Test;

class OverrideLevelSpec {

	private OverrideLevel overrideLevel
	private String header
	private List <String> lines
	private List <KeyValue> pairs
	
	@Test
	public void test() {
		
		header = "[Header]"
		lines = ["colour=red",
				 "equalssign==",
				 "shape=rectangle"]
		
		pairs = [new KeyValue("colour","red"),
				 new KeyValue("equalssign","="),
				 new KeyValue("shape", "rectangle")]
		
		overrideLevel = new OverrideLevel(header, lines)
		assert overrideLevel.levelDescription == header
		assert overrideLevel.keyValuePairs[0].key == pairs[0].key
		assert overrideLevel.keyValuePairs[0].value == pairs[0].value
		assert overrideLevel.keyValuePairs[1].key == pairs[1].key
		assert overrideLevel.keyValuePairs[1].value == pairs[1].value
		assert overrideLevel.keyValuePairs[2].key == pairs[2].key
		assert overrideLevel.keyValuePairs[2].value == pairs[2].value
		assert overrideLevel.keyValuePairs.size() == pairs.size()
	}

}