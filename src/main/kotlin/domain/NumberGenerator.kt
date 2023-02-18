package domain

interface NumberGenerator {
    fun generateSixNumber(start: Int, end: Int): Set<LottoNumber>
}
