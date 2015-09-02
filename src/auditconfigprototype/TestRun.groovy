package auditconfigprototype

import groovy.json.*
import javax.swing.filechooser.FileFilter
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.UIManager


UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

String chooseDirectory(String dialogue, String nameMatch="") {
	
	def directoryChooser = new JFileChooser(
		fileSelectionMode: JFileChooser.DIRECTORIES_ONLY)
	
	FileFilter directoryFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory();
		}
		public String getDescription() {
			if (nameMatch == "") {
				return "Directory"
			} else {
				return "$nameMatch directory"
			}
		}
	}
	
	directoryChooser.setAcceptAllFileFilterUsed(false);
	directoryChooser.setFileFilter(directoryFilter)
	directoryChooser.setDialogTitle(dialogue)
	
	int directoryChooserResult = directoryChooser.showOpenDialog()
	if (directoryChooserResult == JFileChooser.APPROVE_OPTION && directoryChooser.getSelectedFile().exists()) {
		if ((nameMatch == "") || (nameMatch == directoryChooser.getSelectedFile().getName())) {
			String directory = directoryChooser.getSelectedFile().getPath().replaceAll('\\\\','/') + '/'
			return directory
		} else {
			throw new IOException("Invalid directory selected: '" + directoryChooser.getSelectedFile().getName() + "' chosen when '" + nameMatch + "' needed")
		}
	} else {
	throw new IOException("Invalid directory selected")
	}
}

String sourceDirectory = chooseDirectory("Choose source directory", "Config")
String outputDirectory = chooseDirectory("Choose output directory")

println "source: $sourceDirectory"
println "output: $outputDirectory"

String nameGuess = sourceDirectory.reverse().drop(1).dropWhile { it != '/' }.drop(1).takeWhile { it != '/' }.reverse()

JFrame frame = new JFrame("Output file name")

String outputFileName = JOptionPane.showInputDialog(
					frame,
					"Enter a name for the resulting output file",
					"Output file name",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					nameGuess) + ".json";

directory = new Directory(sourceDirectory)

configFileJson = JsonOutput.toJson(ConfigFileCollector.instance.collectConfigFiles(directory))

String outputFilePath = outputDirectory + outputFileName

def outputFile = new File(outputFilePath)

outputFile.write(JsonOutput.prettyPrint(configFileJson))

println "\noutput written to $outputFilePath"