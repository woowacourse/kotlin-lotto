package lotto.controller

import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney
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

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
