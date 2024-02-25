package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_TOO_LARGE
import lotto.exception.ExceptionsHandler.handleValidation

class LottoMachine(private val price: Price) {
    fun getRandomLottoCount(lottoPurchaseCount: LottoManualPurchase): Int {
        val randomLottoCount = price.getNumberOfLottoTickets() - lottoPurchaseCount.count
        handleValidation(MANUAL_PURCHASE_COUNT_TOO_LARGE) { randomLottoCount >= 0 }
        return randomLottoCount
    }

    fun createLottoBundle(
        lottoManualPurchaseNumbers: List<List<String>>,
        lottoPurchaseCount: LottoManualPurchase,
    ): LottoBundle {
        val manualLottoBundle = createManualLottoBundle(lottoManualPurchaseNumbers)
        val randomLottoBundle = createRandomLottoBundle(lottoPurchaseCount)
        return manualLottoBundle.append(randomLottoBundle)
    }

    private fun createManualLottoBundle(lottoManualPurchaseNumbers: List<List<String>>) =
        LottoBundle(
            lottoManualPurchaseNumbers.map { lottoManualPurchaseNumber ->
                Lotto(lottoManualPurchaseNumber.map { LottoNumber.from(it) }.toSet())
            },
        )

    private fun createRandomLottoBundle(lottoPurchaseCount: LottoManualPurchase): LottoBundle {
        val lottos = List(price.getNumberOfLottoTickets() - lottoPurchaseCount.count) { randomLotto() }
        return LottoBundle(lottos)
    }

    private fun randomLotto(): Lotto = Lotto(LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }.toSet())

    companion object {
        private const val LOTTO_SIZE = 6
        private val LOTTO_NUMBER_RANGE: IntRange = 1..45
        const val MIN_PRICE = 1_000
    }
}
