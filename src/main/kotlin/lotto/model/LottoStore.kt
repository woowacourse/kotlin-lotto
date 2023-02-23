package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.Lottos

class LottoStore(private val lottoGenerator: LottoGenerator = RandomLottoGenerator()) {
    fun buyManualLotto(lottoCount: Int, vararg lottos: List<Int>): Lottos {
        require(lottoCount >= lottos.size) { ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO }
        return Lottos(lottos.map { lotto -> Lotto.from(lotto.map { number -> LottoNumber(number) }) })
    }

    fun buyAutoLotto(lottoCount: Int): Lottos {
        return Lottos(List(lottoCount) { lottoGenerator.generate() })
    }

    companion object {
        private const val ERROR_MESSAGE_OVER_TO_AVAILABLE_LOTTO = "구매 가능한 로또 수를 초과하였습니다"
    }
}
