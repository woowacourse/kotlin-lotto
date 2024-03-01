package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class Ticket(
    val lottoCount: LottoCount,
) {
    fun issueLottos(
        manualLotto: List<Lotto> = emptyList(),
        lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator(),
    ): List<Lotto> {
        validateManualLotto(manualLotto)

        val lottos = MutableList(lottoCount.autoCount) { Lotto(lottoNumberGenerator.generate()) }
        lottos.addAll(manualLotto)
        return lottos.toList()
    }

    private fun validateManualLotto(manualLotto: List<Lotto>) {
        require(lottoCount.manualCount == manualLotto.size) {
            "입력한 수동 로또 구매 수보다 더 많이 수동 로또를 입력할 수 없습니다."
        }
    }
}
