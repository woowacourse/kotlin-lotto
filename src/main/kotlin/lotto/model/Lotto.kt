package lotto.model

import lotto.constant.LottoConstant

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.distinct().size == LottoConstant.SIZE) { LOTTO_SIZE_ERROR_MESSAGE }
    }

    fun calculateCountOfMatch(winningLotto: Lotto): Int {
        return lottoNumbers.intersect(winningLotto.lottoNumbers.toSet()).size
    }

    fun calculateMatchBonus(bonusLottoNumber: LottoNumber): Boolean {
        return bonusLottoNumber in lottoNumbers
    }

    companion object {
        private const val LOTTO_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
    }
}
