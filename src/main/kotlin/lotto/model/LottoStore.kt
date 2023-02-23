package lotto.model

import lotto.entity.Lotto
import lotto.entity.Lottos

class LottoStore(private val lottoGenerator: LottoGenerator = RandomLottoGenerator()) {
    fun buyManualLotto(lottoCount: Int, vararg lottos: Lotto): Lottos {
        require(lottoCount >= lottos.size) { ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO }
        return Lottos(lottos.map { it })
    }

    fun buyAutoLotto(lottoCount: Int): Lottos {
        return Lottos(List(lottoCount) { lottoGenerator.generate() })
    }

    fun mergeLottos(manualLotto: Lottos, autoLotto: Lottos): Lottos = manualLotto + autoLotto

    companion object {
        private const val ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO = "구매 가능한 로또 수를 초과하였습니다"
    }
}
