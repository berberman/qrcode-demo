package cn.berberman.qrcode

import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.core.KanaryRouter
import com.iyanuadelekan.kanary.handlers.AppHandler
import com.iyanuadelekan.kanary.middleware.simpleConsoleRequestLogger
import com.iyanuadelekan.kanary.server.Server

fun main(args: Array<String>) {
	val app = KanaryApp()
	val server = Server()
	val controller = QRController()
	val router = KanaryRouter()
	router on "qrcode/" use controller
	router.get("get/", controller::show)
	app.mount(router)
	app.use(simpleConsoleRequestLogger)
	server.handler = AppHandler(app)
	server.listen(2333)
}