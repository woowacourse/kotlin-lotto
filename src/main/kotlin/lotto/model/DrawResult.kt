package lotto.model

data class DrawResult(
    val winningLotto: Lotto,
    val bonusNumber: String,
) {
    init {
        require(bonusNumber.toIntOrNull() in LottoAnalyzer.LOTTO_NUMBER_RANGE) { LottoAnalyzer.BONUS_NUMBER_RANGE_ERROR_MESSAGE }
        require(bonusNumber !in winningLotto.numbers) { LottoAnalyzer.BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }
}
