package auditconfigprototype

import java.io.File;
import java.util.List;

class ConfigFile {
  List <OverrideLevel> overrideLevels
  String fileName
  String relativePath
  
  private File file
  private def newLine = '\n'
  private String overrideHeading
  private List <String> overrideLines
  
  ConfigFile(String inPath, String relDir) {
	  
	  List <String> lines
	  
	  relativePath = relDir
	  fileName = inPath.reverse().takeWhile {c -> c != '\\'}.reverse()
	  println relativePath + fileName
	  file = new File(inPath)
	  lines = ConfigFileOps.instance.cleanLines(file.getText().split(newLine).toList())
	  overrideLevels = OverrideLevelBuilder.instance.getOverrideSections(lines)
	  overrideLevels = overrideLevels.findAll { it.keyValuePairs.size > 0 }
  }
   
}

@Singleton
class ConfigFileOps {

	private def tab = '\t'
	private def returnCarriage = '\r'
	private def equals = '='
	private def nothing = ''
	private def squareBracket = '['
	private def commentChar = '#'
	
	List <String> cleanLines(List <String> lines) {
		lines = lines.findAll { (!it.startsWith(commentChar)) && (it.contains(equals) || it.startsWith(squareBracket)) }
					 .collect { it.replaceAll(tab, nothing) }
					 .collect { it.replaceAll(returnCarriage, nothing) }
					 .collect { it.trim() }
	}
	
}