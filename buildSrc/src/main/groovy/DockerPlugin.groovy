import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('hello') {
            doLast {
                println 'Hello from the GreetingPlugin'
            }
        }
    }
}

class DockerPluginExtension {
    String imageName = 'my-image'
    String containerName = 'my-container'
    String dockerfileLocation = '.'
}