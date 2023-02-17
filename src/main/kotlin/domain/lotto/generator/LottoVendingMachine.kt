package domain.lotto.generator

import domain.Money
import domain.lotto.Lotto
import domain.lotto.LottoBundleDto

object LottoVendingMachine {
    const val LOTTO_PRICE = 1000

    fun getLottoCount(money: Money): Int {
        return (money.amount / LOTTO_PRICE).toInt()
    }

    fun getLottoBundle(lottoCount: Int, lottoGenerator: LottoGenerator): LottoBundleDto {
        val lottos = mutableListOf<Lotto>()
        repeat(lottoCount) {
            lottos.add(lottoGenerator.generate())
        }
        return LottoBundleDto(lottos)
    }
}
