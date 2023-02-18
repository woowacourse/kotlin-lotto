package domain.game

import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import util.common.generateDistinctRandomNumbers

class LottoMachine {
    fun purchaseLottos(money: Money): List<PurchasedLotto> = mutableListOf<PurchasedLotto>().apply {
        val lottoCount = money.divideByThousand()

        repeat(lottoCount) {
            add(generateLotto())
        }
    }

    private fun generateLotto(): PurchasedLotto =
        PurchasedLotto(
            (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER).generateDistinctRandomNumbers(Lotto.LOTTO_SIZE)
                .map { LottoNumber(it) }
        )
}
