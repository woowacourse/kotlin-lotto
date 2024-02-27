package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_TOO_LARGE
import lotto.exception.ExceptionsHandler.handleValidation
import lotto.model.Lotto.Companion.LOTTO_SIZE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_RANGE

class LottoMachine(private val price: Price) {
    fun getRandomLottoCount(lottoManualPurchaseCount: Int): Int {
        val randomLottoCount = price.getNumberOfLottoTickets() - lottoManualPurchaseCount
        handleValidation(MANUAL_PURCHASE_COUNT_TOO_LARGE) { randomLottoCount >= 0 }
        return randomLottoCount
    }

    fun createLottoBundle(manualPurchaseLottos: ManualPurchaseLottos): LottoBundle {
        val manualLottoBundle = createManualLottoBundle(manualPurchaseLottos)
        val randomLottoBundle = createRandomLottoBundle(getRandomLottoCount(manualPurchaseLottos.lottos.size))
        return manualLottoBundle.append(randomLottoBundle)
    }

    private fun createManualLottoBundle(manualPurchaseLottos: ManualPurchaseLottos): LottoBundle {
        val lotts = manualPurchaseLottos.lottos.map { lottoNumbers -> createLotto(lottoNumbers) }
        return LottoBundle(lotts)
    }

    private fun createRandomLottoBundle(randomLottoCount: Int): LottoBundle {
        val lottos = List(randomLottoCount) { createLotto() }
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
