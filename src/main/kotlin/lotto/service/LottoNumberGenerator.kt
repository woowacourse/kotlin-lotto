package lotto.service

interface LottoNumberGenerator {
    fun generate(): Set<Int>
}
