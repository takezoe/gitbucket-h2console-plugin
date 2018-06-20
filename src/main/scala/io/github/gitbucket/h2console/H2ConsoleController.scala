package io.github.gitbucket.h2console

import javax.servlet.ServletConfig
import javax.servlet.http.{HttpServletRequest, HttpServletRequestWrapper}

import gitbucket.core.controller.ControllerBase
import gitbucket.core.util.AdminAuthenticator

class H2ConsoleController extends ControllerBase with AdminAuthenticator {

  private val servlet = new org.h2.server.web.WebServlet(){
    override def getServletConfig(): ServletConfig = new DummyServletConfig()
  }
  servlet.init()

  get("/h2console")(adminOnly {
    servlet.doGet(new WrappedRequest(request, ""), response)
  })

  post("/h2console")(adminOnly {
    servlet.doGet(new WrappedRequest(request, ""), response)
  })

  get("/h2console/*")(adminOnly {
    servlet.doGet(new WrappedRequest(request, multiParams("splat").head), response)
  })

  post("/h2console/*")(adminOnly {
    servlet.doPost(new WrappedRequest(request, multiParams("splat").head), response)
  })

}

class WrappedRequest(request: HttpServletRequest, path: String) extends HttpServletRequestWrapper(request){
  override def getPathInfo = "/" + path
}

class DummyServletConfig extends ServletConfig {
  override def getServletContext = ???
  override def getServletName = ???
  override def getInitParameterNames = new java.util.Vector[String]().elements
  override def getInitParameter(name: String) = ???
}