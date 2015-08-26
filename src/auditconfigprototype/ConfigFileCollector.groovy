package auditconfigprototype

/**
 * Created by goldsteing on 19/08/2015.
 */
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
