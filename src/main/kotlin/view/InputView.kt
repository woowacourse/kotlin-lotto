package view

import model.LottoNumber
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

    fun inputHandpickedNumber(): Int {
        println()
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val handpickedNumber = br.readLine()

        return try {
            InputValidator.validateHandpickedNumber(handpickedNumber)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputHandpickedNumber()
        }
    }

    fun inputHandpickedNumbers(): List<LottoNumber> {
        println()
        println("수동으로 구매할 번호를 입력해 주세요.")
        val handpickedNumbers = br.readLine()

        return try {
            InputValidator.validateHandpickedNumbers(handpickedNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputHandpickedNumbers()
        }
    }

    fun inputWinningNumbers(): List<LottoNumber> {
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

    fun inputBonusNumber(winningNumbers: List<LottoNumber>): LottoNumber {
        println()
        println("보너스 볼을 입력해 주세요.")
        val bonusNumber = br.readLine()

        return try {
            InputValidator.validateBonusNumber(bonusNumber, winningNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputBonusNumber(winningNumbers)
        }
    }
}
