import groovy.json.JsonOutput;

def path = args[0];

def toJson(String path) {
	def map = [:];
	def file = new File(path);
	if (file.isFile()) {
		return file.text;
	}

	file.eachFile { subFile ->
		if (!subFile.name.contains(".")) {
			map.put(subFile.name, toJson("$path/${subFile.name}"));
		}
	}

	return map;
}	

def result = toJson(path);
def json = JsonOutput.toJson(result);

println json;
	
