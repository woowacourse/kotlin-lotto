package domain.game

import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money
import util.common.constant.ERROR_PREFIX
import util.common.generateDistinctRandomNumbers

class LottoMachine {
    fun purchaseAutoLottos(money: Money): List<PurchasedLotto> = mutableListOf<PurchasedLotto>().apply {
        val lottoCount = money.divideBy(Money(LOTTO_PRICE))

        repeat(lottoCount) {
            add(generateRandomLotto())
        }
    }

    private fun generateRandomLotto(): PurchasedLotto =
        PurchasedLotto(
            (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER).generateDistinctRandomNumbers(Lotto.LOTTO_SIZE)
                .map { LottoNumber(it) }
        )

    fun purchaseManualLottos(money: Money, lottoSize: LottoSize, lottoNumbers: List<List<LottoNumber>>): Pair<Money, List<PurchasedLotto>> {
        require(lottoSize.value == lottoNumbers.size) { ERROR_MESSAGE_INVALID_PURCHASING_LOTTO_SIZE }
        return Pair(Money(money.amount - (1000 * lottoSize.value)), List(lottoSize.value) { generateManualLotto(lottoNumbers[it]) })
    }

    private fun generateManualLotto(lottoNumbers: List<LottoNumber>): PurchasedLotto =
        PurchasedLotto(lottoNumbers)

    companion object {
        const val LOTTO_PRICE = 1000
        private const val ERROR_MESSAGE_INVALID_PURCHASING_LOTTO_SIZE =
            "$ERROR_PREFIX 입력하신 로또 구매 개수와 로또 번호 리스트의 개수가 일치하지 않습니다."
    }
}
