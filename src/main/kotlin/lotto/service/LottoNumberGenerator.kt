package lotto.service

import lotto.domain.LottoNumber

interface LottoNumberGenerator {
    fun generateLottoNumbers(): List<LottoNumber>
}
