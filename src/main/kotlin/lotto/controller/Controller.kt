package lotto.controller
import lotto.view.insertCostMessage
import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))

class Controller {
    fun run() {
        insertCostMessage()
    }
}