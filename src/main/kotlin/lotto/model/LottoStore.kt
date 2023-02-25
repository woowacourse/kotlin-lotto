package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.Lottos

class LottoStore(private val purchaseLottoCount: LottoCount, private val lottoGenerator: LottoGenerator = RandomLottoGenerator()) {

    fun buyManualLotto(lottoCount: LottoCount, vararg lottos: Lotto): Lottos {
        require(lottoCount.value >= lottos.size) { ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO }
        return Lottos(lottos.map { it })
    }

    fun calculateAutoLottoCount(manualLottoCount: LottoCount): LottoCount = purchaseLottoCount - manualLottoCount

    fun buyAutoLotto(lottoCount: LottoCount): Lottos {
        return Lottos(List(lottoCount.value) { lottoGenerator.generate() })
    }

    fun mergeLottos(manualLotto: Lottos, autoLotto: Lottos): Lottos = manualLotto + autoLotto

    companion object {
        private const val ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO = "구매 가능한 로또 수를 초과하였습니다"
    }
}
