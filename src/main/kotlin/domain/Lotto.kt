package domain

import util.PREFIX

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    constructor(lottoNumbers: List<Int>) : this(lottoNumbers.map { LottoNumber.of(it) }.toSet())
    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.of(it) }.toSet())

    init {
        require(lottoNumbers.size == LOTTO_SIZE) { "$PREFIX 로또번호는 6개여야합니다." }
    }

    fun has(lottoNumber: LottoNumber): Boolean {
        return lottoNumbers.contains(lottoNumber)
    }

    fun countSameNumber(otherLotto: Lotto): Int {
        return lottoNumbers.count { otherLotto.has(it) }
    }

    companion object {
        const val LOTTO_SIZE = 6
    }
}
