package view

import util.InputValidator
import java.io.BufferedReader
import java.io.InputStreamReader

object InputView {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    fun inputPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val purchaseAmount = br.readLine()

        return try {
            InputValidator.validatePurchaseAmount(purchaseAmount)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputPurchaseAmount()
        }
    }

    fun inputWinningNumbers(): List<Int> {
        println()
        println("지난 주 당첨 번호를 입력해 주세요.")
        val winningNumbers = br.readLine()

        return try {
            InputValidator.validateWinningNumbers(winningNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputWinningNumbers()
        }
    }
}
