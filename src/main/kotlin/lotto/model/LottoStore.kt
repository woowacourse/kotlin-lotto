package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.LottoTicket
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney

class LottoStore(
    private val purchaseMoney: PurchaseMoney,
    private val manualLottoTickets: Array<LottoTicket> = arrayOf(),
    private val lottoGenerator: LottoGenerator = RandomLottoGenerator()
) {
    private val auttoLottoCount get() = purchaseMoney.getPurchaseLottoCount() - manualLottoTickets.size

    fun makeLottos(): Lottos {
        return mergeLottos(buyManualLotto(), buyAutoLotto())
    }

    fun buyManualLotto(): Lottos {
        return Lottos(manualLottoTickets.map { lottoTicket -> Lotto.from(lottoTicket.numbers.map(::LottoNumber)) })
    }

    fun buyAutoLotto(): Lottos {
        return Lottos(List(auttoLottoCount) { lottoGenerator.generate() })
    }

    fun getAutoLottoCount(): LottoCount = LottoCount(auttoLottoCount)

    private fun mergeLottos(manualLotto: Lottos, autoLotto: Lottos): Lottos = manualLotto + autoLotto
}
