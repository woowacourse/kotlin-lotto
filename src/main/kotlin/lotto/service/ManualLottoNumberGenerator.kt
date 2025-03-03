package lotto.service

import lotto.domain.LottoNumber

class ManualLottoNumberGenerator(val numbers: List<Int>) : LottoNumberGenerator {
    override fun generateLottoNumbers(): List<LottoNumber> {
        return numbers.map { numbers -> LottoNumber(numbers) }
    }
}
