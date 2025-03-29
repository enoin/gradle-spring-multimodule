import org.gradle.api.*;

import javax.inject.Inject;
import java.util.Optional;

public class AppVersionPlugin implements Plugin<Project> {

	final Project project;

	@Inject
	public AppVersionPlugin(Project project) {
		this.project = project;
	}

	@Override
	public void apply(Project project) {
		final AppVersionPluginExtension extension = project.getExtensions()
						.create("appVersion", AppVersionPluginExtension.class);

		project.getTasks().register("setAppVersion", AppVersionTask.class);
		project.afterEvaluate(p -> {
			getProperty("versionPropertyKey").ifPresent(extension::setVersionPropertyKey);
			getProperty("fileLocation").ifPresent(extension::setFileLocation);
		});
	}

	private Optional<String> getProperty(final String prop) {
		return project.hasProperty(prop) ?
				Optional.ofNullable(project.property(prop)).map(Object::toString) :
				Optional.empty();
	}
}

