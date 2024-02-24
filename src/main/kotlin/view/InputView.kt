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

    fun inputPurchaseSizeOfManualLotto(purchaseAmount: Int): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val numberOfManualLotto = br.readLine()

        return try {
            InputValidator.validatePurchaseSizeOfManualLotto(numberOfManualLotto, purchaseAmount)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputPurchaseSizeOfManualLotto(purchaseAmount)
        }
    }

    fun inputGuideManualLottoNumbers() {
        println()
        println("수동으로 구매할 번호를 입력해 주세요.")
    }

    fun inputManualLottos(): List<Int> {
        val manualLotto = br.readLine()

        return try {
            InputValidator.validatePublishedLottos(manualLotto)
        } catch (exceptIon: IllegalArgumentException) {
            println(exceptIon.message)
            inputManualLottos()
        }
    }

    fun inputWinningNumbers(): List<Int> {
        println()
        println("지난 주 당첨 번호를 입력해 주세요.")
        val winningNumbers = br.readLine()

        return try {
            InputValidator.validatePublishedLottos(winningNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputWinningNumbers()
        }
    }

    fun inputBonusNumber(winningNumbers: List<Int>): Int {
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
