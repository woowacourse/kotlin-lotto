package lotto.model

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.distinct().size == LOTTO_SIZE) { LOTTO_SIZE_ERROR_MESSAGE }
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val LOTTO_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
    }
}
