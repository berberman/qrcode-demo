package cn.berberman.qrcode

import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.response.send
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.util.log.StdErrLog
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class QRController : KanaryController() {
	companion object {
		private val logger = StdErrLog.getLogger(QRController::class.java)
	}

	fun show(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
		response.contentType = "image/png;charset=utf-8"
		val content = request.getParameter("content")
		if (content == null) {
			response.characterEncoding = "UTF-8"
			response withStatus 415 send "Please input content text! (e.g. : /get?content=xxx)"
			logger.warn("请求失败! 地址:${request.remoteAddr}")

			baseRequest.done()
		} else {
			QRUtil.generateQRCodeToStream(content = content, outputStream = response.outputStream)
			Logger.getAnonymousLogger().info("请求成功! 地址:${request.remoteAddr} 内容:$content")
			baseRequest.done()
		}
	}

}