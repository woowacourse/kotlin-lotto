package lotto.domain

enum class LottoRules(val value: Int) {
    INVALID_LOTTO_MIN_NUMBER(1),
    INVALID_LOTTO_MAX_NUMBER(45),
    LOTTO_NUMBER_SIZE(6),
}
