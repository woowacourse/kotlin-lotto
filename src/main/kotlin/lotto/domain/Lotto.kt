package lotto.domain

data class Lotto(val lottoNums: List<LottoNumber>) {
    init {
        require(lottoNums.size == DEFAULT_LOTTO_SIZE) { LOTTO_GET_SIX_NUMBER_ERROR_MESSAGE }
        require(lottoNums.size == lottoNums.toSet().size) { LOTTO_NUM_DUPLICATE_ERROR_MESSAGE }
    }

    fun compareWithNumber(number: LottoNumber): Boolean {
        return lottoNums.contains(number)
    }

    companion object {
        private const val DEFAULT_LOTTO_SIZE = 6
        private const val LOTTO_GET_SIX_NUMBER_ERROR_MESSAGE = "로또는 6개의 숫자를 가져야 합니다"
        private const val LOTTO_NUM_DUPLICATE_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다"
    }
}
