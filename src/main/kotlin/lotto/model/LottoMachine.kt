package lotto.model

class LottoMachine(
    private val amount: Int,
) {
    init {
        validateAmountMinimumRange()
        validateAmountUnit()
    }

    private fun validateAmountMinimumRange() {
        require(amount >= 0) {
            "[ERROR] 0원 이상의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    private fun validateAmountUnit() {
        require(amount % 1000 == 0) {
            "[ERROR] 1,000원 단위의 금액으로 입력해 주세요. 입력값: $amount"
        }
    }

    fun getLottoQuantity(): Int = amount / 1000

    fun getLottos(lottoQuantity: Int): Lottos {
        val lottos = List<Lotto>(lottoQuantity) { Lotto(getLottoNumbers()) }
        return Lottos(lottos)
    }

    private fun getLottoNumbers(): List<Int> {
        val shuffledLottoNumbers = (1..45).shuffled()
        val selectedLottoNumbers = shuffledLottoNumbers.take(6)

        return selectedLottoNumbers.sorted()
    }

    fun getProfitRate(countLottoByRank: Map<Rank, Int>): Float {
        val totalProfit = countLottoByRank.entries.sumOf { it.key.winningMoney * it.value }
        return formatProfitRate(totalProfit)
    }

    private fun formatProfitRate(totalProfit: Int): Float = totalProfit / amount.toFloat()
}
