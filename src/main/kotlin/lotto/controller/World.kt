package lotto.controller

import lotto.entity.Bonus
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinNumber
import lotto.entity.WinStatistics
import lotto.model.LottoRankDeterminer
import lotto.model.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.OutputView

class World {
    private val lottoPrice = LottoPrice(DEFAULT_LOTTO_PRICE)

    fun initPurchaseMoney(): PurchaseMoney {
        OutputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
        return InputView.readPurchaseMoney(lottoPrice)
    }

    fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoCount = purchaseMoney.value / lottoPrice.value
        OutputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoCount)
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        OutputView.lottosResult(lottos)
        return lottos
    }

    fun initWinNumber(): WinNumber {
        OutputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
        return InputView.readWinNumber()
    }

    fun initBonus(): Bonus {
        OutputView.printMessage(OutputView.MESSAGE_BONUS)
        return InputView.readBonus()
    }

    fun makeWinStatistics(): WinStatistics {
        val purchaseMoney = initPurchaseMoney()
        val lottos = initLottos(purchaseMoney)
        val winLotto = WinLotto(initWinNumber(), initBonus())
        val winStatistics = WinStatistics(
            lottos.value.map {
                LottoRankDeterminer().determine(it, winLotto)
            }
        )
        return winStatistics
    }

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
