import gitbucket.core.controller.Context
import gitbucket.core.plugin.Link
import io.github.gitbucket.h2console.H2ConsoleController
import io.github.gitbucket.solidbase.model.Version

class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "h2console-plugin"
  override val pluginName: String = "H2 Console Plugin"
  override val description: String = "Add H2 Console to the administration console."
  override val versions: Seq[Version] = List(new Version("1.0.0"))

  override val controllers = Seq(
    "/h2console/*" -> new H2ConsoleController()
  )

  override val systemSettingMenus: Seq[(Context) => Option[Link]] = Seq(
    (ctx: Context) => Some(Link("h2console", "H2 Console", "h2console/", Some("database")))
  )

}
