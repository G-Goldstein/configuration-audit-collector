package auditconfigprototype;

import static org.junit.Assert.*;

import org.junit.Test;

class OverrideLevelBuilderSpec {

	private OverrideLevel overrideLevel
	
	@Test
	public void test() {
		List <String> lines = ["[Level heading]",
								"colour=red",
								"shape=square",
								"[Second heading]",
								"colour=blue",
								"shape=circle"]
		
		OverrideLevel overrideLevel0 = new OverrideLevel("[Level heading]", ["colour=red", "shape=square"])
		OverrideLevel overrideLevel1 = new OverrideLevel("[Second heading]", ["colour=blue", "shape=circle"])
		List <Override> overrideLevels = OverrideLevelBuilder.instance.getOverrideSections(lines)

		assert overrideLevels[0].levelDescription == overrideLevel0.levelDescription
		assert overrideLevels[0].keyValuePairs.size == 2

		assert overrideLevels[0].keyValuePairs[0].key == overrideLevel0.keyValuePairs[0].key
		assert overrideLevels[0].keyValuePairs[0].value == overrideLevel0.keyValuePairs[0].value
		assert overrideLevels[0].keyValuePairs[1].key == overrideLevel0.keyValuePairs[1].key
		assert overrideLevels[0].keyValuePairs[1].value == overrideLevel0.keyValuePairs[1].value

		assert overrideLevels[1].levelDescription == overrideLevel1.levelDescription
		assert overrideLevels[1].keyValuePairs.size == 2

		assert overrideLevels[1].keyValuePairs[0].key == overrideLevel1.keyValuePairs[0].key
		assert overrideLevels[1].keyValuePairs[0].value == overrideLevel1.keyValuePairs[0].value
		assert overrideLevels[1].keyValuePairs[1].key == overrideLevel1.keyValuePairs[1].key
		assert overrideLevels[1].keyValuePairs[1].value == overrideLevel1.keyValuePairs[1].value
	}
}