package inputform.backend.config

import com.google.inject.{Guice, Module}
import net.codingwell.scalaguice.InjectorExtensions._
import inputform.backend.service.Service

trait Initializer {
  def initialize(module: Module): Service = {
    val injector = Guice.createInjector(module)
    injector.instance[Service]
  }
}
