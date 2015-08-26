package auditconfigprototype

import java.io.File;

class Directory {
  
  List <Directory> subDirectories
  List <ConfigFile> configFiles
  String relativeDirectory
  private String dir
    
  Directory(String inDir, String relDir) {
	  File dirFile
	  dir = inDir
	  relativeDirectory = relDir
	  println relativeDirectory
	  dirFile = new File(dir)
	  configFiles = []
	  subDirectories = []
	  dirFile.eachFileMatch ~/.*\.ini/, {
		  configFiles << new ConfigFile(it.absolutePath, relativeDirectory)
	  }
	  dirFile.eachDir {
		  subDirectories << new Directory(it.absolutePath, relativeDirectory + "$it.name/")
	  }
  }
}