package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import lotto.domain.value.LottoCount

class LottoStore {
    private val manualLottoMachine = ManualLottoMachine()
    private val automaticLottoMachine = AutomaticLottoMachine()

    fun purchase(lottoOrder: LottoOrder): List<Lotto> {
        val manualLottos =
            purchaseLotto(
                manualLottoMachine,
                lottoOrder.manualLottoCount,
                lottoOrder.manualLottoNumbers,
            )
        val automaticLottos =
            purchaseLotto(automaticLottoMachine, lottoOrder.getAutomaticLottoCount())
        return manualLottos + automaticLottos
    }

    private fun purchaseLotto(
        lottoMachine: LottoMachine,
        lottoCount: LottoCount,
        lottoNumbers: List<List<Int>>? = null,
    ): List<Lotto> = lottoMachine.generate(lottoCount, lottoNumbers)
}
