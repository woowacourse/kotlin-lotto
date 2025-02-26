package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_MIN_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE

class LottoMachine(
    private val amount: Int,
    val passiveLottoQuantity: Int,
    private val passiveLottoNumbers: List<Lotto>,
) {
    init {
        validateAmountMinimumRange()
        validateAmountUnit()
    }

    private fun validateAmountMinimumRange() {
        require(amount > LOTTO_MIN_AMOUNT) {
            "[ERROR] ${LOTTO_MIN_AMOUNT}원 이상의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    private fun validateAmountUnit() {
        require(amount % LOTTO_EACH_AMOUNT == 0) {
            "[ERROR] ${LOTTO_EACH_AMOUNT}원 단위의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    fun getActiveLottoQuantity(): Int = amount / LOTTO_EACH_AMOUNT - passiveLottoQuantity

    private fun getActiveLottos(lottoQuantity: Int): Lottos {
        val lottos = List<Lotto>(lottoQuantity) { Lotto(getLottoNumbers().map { LottoNumber((it)) }.toSet()) }
        return Lottos(lottos)
    }

    fun getTotalLottos(lottoQuantity: Int): Lottos = Lottos(passiveLottoNumbers).plus(getActiveLottos(lottoQuantity))

    private fun getLottoNumbers(): List<Int> {
        val shuffledLottoNumbers = (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE).shuffled()
        val selectedLottoNumbers = shuffledLottoNumbers.take(LOTTO_NUMBER_SIZE)

        return selectedLottoNumbers.sorted()
    }

    fun getProfitRate(countLottoByRank: Map<Rank, Int>): Float {
        val totalProfit = countLottoByRank.entries.sumOf { it.key.winningMoney * it.value }
        return formatProfitRate(totalProfit)
    }

    private fun formatProfitRate(totalProfit: Int): Float = totalProfit / amount.toFloat()

    companion object {
        private const val LOTTO_MIN_AMOUNT = 0
        private const val LOTTO_EACH_AMOUNT = 1000
    }
}
