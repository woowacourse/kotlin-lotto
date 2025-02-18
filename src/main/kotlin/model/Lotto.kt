package model

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.size == NUMBER_OF_LOTTO_NUMBERS)
        require(lottoNumbers.map { it.number }.distinct().size == NUMBER_OF_LOTTO_NUMBERS)
    }

    companion object {
        private const val NUMBER_OF_LOTTO_NUMBERS = 6
    }
}
