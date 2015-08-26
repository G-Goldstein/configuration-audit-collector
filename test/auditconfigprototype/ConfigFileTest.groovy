package auditconfigprototype;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

class ConfigFileTest {
			
	@Test
	public void test() {
		
		List<String> dirtyLines = ["	Col	our=Red",
									"Some text here",
									"Shape=Square   "]
		
		List<String> cleanedLines = ["Colour=Red",
									  "Shape=Square"]
									
		assert ConfigFileOps.instance.cleanLines(dirtyLines) == cleanedLines
	}

}
