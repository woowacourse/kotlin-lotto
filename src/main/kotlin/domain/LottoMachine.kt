package domain

interface LottoMachine {
    fun createRandomLotto(): Lotto
    fun createManualLotto(lottoNumbers: Set<Int>): Lotto
}
