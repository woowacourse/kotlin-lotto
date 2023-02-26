package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.LottoTicket
import lotto.entity.Lottos

class LottoStore(private val purchaseLottoCount: LottoCount, private val lottoGenerator: LottoGenerator = RandomLottoGenerator()) {

    fun buyManualLotto(vararg lottos: LottoTicket): Lottos {
        return Lottos(lottos.map { lottoTicket -> Lotto.from(lottoTicket.numbers.map(::LottoNumber)) })
    }

    fun calculateAutoLottoCount(manualLottoCount: LottoCount): LottoCount = purchaseLottoCount - manualLottoCount

    fun buyAutoLotto(lottoCount: LottoCount): Lottos {
        return Lottos(List(lottoCount.value) { lottoGenerator.generate() })
    }

    fun mergeLottos(manualLotto: Lottos, autoLotto: Lottos): Lottos = manualLotto + autoLotto
}
