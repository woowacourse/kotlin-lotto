package lotto.domain

import lotto.domain.Lotto.Companion.PRICE

class LottoShop {
    fun buyLottos(
        pay: Int,
        manualLottos: List<List<Int>> = emptyList(),
    ): List<Lotto> {
        val randomLottoCount = countRandomLotto(pay, manualLottos.size)
        val lottoMachine = LottoMachine()
        val manualLottos: List<Lotto> = lottoMachine.makeLotto(ManualLottoGenerator(numbers = manualLottos))
        val randomLottos: List<Lotto> = lottoMachine.makeLotto(RandomLottoGenerator(count = randomLottoCount))
        return manualLottos + randomLottos
    }

    private fun countRandomLotto(
        pay: Int,
        manualLottoCount: Int,
    ): Int {
        require(pay >= PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
        val lottoCount: Int = countLotto(pay)
        val randomLottoCount: Int = lottoCount - manualLottoCount
        require(lottoCount >= manualLottoCount)
        return randomLottoCount
    }

    companion object {
        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${PRICE}원입니다."

        fun countLotto(pay: Int) = pay / PRICE
    }
}
