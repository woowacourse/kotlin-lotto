package domain.game

import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money
import domain.result.LottoPurchaseResult
import util.common.constant.ERROR_PREFIX
import util.common.generateDistinctRandomNumbers

class LottoMachine {
    fun purchaseAutoLottos(money: Money): List<PurchasedLotto> =
        List(money / Money(LOTTO_PRICE)) { generateRandomLotto() }

    private fun generateRandomLotto(): PurchasedLotto = PurchasedLotto(
        (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER).generateDistinctRandomNumbers(Lotto.LOTTO_SIZE)
            .map { LottoNumber(it) }.toSet()
    )

    fun purchaseManualLottos(
        money: Money,
        lottoSize: LottoSize,
        lottoNumbers: List<Set<LottoNumber>>,
    ): LottoPurchaseResult {
        require(lottoSize.value == lottoNumbers.size) { ERROR_MESSAGE_INVALID_PURCHASING_LOTTO_SIZE }
        require(money.isGreaterThan(Money(LOTTO_PRICE * lottoSize.value))) { ERROR_MESSAGE_INSUFFICIENT_AMOUNT }
        return LottoPurchaseResult(
            money - (Money(LOTTO_PRICE * lottoSize.value)),
            List(lottoSize.value) { generateManualLotto(lottoNumbers[it].toSet()) }
        )
    }

    private fun generateManualLotto(lottoNumbers: Set<LottoNumber>): PurchasedLotto = PurchasedLotto(lottoNumbers)

    companion object {
        const val LOTTO_PRICE = 1_000
        private const val ERROR_MESSAGE_INVALID_PURCHASING_LOTTO_SIZE =
            "$ERROR_PREFIX 입력하신 로또 구매 개수와 로또 번호 리스트의 개수가 일치하지 않습니다."
        private const val ERROR_MESSAGE_INSUFFICIENT_AMOUNT = "$ERROR_PREFIX 금액이 부족하여 로또를 구매하실 수 없습니다."
    }
}
