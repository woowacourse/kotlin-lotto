package lotto

import lotto.domain.Bank
import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.Validator
import lotto.domain.Validator.ERROR_INPUT_HANDLER
import lotto.domain.Validator.ERROR_NOT_NUMBER
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
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoes = mutableListOf<Lotto>()
        repeat(purchaseCount) {
            lottoes.add(lottoFactory.createLotto())
        }

        val lottoBunch = LottoBunch(lottoes)
        OutputView.printPurchaseResult(lottoBunch.toString(), purchaseCount)
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
