
public class AppVersionPluginExtension {

	private String fileLocation = "app.version.properties";
	private String versionPropertyKey = "app.version";

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getVersionPropertyKey() {
		return versionPropertyKey;
	}

	public void setVersionPropertyKey(String versionPropertyKey) {
		this.versionPropertyKey = versionPropertyKey;
	}
}
