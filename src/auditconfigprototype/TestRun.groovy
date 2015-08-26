package auditconfigprototype

import groovy.json.*


String rootDirectory = "//172.30.30.243/jhc/SGSSPreProd/Config/"
String outputPath = "C:/Users/goldsteing/Desktop/Projects/Audit Configuration/viewer/data/"
String outputFileName = "SGSSPreProd"

outputFileName += ".json"

directory = new Directory(rootDirectory, "/")

configFileJson = JsonOutput.toJson(ConfigFileCollector.instance.collectConfigFiles(directory))

String outputFilePath = outputPath + outputFileName

def outputFile = new File(outputFilePath)

outputFile.write(JsonOutput.prettyPrint(configFileJson))

println "output written to $outputFilePath"