package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_TOO_LARGE
import lotto.exception.ExceptionsHandler.handleValidation
import lotto.model.Lotto.Companion.LOTTO_SIZE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_RANGE

class LottoMachine(private val price: Price) {
    fun getRandomLottoCount(lottoManualPurchaseCount: LottoManualPurchaseCount): Int {
        val randomLottoCount = price.getNumberOfLottoTickets() - lottoManualPurchaseCount.count
        handleValidation(MANUAL_PURCHASE_COUNT_TOO_LARGE) { randomLottoCount >= 0 }
        return randomLottoCount
    }

    fun createLottoBundle(
        lottoManualPurchaseNumbers: List<List<String>>,
        lottoManualPurchaseCount: LottoManualPurchaseCount,
    ): LottoBundle {
        val manualLottoBundle = createManualLottoBundle(lottoManualPurchaseNumbers)
        val randomLottoBundle = createRandomLottoBundle(lottoManualPurchaseCount)
        return manualLottoBundle.append(randomLottoBundle)
    }

    private fun createManualLottoBundle(lottoManualPurchaseNumbers: List<List<String>>) =
        LottoBundle(
            lottoManualPurchaseNumbers.map { lottoManualPurchaseNumber ->
                Lotto(lottoManualPurchaseNumber.map { LottoNumber.from(it) }.toSet())
            },
        )

    private fun createRandomLottoBundle(lottoManualPurchaseCount: LottoManualPurchaseCount): LottoBundle =
        LottoBundle(List(getRandomLottoCount(lottoManualPurchaseCount)) { randomLotto() })

    private fun randomLotto(): Lotto = Lotto(LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }.toSet())
}
