package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.LottoTicket
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney

class LottoStore(
    private val purchaseMoney: PurchaseMoney,
    private val manualLottoTickets: Array<LottoTicket> = arrayOf(),
    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()
) {
    var lottoCount = purchaseMoney.getPurchaseLottoCount()
        private set

    fun makeLottos(): Lottos {
        return mergeLottos(buyManualLotto(), buyAutoLotto())
    }

    fun buyManualLotto(): Lottos {
        lottoCount -= manualLottoTickets.size
        return Lottos(manualLottoTickets.map { lottoTicket -> Lotto.from(lottoTicket.numbers.map(::LottoNumber)) })
    }

    fun buyAutoLotto(): Lottos {
        return Lottos(List(lottoCount) { lottoGenerator.generate() })
    }

    private fun mergeLottos(manualLotto: Lottos, autoLotto: Lottos): Lottos = manualLotto + autoLotto
}
