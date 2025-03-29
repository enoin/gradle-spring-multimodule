import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public abstract class AppVersionTask extends DefaultTask {

	private final Project project;
	private final Logger logger;
	private final AppVersionPluginExtension extension;

	public AppVersionTask() {
		project = getProject();
		logger = project.getLogger();
		extension = project.getExtensions().findByType(AppVersionPluginExtension.class);
	}

	@TaskAction
	public void generateVersionTaskAction() {
		logger.lifecycle("generate version file to: " + extension.getFileLocation());
		setVersionFile();
	}

	private String getVersion() {
		final Provider<String> versionTag = project.getProviders().environmentVariable("VERSION_TAG");
		if (versionTag.isPresent()) {
			logger.lifecycle("Using VERSION_TAG {}", versionTag);
			return versionTag.get();
		}
		return getCurrentBranch() + "-" + getCurrentHash();
	}

	private String getCurrentBranch() {
		return executeCommand("git", "rev-parse", "--abbrev-ref", "HEAD");
	}

	private String getCurrentHash() {
		return executeCommand("git", "rev-parse", "--short", "HEAD");
	}

	private String executeCommand(final Object ... command) {
		return project.getProviders()
				.exec(execSpec -> {
					execSpec.setWorkingDir(project.getProjectDir());
					execSpec.commandLine(command);
					execSpec.setIgnoreExitValue(true);
				})
				.getStandardOutput()
				.getAsText()
				.get()
				.trim();
	}

	private void setVersionFile() {
		final String versionPropertyKey = extension.getVersionPropertyKey();
		final String versionFileLocation = extension.getFileLocation();
		final File file = project.getLayout()
				.getProjectDirectory()
				.file(versionFileLocation)
				.getAsFile();

		try {
			try (FileWriter writer = new FileWriter(file, false)) {
				writer.write(versionPropertyKey + "=" + getVersion());
				System.out.println("Appended to file: " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			logger.error("cannot set the version file!!", e);
		}
	}
}
