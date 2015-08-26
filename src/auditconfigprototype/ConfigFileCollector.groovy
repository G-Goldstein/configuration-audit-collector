package auditconfigprototype

@Singleton
class ConfigFileCollector {
	
	
	
    List <ConfigFile> collectConfigFiles(Directory directory) {
        List <ConfigFile> files = directory.configFiles
        directory.subDirectories.each {
            files += collectConfigFiles(it)
        }
        files
    }

}
