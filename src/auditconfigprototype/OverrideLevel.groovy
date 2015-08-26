package auditconfigprototype

class OverrideLevel {
	List<KeyValue> keyValuePairs = []
	String levelDescription
	
	private String key
	private String value
	private def equals = '='
	
	OverrideLevel(header, lines) {
		levelDescription = header
		lines.each { l ->
			String line = l
			key = line.substring(0, line.indexOf(equals))
			value = line.substring(line.indexOf(equals)+1)
			keyValuePairs << new KeyValue(key, value)
		}
	}
}
