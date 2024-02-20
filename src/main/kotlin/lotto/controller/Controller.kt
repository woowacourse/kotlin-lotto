package lotto.controller
import lotto.util.Constant
import lotto.view.insertCostMessage
import lotto.view.purchaseCountMessage
import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))

class Controller {
    fun run() {
        insertCostMessage()
        val charge = br.readLine()
        purchaseCountMessage(Constant.PURCHASE_LOTTO_COUNT.format(charge/1000))
    }
}