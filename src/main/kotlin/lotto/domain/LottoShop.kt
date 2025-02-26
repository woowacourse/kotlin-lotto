package lotto.domain

import lotto.domain.Lotto.Companion.PRICE

class LottoShop(
    pay: Money,
    val manualCount: Int = 0,
) {
    constructor(pay: Int, manualCount: Int = 0) : this(Money(pay), manualCount)

    private val lottoCount: Int = pay / PRICE
    val randomCount: Int = lottoCount - manualCount

    init {
        require(lottoCount >= manualCount) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTO }
    }

    fun buyLottos(manualLottosNumbers: List<List<Int>> = emptyList()): List<Lotto> {
        val lottoMachine = LottoMachine()
        val manualLottos: List<Lotto> = lottoMachine.produce(ManualLottoGenerator(manualLottosNumbers))
        val randomLottos: List<Lotto> = lottoMachine.produce(RandomLottoGenerator(randomCount))
        return manualLottos + randomLottos
    }

    companion object {
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTO = "수동으로 구매할 로또의 개수가 너무 많습니다."
    }
}
