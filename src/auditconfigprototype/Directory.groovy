package auditconfigprototype

import java.io.File;
import java.util.List;

class Directory {
  
  List <Directory> subDirectories
  List <ConfigFile> configFiles
  String relativeDirectory
  private String dir
  
  List <String> ignoreDirectories = ['Archive', 'archive']
    
  Directory(String inDir, String relDir) {
	  File dirFile
	  dir = inDir
	  relativeDirectory = relDir
	  println relativeDirectory
	  dirFile = new File(dir)
	  configFiles = []
	  subDirectories = []
	  dirFile.eachFileMatch ~/.*\.(properties|ini|pref)/, {
		  configFiles << new ConfigFile(it.absolutePath, relativeDirectory)
	  }
	  dirFile.eachDir { d ->
		  if (!(d.name in ignoreDirectories)) {
		    subDirectories << new Directory(d.absolutePath, relativeDirectory + "$d.name/")
		  }
	  }
	  configFiles = configFiles.findAll { it.overrideLevels.size > 0 }
  }
  
}