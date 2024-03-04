package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    fun `로또 번호 일치 개수와 보너스 번호 일치 여부에 따른 상금 타입을 반환한다`(
        matchingCount: Int,
        isBonusMatching: Boolean,
        lottoPrize: LottoPrize,
    ) {
        val actual = LottoPrize.getLottoPrize(matchingCount, isBonusMatching)
        assertThat(actual).isEqualTo(lottoPrize)
    }

    companion object {
        @JvmStatic
        fun `로또 당첨 결과 테스트 데이터`() =
            listOf(
                Arguments.of(6, false, LottoPrize.FIRST),
                Arguments.of(5, 1, LottoPrize.SECOND),
                Arguments.of(5, false, LottoPrize.THIRD),
                Arguments.of(4, false, LottoPrize.FOURTH),
                Arguments.of(3, false, LottoPrize.FIFTH),
                Arguments.of(0, false, LottoPrize.NOTHING),
            )
    }
}
