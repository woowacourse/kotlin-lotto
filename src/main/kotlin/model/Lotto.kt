package model

import constants.LottoConstants

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
        require(lottoNumbers.map { it.number }.distinct().size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
    }
}
