package model

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.size == LOTTO_NUMBER_SIZE) { NUMBER_SIZE_EXCEPTION_MESSAGE }
        require(lottoNumbers.toSet().size == LOTTO_NUMBER_SIZE) { DUPLICATED_NUMBER_EXCEPTION_MESSAGE }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val NUMBER_SIZE_EXCEPTION_MESSAGE = "로또 번호는 ${LOTTO_NUMBER_SIZE}개여야 합니다."
        const val DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "로또 번호끼리는 중복되면 안됩니다."
    }
}
