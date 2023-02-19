package domain

import domain.model.PurchaseMoney
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber

class LottoGenerator(
    private val numberGenerator: () -> Set<LottoNumber> = {
        (MINIMUM_NUMBER..MAXIMUM_NUMBER).drawLottoNumbers()
    }
) {

    fun generateLottosAutomatically(count: Int): List<Lotto> =
        List(count) { Lotto(numberGenerator()) }

    fun generateLottosManually(lottoNumbers: Set<LottoNumber>): Lotto =
        Lotto(lottoNumbers)

    private fun PurchaseMoney.getNumberOfLottos(): Int = this.money / LOTTO_PRICE

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val NUMBER_COUNT = 6
        private const val LOTTO_PRICE = 1000
        private const val ZERO = 0

        private const val NUMBER_UNIT_ERROR = "[ERROR] 천원 단위로 입력해주세요."

        private fun IntRange.drawLottoNumbers() =
            this.shuffled().subList(ZERO, NUMBER_COUNT).sorted().map { number -> LottoNumber.from(number) }.toSet()
    }
}
