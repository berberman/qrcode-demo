package cn.berberman.qrcode

import com.google.zxing.*
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.HybridBinarizer
import java.io.InputStream
import java.io.OutputStream
import javax.imageio.ImageIO

object QRUtil {
	fun generateQRCodeToStream(width: Int = 300, height: Int = 300, content: String?, outputStream: OutputStream) {
		val hints = hashMapOf<EncodeHintType, Any>(
				EncodeHintType.CHARACTER_SET to "UTF-8"

		)
		val bitMatrix = MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints)
		MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream)
	}

	fun decodeQRCode(inputStream: InputStream): Result {
		val image = ImageIO.read(inputStream)
		val binaryBitmap = BinaryBitmap(HybridBinarizer(BufferedImageLuminanceSource(image)))
		val hints = hashMapOf<DecodeHintType, Any>(
				DecodeHintType.CHARACTER_SET to "UTF-8")
		return MultiFormatReader().decode(binaryBitmap, hints)
	}
}