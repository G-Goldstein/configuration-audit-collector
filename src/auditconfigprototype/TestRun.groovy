package auditconfigprototype

import groovy.json.*


String rootDirectory = "//172.30.30.243/jhc/SGSSUAT/Config/Envs/"
String outputFileName = "SGSSUAT"

outputFileName += ".json"

directory = new Directory(rootDirectory, "/")

configFileJson = JsonOutput.toJson(ConfigFileCollector.instance.collectConfigFiles(directory))

String outputFilePath = rootDirectory + outputFileName

def outputFile = new File(outputFilePath)

outputFile.write(JsonOutput.prettyPrint(configFileJson))

println "output written to $outputFilePath"