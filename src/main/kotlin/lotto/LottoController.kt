package lotto

import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.Validator
import lotto.domain.Validator.ERROR_INPUT_HANDLER
import lotto.domain.Validator.ERROR_NOT_NUMBER
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun runLotto() {
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            OutputView.printGetPurchaseMoneyScript()
            getVerifiedPurchaseMoney(InputView.getUserInput())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getPurchaseMoney) as PurchaseMoney
        }
    }

    private fun getVerifiedPurchaseMoney(input: String): PurchaseMoney {
        require(Validator.isNumber(input)) { ERROR_NOT_NUMBER }

        return PurchaseMoney(input.toInt())
    }

    private fun getMainLottoNumber(): List<LottoNumber> {
        return kotlin.runCatching {
            OutputView.printGetMainLottoNumbersScript()
            getVerifiedMainLottoNumber(InputView.getUserInput())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getMainLottoNumber) as List<LottoNumber>
        }
    }

    private fun getVerifiedMainLottoNumber(input: String): List<LottoNumber> {
        val lottoNumbers = input.split(",").map { number -> number.trim() }

        lottoNumbers.forEach { require(Validator.isNumber(it)) { ERROR_NOT_NUMBER } }

        return lottoNumbers.map { LottoNumber(it.toInt()) }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            OutputView.printGetBonusLottoNumberScript()
            getVerifiedBonusLottoNumber(InputView.getUserInput())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getBonusLottoNumber) as LottoNumber
        }
    }

    private fun getVerifiedBonusLottoNumber(input: String): LottoNumber {
        require(Validator.isNumber(input)) { ERROR_NOT_NUMBER }

        return LottoNumber(input.toInt())
    }

    fun getWinningLotto(): WinningLotto = WinningLotto(getMainLottoNumber(), getBonusLottoNumber())

    private fun inputErrorHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        return when (error) {
            is IllegalArgumentException -> inputIllegalArgumentExceptionHandler(error, repeatFunction)
            else -> throw IllegalStateException(ERROR_INPUT_HANDLER)
        }
    }

    private fun inputIllegalArgumentExceptionHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        println(error.message)
        return repeatFunction()
    }
}
