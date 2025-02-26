package domain.model.lotto

import domain.model.number.LottoNumber

data class Lotto(val numbers: List<LottoNumber>) {
    init {
        if (numbers.size != LOTTO_SIZE) {
            throw LottoException.InvalidLottoSize()
        }
        if (numbers.size != numbers.toSet().size) {
            throw LottoException.DuplicatedLottoSize()
        }
    }

    companion object {
        const val LOTTO_SIZE = 6
    }
}
