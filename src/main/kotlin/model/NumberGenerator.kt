package lotto.model

fun interface NumberGenerator {
    fun generate(
        size: Int,
        minNumber: Int,
        maxNumber: Int,
    ): LottoNumbers
}
