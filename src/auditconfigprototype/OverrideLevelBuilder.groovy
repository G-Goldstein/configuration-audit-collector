package auditconfigprototype

@Singleton
class OverrideLevelBuilder {
	private String currentHeading = "[Default]"
	private List <String> currentLines = []
	
	List <OverrideLevel> getOverrideSections(List <String> lines) {
		List <Override> overrides = []
		lines.each {
			if (isOverrideHeader(it)) {
				if (currentLines.size() > 0) {
					overrides << new OverrideLevel(currentHeading, currentLines)
				}
				currentHeading = it
				currentLines = []
			} else {
				currentLines << it
			}
		}
		overrides << new OverrideLevel(currentHeading, currentLines)
		currentHeading = "[Default]"
		currentLines = []
		return overrides
	}
  
	boolean isOverrideHeader(String line) {
		line.trim()
		line.startsWith("[") && line.endsWith("]")
	}
  
}
