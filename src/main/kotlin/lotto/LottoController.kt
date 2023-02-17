package lotto

import lotto.domain.Bank
import lotto.domain.InputNumberValidator
import lotto.domain.InputNumberValidator.ERROR_INPUT_HANDLER
import lotto.domain.InputNumberValidator.ERROR_NOT_NUMBER
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.factory.LottoFactory
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(purchaseMoney.getPurchaseCount())
        val winningLotto = getWinningLotto()
        confirmLottoWinning(lottoBunch, winningLotto, purchaseMoney)
    }

    private fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoBunch = LottoBunch(lottoFactory, purchaseCount)
        OutputView.printPurchaseResult(lottoBunch, purchaseCount)
        return lottoBunch
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
        require(InputNumberValidator.isNumber(input)) { ERROR_NOT_NUMBER }

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

        lottoNumbers.forEach { require(InputNumberValidator.isNumber(it)) { ERROR_NOT_NUMBER } }

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
        require(InputNumberValidator.isNumber(input)) { ERROR_NOT_NUMBER }

        return LottoNumber(input.toInt())
    }

    fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getWinningLotto) as WinningLotto
        }
    }

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

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        OutputView.printWinningStatsScript()
        val ranks = lottoBunch.value.map { Bank.getRank(it, winningLotto) }
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult.toString())

        OutputView.printYieldRate(Bank.getYieldRate(purchaseMoney, Bank.sumTotalPrizeMoney(lottoBunch, winningLotto)))
    }
}
