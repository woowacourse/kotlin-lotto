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
        manualPurchaseLottos: ManualPurchaseLottos,
        lottoManualPurchaseCount: LottoManualPurchaseCount,
    ): LottoBundle {
        val manualLottoBundle = createManualLottoBundle(manualPurchaseLottos)
        val randomLottoBundle = createRandomLottoBundle(lottoManualPurchaseCount)
        return manualLottoBundle.append(randomLottoBundle)
    }

    private fun createManualLottoBundle(manualPurchaseLottos: ManualPurchaseLottos): LottoBundle {
        val lotts = manualPurchaseLottos.lottos.map { lottoNumbers -> createLotto(lottoNumbers) }
        return LottoBundle(lotts)
    }

    private fun createRandomLottoBundle(lottoManualPurchaseCount: LottoManualPurchaseCount): LottoBundle {
        val lottos = List(getRandomLottoCount(lottoManualPurchaseCount)) { createLotto() }
        return LottoBundle(lottos)
    }

    private fun createLotto(lottoNumbers: List<String>? = null): Lotto {
        val numbers = lottoNumbers?.map { number -> LottoNumber.from(number) } ?: generateRandomLottoNumbers()
        return Lotto(numbers.toSet())
    }

    private fun generateRandomLottoNumbers(): List<LottoNumber> {
        return LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_SIZE).map { LottoNumber(it) }
    }
}
