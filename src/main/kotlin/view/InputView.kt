package view

import model.LottoNumber
import util.InputValidator
import java.io.BufferedReader
import java.io.InputStreamReader

object InputView {
    private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
    private const val INPUT_PURCHASE_SIZE_OF_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val INPUT_GUIDE_MANUAL_LOTTO_NUMBER = "\n수동으로 구매할 번호를 입력해 주세요."
    private const val INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER = "\n보너스 볼을 입력해 주세요."

    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun inputPurchaseAmount(): Int {
        println(INPUT_PURCHASE_AMOUNT)
        val purchaseAmount = br.readLine()

        return try {
            InputValidator.validatePurchaseAmount(purchaseAmount)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputPurchaseAmount()
        }
    }

    fun inputPurchaseSizeOfManualLotto(purchaseAmount: Int): Int {
        println(INPUT_PURCHASE_SIZE_OF_MANUAL_LOTTO)
        val numberOfManualLotto = br.readLine()

        return runCatching {
            InputValidator.validatePurchaseSizeOfManualLotto(numberOfManualLotto, purchaseAmount)
        }.fold(
            onSuccess = { it },
            onFailure = { exception ->
                println(exception.message)
                inputPurchaseSizeOfManualLotto(purchaseAmount)
            },
        )
    }

    fun inputGuideManualLottoNumbers() {
        println(INPUT_GUIDE_MANUAL_LOTTO_NUMBER)
    }

    fun inputManualLottos(): List<LottoNumber> {
        val manualLotto = br.readLine()

        return runCatching {
            InputValidator.validatePublishedLottos(manualLotto)
        }.fold(
            onSuccess = { it },
            onFailure = { exception ->
                println(exception.message)
                inputManualLottos()
            },
        )
    }

    fun inputWinningNumbers(): List<LottoNumber> {
        println(INPUT_WINNING_NUMBER)
        val winningNumbers = br.readLine()

        return try {
            InputValidator.validatePublishedLottos(winningNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputWinningNumbers()
        }
    }

    fun inputBonusNumber(winningNumbers: List<LottoNumber>): Int {
        println(INPUT_BONUS_NUMBER)
        val bonusNumber = br.readLine()

        return try {
            InputValidator.validateBonusNumber(bonusNumber, winningNumbers)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputBonusNumber(winningNumbers)
        }
    }
}
