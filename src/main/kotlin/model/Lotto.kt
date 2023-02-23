package model

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.size == LOTTO_NUMBER_COUNT_RULE) { LOTTO_NUMBER_COUNT_ERROR_MESSAGE }
        require(lottoNumbers.toSet().size == LOTTO_NUMBER_COUNT_RULE) { LOTTO_NUMBER_DUPLICATE_ERROR_MESSAGE }
    }

    fun getNumbers() = lottoNumbers.map { lottoNumber -> lottoNumber.number }

    fun getMatchOfNumber(winningNumber: Lotto): Int {
        val winningNumbers: List<LottoNumber> = winningNumber.getNumbers()
            .map { number ->
                LottoNumber(number)
            }

        return lottoNumbers.filter { lottoNumber ->
            winningNumbers.contains(lottoNumber)
        }.size
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT_RULE = 6
        private const val LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호의 개수가 6개가 아닙니다"
        private const val LOTTO_NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호가 중복입니다"
    }
}
