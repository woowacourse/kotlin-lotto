package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("보너스 번호 판단 여부 테스트 데이터")
    fun `보너스 번호를 가지는 순위인지 판단한다`(
        lottoPrize: LottoPrize,
        expected: Boolean,
    ) {
        val actual = lottoPrize.isBonus()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("로또 번호 일치 개수에 따른 순위 결정 테스트 데이터")
    fun `로또 번호가 일치하는 개수에 따라 순위를 결정한다`(
        matchingCount: Int,
        expected: LottoPrize,
    ) {
        val actual = LottoPrize.valueOf(matchingCount, false)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("보너스 번호 일치 여부에 따른 순위 결정 테스트 데이터")
    fun `로또 번호가 5개 일치할 때, 보너스 번호에 따라 순위를 결정한다`(
        isMatchingBonus: Boolean,
        expected: LottoPrize,
    ) {
        val actual = LottoPrize.valueOf(5, isMatchingBonus)
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `보너스 번호 판단 여부 테스트 데이터`() =
            listOf(
                Arguments.of(LottoPrize.FIRST, false),
                Arguments.of(LottoPrize.SECOND, true),
                Arguments.of(LottoPrize.THIRD, false),
                Arguments.of(LottoPrize.FOURTH, false),
                Arguments.of(LottoPrize.FIFTH, false),
            )

        @JvmStatic
        fun `로또 번호 일치 개수에 따른 순위 결정 테스트 데이터`() =
            listOf(
                Arguments.of(0, LottoPrize.NOTHING),
                Arguments.of(1, LottoPrize.NOTHING),
                Arguments.of(2, LottoPrize.NOTHING),
                Arguments.of(3, LottoPrize.FIFTH),
                Arguments.of(4, LottoPrize.FOURTH),
                Arguments.of(6, LottoPrize.FIRST),
            )

        @JvmStatic
        fun `보너스 번호 일치 여부에 따른 순위 결정 테스트 데이터`() =
            listOf(
                Arguments.of(true, LottoPrize.SECOND),
                Arguments.of(false, LottoPrize.THIRD),
            )
    }
}
