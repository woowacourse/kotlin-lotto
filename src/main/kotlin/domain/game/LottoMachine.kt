package domain.game

import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.number.LottoNumber
import util.common.divideByThousand
import util.common.generateDistinctRandomNumbers

class LottoMachine {
    fun purchaseLottos(money: Int): List<PurchasedLotto> = mutableListOf<PurchasedLotto>().apply {
        val lottoCount = money.divideByThousand()

        repeat(lottoCount) {
            add(purchaseLotto())
        }
    }

    private fun purchaseLotto(): PurchasedLotto =
        PurchasedLotto((LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER)
            .generateDistinctRandomNumbers(Lotto.LOTTO_SIZE)
            .map { LottoNumber(it) })
}
