package lotto.model

import lotto.contants.LottoRuleConstants

data class LottoNumber(
    val number: Int,
) {
    init {
        require(number in LottoRuleConstants.MINIMUM_NUMBER.value..LottoRuleConstants.MAXIMUM_NUMBER.value) { ERROR_INVALID_RANGE }
    }

    companion object {
        private const val ERROR_INVALID_RANGE = "숫자는 1에서 45 사이여야 합니다."
    }
}
